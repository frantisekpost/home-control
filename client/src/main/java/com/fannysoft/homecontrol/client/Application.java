package com.fannysoft.homecontrol.client;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.web.client.RestTemplate;


public class Application {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<?, ?>> agents = restTemplate.getForObject(ComponentFactory.SERVER_URI + "/agents", List.class);
		
		JFrame f = new JFrame();
		f.setTitle("Home control");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		f.setContentPane(panel);
		panel.setLayout(new GridLayout(agents.size(), 1));
		
		for (LinkedHashMap<?, ?> map : agents) {
			panel.add(ComponentFactory.createComponent(map));
		}

		f.pack();
		f.setVisible(true);
	}
	
}
