package com.fannysoft.homecontrol.queue;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Timer;
import java.util.TimerTask;

public class AgentRepo {

	private static final AgentRepo instance = new AgentRepo();
	
	private static final int PERIOD = 60000;
	
	private static final int MAX_INACTIVE_TIME = 2; //int minutes (2 * PERIOD) ms
	
	private AgentRepo() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				checkHealth();
			}
		}, 0, PERIOD);
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

	private synchronized void checkHealth() {
		List<Integer> agentsToRemove = new ArrayList<>();
		for (Entry<Integer, ServerAgent> entry : agentMap.entrySet()) {
			int value = entry.getValue().getHealth() + 1;
			if (value >= MAX_INACTIVE_TIME) {
				agentsToRemove.add(entry.getKey());
			}
			entry.getValue().setHealth(value);
		}
		
		for (Integer id : agentsToRemove) {
			agentMap.remove(id);
		}
	}
	
	public synchronized void keepAlive(Integer id) {
		ServerAgent agent = agentMap.get(id);
		if (agent != null) {
			agent.setHealth(0);
		}
	}
	
}
