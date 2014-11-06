package com.fannysoft.homecontrol.client;

import java.net.URL;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import org.springframework.web.client.RestTemplate;

public class ApplicationController implements Initializable{

	@FXML
	Button refreshButton;
	
	@FXML
	VBox mainArea;
	
	@FXML
	Label statusLabel;
	
	RestTemplate restTemplate;
	
	public ApplicationController() {
		restTemplate = new RestTemplate();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		refreshButton.setOnAction(e -> refresh());
		refresh();
		
	}
	
	@SuppressWarnings("unchecked")
	private void refresh() {
		mainArea.getChildren().clear();
		
		List<LinkedHashMap<?, ?>> agents = restTemplate.getForObject(ComponentFactory.SERVER_URI + "/agents", List.class);
		statusLabel.setText(String.format("Connected %d agents", agents.size()));
		
		for (LinkedHashMap<?, ?> map : agents) {
			mainArea.getChildren().add(ComponentFactory.createComponent(map));
		}
	}

}
