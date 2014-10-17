package com.fannysoft.homecontrol.queue;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import org.fusesource.hawtbuf.AsciiBuffer;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Future;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

import com.fannysoft.homecontrol.agent.Agent;
import com.fannysoft.homecontrol.config.AgentConfiguration;

public class AgentConnectorImpl<T extends Agent> implements AgentConnector<T> {

	private LinkedList<Future<Void>> queue;
	
	private UTF8Buffer topic;
	
	private FutureConnection connection;

	private Timer keepaliveTimer;
	
	protected T connectedAgent;
	
	public AgentConnectorImpl(AgentConfiguration agentConfiguration) {
		MQTT mqtt = new MQTT();
		try {
			mqtt.setHost(agentConfiguration.getHost(), agentConfiguration.getPort());
			mqtt.setUserName(agentConfiguration.getUser());
			mqtt.setPassword(agentConfiguration.getPassword());
			connection = mqtt.futureConnection();
			connection.connect().await();
			
			queue = new LinkedList<Future<Void>>();
	        topic = new UTF8Buffer(agentConfiguration.getDestination());
		} catch (Exception e) {
			e.printStackTrace();
			//TODO log exception
		}
		
		keepaliveTimer = new Timer();
		keepaliveTimer.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("sending keepalive");
				sendKeepalive();
			}
			
		}, 0, agentConfiguration.getKeepaliveDelay());
		
	}

	@Override
	public void sendKeepalive() {
    	Buffer msg = new AsciiBuffer(createKeepaliveMessage());
        queue.add(connection.publish(topic, msg, QoS.EXACTLY_ONCE, false));
	}
	
	protected String createKeepaliveMessage() {
		//TODO gether information from agent and send it as a keepalive message
		return "";
	}

	@Override
	public void connectAgent(T agent) {
		this.connectedAgent = agent;
	}

}
