package com.fannysoft.homecontrol.queue;

import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import com.fannysoft.homecontrol.agent.Agent;
import com.fannysoft.homecontrol.config.BrokerConfiguration;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//TODO during registration ticket, if agent has no id, it sends its UUID. server receives message and assing an id to this UUID.\
//client receives all responses, so it recognises its message with and UUID in the server repsonse with the new id.
public class AgentConnectorImpl<T extends Agent> extends AbstractMessageTransporter implements AgentConnector<T> {

	private Timer keepaliveTimer;
	
	protected T connectedAgent;
	
	public AgentConnectorImpl(BrokerConfiguration agentConfiguration, T agent) {
		this.connectedAgent = agent;
		try {
			init(agentConfiguration);
		} catch (URISyntaxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		keepaliveTimer = new Timer();
		keepaliveTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				sendKeepalive();
			}
			
		}, 0, agentConfiguration.getKeepaliveDelay());
	}

	@Override
	public void sendKeepalive() {
		sendMessage(createKeepaliveMessage());
	}
	
	protected String createKeepaliveMessage() {
		ObjectMapper objectMapper = new ObjectMapper();
		String message = null;
		try {
			message = objectMapper.writeValueAsString(connectedAgent);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}

	@Override
	public void connectAgent(T agent) {
		this.connectedAgent = agent;
	}

	@Override
	void acceptMessage(String message) {
		String expectedMessage = connectedAgent.getUuid() + ";";
		if (message.startsWith(expectedMessage)) {
			int index = message.indexOf(";");
			if (index != -1) {
				String idSubstring = message.substring(index+1);
				int id = Integer.parseInt(idSubstring);
				System.out.println("id : " + id);
				connectedAgent.setId(id);
			}
			System.out.println("Agent " + connectedAgent.getUuid() + " " + connectedAgent.getId() + " + received message " + message);
		} else {
			System.out.println(message);
		}
	}

}
