package com.fannysoft.homecontrol.queue;

import java.net.URISyntaxException;
import java.util.Timer;
import java.util.TimerTask;

import com.fannysoft.homecontrol.agent.Agent;
import com.fannysoft.homecontrol.config.BrokerConfiguration;

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
		//TODO gether information from agent and send it as a keepalive message
		return connectedAgent.getName() + " reporting to service ";
	}

	@Override
	public void connectAgent(T agent) {
		this.connectedAgent = agent;
	}

	@Override
	void acceptMessage(String message) {
		System.out.println("Agent received message " + message);
	}

}
