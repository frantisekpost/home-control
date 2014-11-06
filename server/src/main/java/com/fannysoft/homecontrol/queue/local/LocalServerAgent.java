package com.fannysoft.homecontrol.queue.local;

import com.fannysoft.homecontrol.agent.Agent;
import com.fannysoft.homecontrol.agent.OnOffState;
import com.fannysoft.homecontrol.queue.ServerAgent;

public class LocalServerAgent<T extends Agent> implements ServerAgent {

	protected T agent;
	
	public LocalServerAgent(T agent) {
		this.agent = agent;
	}
	
	public T getActor() {
		return agent;
	}
	
	@Override
	public void sendState(OnOffState state) {
	}

	@Override
	public String getDescription() {
		return agent.getDescription();
	}
	
	@Override
	public Integer getId() {
		return agent.getId();
	}
	
	@Override
	public String getName() {
		return agent.getName();
	}
	
	@Override
	public long getUuid() {
		return agent.getUuid();
	}
	
	@Override
	public void setId(Integer id) {
		agent.setId(id);
	}
	
	@Override
	public void setDescription(String description) {
		agent.setDescription(description);
	}
	
	@Override
	public void setName(String name) {
		agent.setName(name);
	}

	@Override
	public void setHealth(int health) {
		//noop
	}

	@Override
	public int getHealth() {
		return 0;
	}
	
}
