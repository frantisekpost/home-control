package com.fannysoft.homecontrol.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fannysoft.homecontrol.agent.Agent;
import com.fannysoft.homecontrol.agent.DummyActor;

@Repository
public class AgentRepositoryImpl implements AgentRepository {

	@Override
	public Agent getAgent(long id) {
		return new DummyActor("Actor " + id);
	}

	@Override
	public List<Agent> getAgents() {
		List<Agent> agents = new ArrayList<>();
		agents.add(new DummyActor("dummy actor"));
		return null;
	}

}
