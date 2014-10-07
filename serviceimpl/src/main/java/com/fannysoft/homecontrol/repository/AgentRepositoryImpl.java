package com.fannysoft.homecontrol.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fannysoft.homecontrol.agent.Agent;
import com.fannysoft.homecontrol.agent.DummyActor;

@Repository
public class AgentRepositoryImpl implements AgentRepository {

	private static List<Agent> agents = new ArrayList<>();
	
	static {
		agents.add(new DummyActor("dummy actor 1", "lorem ipsum", 1));
		agents.add(new DummyActor("dummy actor 2", "dolor sit amet", 2));
	}
	
	@Override
	public Agent getAgent(long id) {
		Agent a = null;
		for (Agent agent : agents) {
			if (id == agent.getId()) {
				a = agent;
				break;
			}
		}
		return a;
	}

	@Override
	public List<Agent> getAgents() {
		return agents;
	}

}
