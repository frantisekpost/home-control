package com.fannysoft.homecontrol.queue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fannysoft.homecontrol.agent.Agent;

public class AgentRepo {

	private static final AgentRepo instance = new AgentRepo();
	
	private AgentRepo() {
	}
	
	private Map<Integer, Agent> agentMap = new LinkedHashMap<>();
	
	private Integer idSequence = 0;
	
	public Agent getAgent(Integer id) {
		return agentMap.get(id);
	}
	
	public Integer putAgent(Agent agent) {
		idSequence++;
		agent.setId(idSequence);
		agentMap.put(idSequence, agent);
		return idSequence;
	}
	
	public static AgentRepo getInstance() {
		return instance;
	}
	
	public List<Agent> getAllAgents() {
		List<Agent> agents = new ArrayList<>();
		agents.addAll(agentMap.values());
		return agents;
	}
	
}
