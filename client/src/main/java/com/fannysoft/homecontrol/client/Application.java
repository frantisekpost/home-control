package com.fannysoft.homecontrol.client;

import java.awt.GridLayout;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.web.client.RestTemplate;


public class Application {

//	public static final String SERVER_URI = "http://localhost:8080/server";
	public static final String SERVER_URI = "http://192.168.111.50:8123/server";
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		RestTemplate restTemplate = new RestTemplate();
		List<LinkedHashMap<?, ?>> agents = restTemplate.getForObject(SERVER_URI + "/agents", List.class);
		
		JFrame f = new JFrame();
		f.setTitle("Home control");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		f.setContentPane(panel);
		panel.setLayout(new GridLayout(agents.size(), 1));
		
		System.out.println("connected agents");
		for (LinkedHashMap<?, ?> map : agents) {
			for (Entry<?, ?> entry : map.entrySet()) {
				System.out.print(entry.getKey() + ": " + entry.getValue() + "  ");
			}
			System.out.println();
			
			panel.add(ComponentFactory.createComponent(map));
		}
//		for (Agent agent : agents) {
//			System.out.println("agent: " + agent.getName() + "  " + agent.getDescription());
//		}
		
		f.pack();
		f.setVisible(true);
	}
	
}
