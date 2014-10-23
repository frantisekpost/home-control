package com.fannysoft.homecontrol.agent.demo.actor;

import com.fannysoft.homecontrol.config.ImplicitAgentConfiguration;

public class DemoAgentStarter {

	public static void main(String[] args) throws InterruptedException {
		ImplicitAgentConfiguration agentConfiguration = new ImplicitAgentConfiguration();
		agentConfiguration.setDestination("/topic/register");
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
