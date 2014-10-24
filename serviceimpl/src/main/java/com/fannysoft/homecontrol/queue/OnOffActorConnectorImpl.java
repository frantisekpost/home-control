package com.fannysoft.homecontrol.queue;

import com.fannysoft.homecontrol.agent.OnOffActor;
import com.fannysoft.homecontrol.config.BrokerConfiguration;

public class OnOffActorConnectorImpl extends AgentConnectorImpl<OnOffActor> {

	public OnOffActorConnectorImpl(BrokerConfiguration agentConfiguration, OnOffActor agent) {
		super(agentConfiguration, agent);
	}

	@Override
	void acceptMessage(String message) {
		super.acceptMessage(message);
		//TODO get command and apply it to the registered agent
	}
	
}
