package com.fannysoft.homecontrol.agents;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.fannysoft.homecontrol.queue.AgentRepo;
import com.fannysoft.homecontrol.queue.local.LocalServerDataProvider;

@Component
public class ServerDefaultAgentsManager implements InitializingBean {

	AgentRepo agentRepo = AgentRepo.getInstance();
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		NetworkLatencyDataProvider networkLatencyDataProvider = new NetworkLatencyDataProvider("Network checker", "Measures network latency");
		LocalServerDataProvider latencyDataProvider = new LocalServerDataProvider(networkLatencyDataProvider);
		Integer id = agentRepo.putAgent(latencyDataProvider);
		latencyDataProvider.setId(id);
		
		ServerDateProvider serverdateProvider = new ServerDateProvider();
		LocalServerDataProvider dateProvider = new LocalServerDataProvider(serverdateProvider);
		id = agentRepo.putAgent(dateProvider);
		dateProvider.setId(id);
	}

}
