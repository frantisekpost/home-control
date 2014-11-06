package com.fannysoft.homecontrol.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.springframework.web.client.RestTemplate;

import com.fannysoft.homecontrol.agent.OnOffState;
import com.fannysoft.homecontrol.data.DataType;
import com.fannysoft.homecontrol.data.LatencyDTO;

public class ComponentFactory {

	public static final String SERVER_URI = "http://localhost:8080/server";
//	public static final String SERVER_URI = "http://192.168.111.132:8080/home-control";
	
	private static final String ACTOR_URL = "/actor";
	private static final String START_ACTOR_URL = "/start/";
	private static final String STOP_ACTOR_URL = "/stop/";
	private static final String READ_DATA_URL = "/data/get/";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
	
	static RestTemplate restTemplate = new RestTemplate();
	
	public static Pane createComponent(LinkedHashMap<?, ?> agentMap) {
		
		int health = (Integer) agentMap.get("health");
		int id = (Integer) agentMap.get("id");
		String name = (String) agentMap.get("name");
		String description = (String) agentMap.get("description");
		
		if (health == 0) {
			description += " (live)";
		} else {
			description += " (inactive for " + health + " minute(s))";
		}
		
		Object state = agentMap.get("state");
		Object dataType = agentMap.get("dataType");
		if (state != null) {
			OnOffState s = OnOffState.valueOf(state.toString());
			return createOnOffActor(id, name, description, s);
		} else if (dataType != null) {
			DataType d = DataType.valueOf(dataType.toString());
			Object data = agentMap.get("data");
			return createDataProvider(id, name, description, d, data);
		} else {
			return new Pane();
		}
	}
	
	private static Pane createOnOffActor(final int id, String name, String description, OnOffState state) {
		
		AnchorPane pane = new AnchorPane();
		pane.getStyleClass().add("border");
		Label nameLabel = new Label(name);
		Label descriptionLabel = new Label(description);
		
		AnchorPane.setLeftAnchor(nameLabel, 10d);
		AnchorPane.setTopAnchor(nameLabel, 5d);

		AnchorPane.setLeftAnchor(descriptionLabel, 10d);
		AnchorPane.setTopAnchor(descriptionLabel, 25d);
		
		pane.getChildren().add(nameLabel);
		pane.getChildren().add(descriptionLabel);
		
		ToggleButton toggle = new ToggleButton("OFF");
		
		AnchorPane.setRightAnchor(toggle, 10d);
		AnchorPane.setTopAnchor(toggle, 5d);
		pane.getChildren().add(toggle);
		
		toggle.setOnAction(e -> {
			if (toggle.isSelected()) {
				restTemplate.getForObject(SERVER_URI+ACTOR_URL+START_ACTOR_URL+id, String.class);
				toggle.setText(OnOffState.ON.name());
			} else {
				restTemplate.getForObject(SERVER_URI+ACTOR_URL+STOP_ACTOR_URL+id, String.class);
				toggle.setText(OnOffState.OFF.name());
			}
		});
		
		return pane;
	}
	
	@SuppressWarnings("unchecked")
	private static Pane createDataProvider(final int id, String name, String description, DataType d, Object data) {
		AnchorPane pane = new AnchorPane();
		pane.getStyleClass().add("border");
		
		Label nameLabel = new Label(name);
		Label descriptionLabel = new Label(description);
		
		AnchorPane.setLeftAnchor(nameLabel, 10d);
		AnchorPane.setTopAnchor(nameLabel, 5d);

		AnchorPane.setLeftAnchor(descriptionLabel, 10d);
		AnchorPane.setTopAnchor(descriptionLabel, 25d);
		
		pane.getChildren().add(nameLabel);
		pane.getChildren().add(descriptionLabel);
		
		switch (d) {
		case DATE:
			long ts = ((Long)data).longValue();
			Date date = new Date(ts);
			
			Label valueLabel = new Label(dateFormat.format(date));
			AnchorPane.setRightAnchor(valueLabel, 10d);
			AnchorPane.setTopAnchor(valueLabel, 5d);
			pane.getChildren().add(valueLabel);
			break;
		case LATENCY_DATA:
			ToggleButton toggle = new ToggleButton("Show data");
			
			AnchorPane.setRightAnchor(toggle, 10d);
			AnchorPane.setTopAnchor(toggle, 5d);
			pane.getChildren().add(toggle);
			
			toggle.setOnAction(e -> {
				RestTemplate restTemplate = new RestTemplate();
				LinkedHashMap<?,?> map = (LinkedHashMap<?, ?>) restTemplate.getForObject(SERVER_URI + READ_DATA_URL + id, Object.class);;
				ArrayList<Integer> minuteQueue = (ArrayList<Integer>) map.get("minuteQueue");
				ArrayList<Integer> hourQueue = (ArrayList<Integer>) map.get("hourQueue");
				ArrayList<Integer> dayQueue = (ArrayList<Integer>) map.get("dayQueue");
				
				LatencyDTO latencyData = new LatencyDTO(minuteQueue, hourQueue, dayQueue);
				
				showLatencyData(latencyData, toggle);
			});
			break;
		default:
		}
		
		return pane;

	}

	private static void showLatencyData(LatencyDTO latencyData, ToggleButton toggle) {
		Stage stage = (Stage) toggle.getScene().getWindow();
		
		StringBuilder text = new StringBuilder("Latency of network in ms \n\nLast 60 minutes: \n");
		for (long l : latencyData.getMinuteQueue()) {
			text.append(" " + l);
		}
		text.append("\n\nLast 24 hours: \n");
		for (long l : latencyData.getHourQueue()) {
			text.append(" " + l);
		}
		text.append("\n\nLast 30 days: \n");
		for (long l : latencyData.getDayQueue()) {
			text.append(" " + l);
		}
		
		final Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(20);
        dialogVbox.getChildren().add(new Text(text.toString()));
        Scene dialogScene = new Scene(dialogVbox, 500, 200);
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
}
