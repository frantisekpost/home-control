package com.fannysoft.homecontrol.agents;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fannysoft.homecontrol.repository.AgentRepository;

@Component
public class ServerDefaultAgentsManager implements InitializingBean {

	@Autowired
	AgentRepository agentRepository;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		//TODO id should be set by repository
		NetworkLatencyDataProvider latency = new NetworkLatencyDataProvider("network latency", "measures network latency ", 10);
		ServerDateProvider date = new ServerDateProvider(11);
		agentRepository.addAgent(latency);
		agentRepository.addAgent(date);
	}

}
