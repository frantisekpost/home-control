package com.fannysoft.homecontrol.queue;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fannysoft.homecontrol.config.BrokerConfiguration;

@Component
public class AgentRegistrator extends AbstractMessageTransporter implements InitializingBean {

	@Autowired
	private BrokerConfiguration brokerConfiguration;
	
	@Override
	public void afterPropertiesSet() throws Exception {
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
