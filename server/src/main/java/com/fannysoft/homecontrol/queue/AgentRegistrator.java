package com.fannysoft.homecontrol.queue;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.fannysoft.homecontrol.config.Destinations;
import com.fannysoft.homecontrol.config.ImplicitBrokerConfiguration;

@Component
public class AgentRegistrator extends AbstractMessageTransporter implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		ImplicitBrokerConfiguration brokerConfiguration = new ImplicitBrokerConfiguration();
		brokerConfiguration.setConsumeDestination(Destinations.SERVER_REGISTRATION_PATH);
		brokerConfiguration.setPublishDestination(Destinations.AGENT_CONTROL_PATH);
		brokerConfiguration.setHost("localhost");
		brokerConfiguration.setPassword("password");
		brokerConfiguration.setPort(1883);
		brokerConfiguration.setUser("admin");
		
		init(brokerConfiguration);
	}

	@Override
	void acceptMessage(String message) {
		System.out.println("AgentRegistrator received message " + message);
		respondToClient();
	}
	
	private void respondToClient() {
		sendMessage("Hello agent, this is AgentRegistrator");
	}

}
