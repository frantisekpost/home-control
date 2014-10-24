package com.fannysoft.homecontrol.queue;

import java.io.IOException;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fannysoft.homecontrol.agent.Agent;
import com.fannysoft.homecontrol.agent.DummyActor;
import com.fannysoft.homecontrol.config.BrokerConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AgentRegistrator extends AbstractMessageTransporter implements InitializingBean {

	@Autowired
	private BrokerConfiguration brokerConfiguration;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		init(brokerConfiguration);
	}

	private AgentRepo agentRepo = AgentRepo.getInstance();
	
	@Override
	void acceptMessage(String message) {
		System.out.println("AgentRegistrator received message " + message);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		Agent agent = null;
		try {
			agent = objectMapper.readValue(message, DummyActor.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (agent != null) {
			if (agent.getId() == null) {
				Integer id = agentRepo.putAgent(agent);
				String response = agent.getUuid() + ";" + id;
				sendMessage(response);
			} 
		}
		
	}
	
}
