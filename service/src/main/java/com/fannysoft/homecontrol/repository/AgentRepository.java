package com.fannysoft.homecontrol.repository;

import java.util.List;

import com.fannysoft.homecontrol.agent.Agent;

public interface AgentRepository {

	Agent getAgent(long id);
	
	List<Agent> getAgents();
	
}
