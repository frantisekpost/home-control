package com.fannysoft.homecontrol.queue;

import com.fannysoft.homecontrol.agent.Agent;
import com.fannysoft.homecontrol.agent.OnOffState;

public class ServerAgent implements Agent {

	private AgentRegistrator agentRegistrator;
	
	private OnOffState state = OnOffState.OFF;
	
	private String name;
	
	private Integer id;
	
	private String description;
	
	private long uuid;

	public AgentRegistrator getAgentRegistrator() {
		return agentRegistrator;
	}

	public void setAgentRegistrator(AgentRegistrator agentRegistrator) {
		this.agentRegistrator = agentRegistrator;
	}

	public OnOffState getState() {
		return state;
	}

	public void setState(OnOffState state) {
		this.state = state;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getUuid() {
		return uuid;
	}

	public void setUuid(long uuid) {
		this.uuid = uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (uuid ^ (uuid >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServerAgent other = (ServerAgent) obj;
		if (uuid != other.uuid)
			return false;
		return true;
	}
	
	public void sendState(OnOffState state) {
		this.state = state;
		agentRegistrator.sendMessage(uuid + "#" + state.name());
	}
	
}
