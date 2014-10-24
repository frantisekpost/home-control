package com.fannysoft.homecontrol.queue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AgentRepo {

	private static final AgentRepo instance = new AgentRepo();
	
	private AgentRepo() {
	}
	
	private Map<Integer, ServerAgent> agentMap = new LinkedHashMap<>();
	
	private Integer idSequence = 0;
	
	public ServerAgent getAgent(Integer id) {
		return agentMap.get(id);
	}
	
	public Integer putAgent(ServerAgent agent) {
		idSequence++;
		agent.setId(idSequence);
		agentMap.put(idSequence, agent);
		return idSequence;
	}
	
	public static AgentRepo getInstance() {
		return instance;
	}
	
	public List<ServerAgent> getAllAgents() {
		List<ServerAgent> agents = new ArrayList<>();
		agents.addAll(agentMap.values());
		return agents;
	}
	
}
