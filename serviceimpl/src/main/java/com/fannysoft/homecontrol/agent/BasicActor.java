package com.fannysoft.homecontrol.agent;

import java.io.Serializable;

import com.fannysoft.homecontrol.config.BrokerConfiguration;
import com.fannysoft.homecontrol.queue.OnOffActorConnectorImpl;

public class BasicActor extends AbstractAgent implements OnOffActor, Serializable {

	private static final long serialVersionUID = 1L;

	private OnOffState state = OnOffState.OFF;
	
	private OnOffActorConnectorImpl agentConnector;
	
	public BasicActor() {
	}
	
	public BasicActor(String name, String description) {
		super(name, description);
	}
	
	public boolean setState(OnOffState state) {
		this.state = state;
		System.out.println(getName() + " set state to " + state.name());
		return true;
	}

	@Override
	public OnOffState getState() {
		return state;
	}
	
	public void initConnector(BrokerConfiguration agentConfiguration) {
		agentConnector = new OnOffActorConnectorImpl(agentConfiguration, this);
		agentConnector.connectAgent(this);
	}
}
