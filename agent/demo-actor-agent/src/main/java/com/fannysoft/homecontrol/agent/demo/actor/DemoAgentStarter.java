package com.fannysoft.homecontrol.agent.demo.actor;

import com.fannysoft.homecontrol.config.Destinations;
import com.fannysoft.homecontrol.config.ImplicitBrokerConfiguration;

public class DemoAgentStarter {

	public static void main(String[] args) throws InterruptedException {
		ImplicitBrokerConfiguration agentConfiguration = new ImplicitBrokerConfiguration();
		agentConfiguration.setPublishDestination(Destinations.SERVER_REGISTRATION_PATH);
		agentConfiguration.setConsumeDestination(Destinations.AGENT_CONTROL_PATH);
		agentConfiguration.setHost("localhost");
		agentConfiguration.setKeepaliveDelay(15000); //send keepalive every 15 seconds
		agentConfiguration.setPassword("password");
		agentConfiguration.setPort(1883);
		agentConfiguration.setUser("admin");
		
		DemoActorAgent agent = new DemoActorAgent(agentConfiguration);
				
		synchronized (agent) {
			agent.wait();
		}
	}
	
}
