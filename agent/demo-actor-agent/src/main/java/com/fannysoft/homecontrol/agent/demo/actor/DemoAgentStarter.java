package com.fannysoft.homecontrol.agent.demo.actor;


import java.util.Date;

import com.fannysoft.homecontrol.agent.BasicActor;
import com.fannysoft.homecontrol.config.Destinations;
import com.fannysoft.homecontrol.config.ImplicitBrokerConfiguration;

public class DemoAgentStarter {

	public static void main(String[] args) throws InterruptedException {
		ImplicitBrokerConfiguration brokerConfiguration = new ImplicitBrokerConfiguration();
		brokerConfiguration.setPublishDestination(Destinations.SERVER_REGISTRATION_PATH);
		brokerConfiguration.setConsumeDestination(Destinations.AGENT_CONTROL_PATH);
		brokerConfiguration.setHost("localhost");
		brokerConfiguration.setKeepaliveDelay(15000); //send keepalive every 15 seconds
		brokerConfiguration.setPassword("password");
		brokerConfiguration.setPort(1883);
		brokerConfiguration.setUser("admin");
		
		BasicActor actor = new BasicActor("Basic actor " + new Date(), "Actor, printing commands to console");
		actor.initConnector(brokerConfiguration);
				
		synchronized (actor) {
			actor.wait();
		}
	}
	
}
