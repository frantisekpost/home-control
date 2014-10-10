package com.fannysoft.homecontrol.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;

import org.springframework.web.client.RestTemplate;

import com.fannysoft.homecontrol.agent.OnOffState;
import com.fannysoft.homecontrol.data.DataType;
import com.fannysoft.homecontrol.data.LatencyDTO;

public class ComponentFactory {

//	private static final String SERVER_URL = "http://localhost:8080/server";
	private static final String ACTOR_URL = "/actor";
	private static final String START_ACTOR_URL = "/start/";
	private static final String STOP_ACTOR_URL = "/stop/";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
	
	public static JComponent createComponent(LinkedHashMap<?, ?> agentMap) {
		int id = (Integer) agentMap.get("id");
		String name = (String) agentMap.get("name");
		String description = (String) agentMap.get("description");
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
			return new JPanel();
		}
	}
	
	private static JComponent createOnOffActor(final int id, String name, String description, OnOffState state) {
		JPanel panel = new JPanel(new GridBagLayout());
		Border b = BorderFactory.createRaisedSoftBevelBorder();
		panel.setBorder(b);
		
		JLabel nameLabel = new JLabel(name);
		JLabel descriptionLabel = new JLabel(description);
		final JToggleButton button = new JToggleButton(state.name());
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1f;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		panel.add(nameLabel, gc);
		
		gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.weightx = 1f;
		gc.weightx = 1f;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridy = 1;
		panel.add(descriptionLabel, gc);
		
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 0f;
		gc.gridheight = 2;
		panel.add(button, gc);
		
		final RestTemplate restTemplate = new RestTemplate();
		button.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (button.isSelected()) {
					restTemplate.getForObject(Application.SERVER_URI+ACTOR_URL+START_ACTOR_URL+id, String.class);
					button.setText(OnOffState.ON.name());
				} else {
					restTemplate.getForObject(Application.SERVER_URI+ACTOR_URL+STOP_ACTOR_URL+id, String.class);
					button.setText(OnOffState.OFF.name());
				}
			}
			
		});
		
		return panel;
	}
	
	@SuppressWarnings("unchecked")
	private static JComponent createDataProvider(final int id, String name, String description, DataType d, Object data) {
		JPanel panel = new JPanel(new GridBagLayout());
		Border b = BorderFactory.createRaisedSoftBevelBorder();
		panel.setBorder(b);
		
		JLabel nameLabel = new JLabel(name);
		JLabel descriptionLabel = new JLabel(description);
		JLabel valueLabel = new JLabel(description);
		
		GridBagConstraints gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.gridy = 0;
		gc.weightx = 1f;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		panel.add(nameLabel, gc);
		
		gc = new GridBagConstraints();
		gc.gridx = 0;
		gc.weightx = 1f;
		gc.weightx = 1f;
		gc.fill = GridBagConstraints.HORIZONTAL;
		gc.anchor = GridBagConstraints.LINE_START;
		gc.gridy = 1;
		panel.add(descriptionLabel, gc);
		
		gc = new GridBagConstraints();
		gc.gridx = 1;
		gc.gridy = 0;
		gc.weightx = 0f;
		gc.gridheight = 2;
		panel.add(valueLabel, gc);
		
		switch (d) {
		case DATE:
			long ts = ((Long)data).longValue();
			Date date = new Date(ts);
			valueLabel.setText(dateFormat.format(date));
			break;
		case LATENCY_DATA:
			panel.remove(valueLabel);
			JButton buttonShow = new JButton("Show data");
			panel.add(buttonShow, gc);
			
			LinkedHashMap<?,?> map = (LinkedHashMap<?, ?>) data;
			ArrayList<Integer> minuteQueue = (ArrayList<Integer>) map.get("minuteQueue");
			ArrayList<Integer> hourQueue = (ArrayList<Integer>) map.get("hourQueue");
			ArrayList<Integer> dayQueue = (ArrayList<Integer>) map.get("dayQueue");
			
			final LatencyDTO latencyData = new LatencyDTO(minuteQueue, hourQueue, dayQueue);

			buttonShow.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					showLatencyData(latencyData);
				}
			});
			
		default:
			break;
		}
		
		return panel;
	}

	protected static void showLatencyData(LatencyDTO latencyData) {
		System.out.println("last 60 minutes: ");
		for (long l : latencyData.getMinuteQueue()) {
			System.out.print(" " + l);
		}
		System.out.println("");
		System.out.println("last 24 hours: ");
		for (long l : latencyData.getHourQueue()) {
			System.out.print(" " + l);
		}
		System.out.println("");
		System.out.println("last 30 days: ");
		for (long l : latencyData.getDayQueue()) {
			System.out.print(" " + l);
		}
	}
	
}
