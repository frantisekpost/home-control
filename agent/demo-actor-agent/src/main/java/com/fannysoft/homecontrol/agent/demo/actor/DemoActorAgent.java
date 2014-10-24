package com.fannysoft.homecontrol.agent.demo.actor;

import java.util.UUID;

import com.fannysoft.homecontrol.agent.OnOffActor;
import com.fannysoft.homecontrol.agent.OnOffState;
import com.fannysoft.homecontrol.config.BrokerConfiguration;
import com.fannysoft.homecontrol.queue.OnOffActorConnectorImpl;

public class DemoActorAgent implements OnOffActor {

	private OnOffState state = OnOffState.OFF;
	
	private OnOffActorConnectorImpl agentConnector;
	
	Integer id;
	
	private long uuid = UUID.randomUUID().getMostSignificantBits();
	
	public DemoActorAgent(BrokerConfiguration agentConfiguration) {
		agentConnector = new OnOffActorConnectorImpl(agentConfiguration, this);
		agentConnector.connectAgent(this);
	}
	
	@Override
	public String getName() {
		return "Demo actor agent";
	}

	@Override
	public String getDescription() {
		return "Prints received commands to console";
	}

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public boolean setState(OnOffState state) {
		System.out.println("State set to " + state);
		this.state = state;
		return true;
	}

	@Override
	public OnOffState getState() {
		return state;
	}

	@Override
	public long getUuid() {
		return uuid;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
}
