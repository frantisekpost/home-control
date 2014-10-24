package com.fannysoft.homecontrol.agents;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.fannysoft.homecontrol.queue.AgentRepo;

@Component
public class ServerDefaultAgentsManager implements InitializingBean {

	AgentRepo agentRepo = AgentRepo.getInstance();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		//TODO id should be set by repository
//		NetworkLatencyDataProvider latency = new NetworkLatencyDataProvider("network latency", "measures network latency ");
//		ServerDateProvider date = new ServerDateProvider();
	}

}
