package com.fannysoft.homecontrol.queue;

import com.fannysoft.homecontrol.agent.Agent;

public interface AgentConnector<T extends Agent> {

	void sendKeepalive();
	
	void connectAgent(T agent);
	
}
