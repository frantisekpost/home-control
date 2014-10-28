package com.fannysoft.homecontrol.queue;

import com.fannysoft.homecontrol.agent.OnOffActor;
import com.fannysoft.homecontrol.agent.OnOffState;

public class RemoteServerAgent extends AbstractServerAgent implements OnOffActor {

	private AgentRegistrator agentRegistrator;
	
	public AgentRegistrator getAgentRegistrator() {
		return agentRegistrator;
	}

	public void setAgentRegistrator(AgentRegistrator agentRegistrator) {
		this.agentRegistrator = agentRegistrator;
	}

	@Override
	public void sendState(OnOffState state) {
		setState(state);
		agentRegistrator.sendMessage(getUuid() + "#" + state.name());
	}
	
}
