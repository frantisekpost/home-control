package com.fannysoft.homecontrol.agent.demo.actor;

import com.fannysoft.homecontrol.agent.OnOffActor;
import com.fannysoft.homecontrol.agent.OnOffState;
import com.fannysoft.homecontrol.config.AgentConfiguration;
import com.fannysoft.homecontrol.queue.OnOffActorConnectorImpl;

public class DemoActorAgent implements OnOffActor {

	private OnOffState state;
	
	private OnOffActorConnectorImpl agentConnector;
	
	public DemoActorAgent(AgentConfiguration agentConfiguration) {
		agentConnector = new OnOffActorConnectorImpl(agentConfiguration);
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
	public int getId() {
		return 0;
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

}
