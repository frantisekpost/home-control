package com.fannysoft.homecontrol.queue;

import com.fannysoft.homecontrol.agent.OnOffActor;
import com.fannysoft.homecontrol.agent.OnOffState;

public interface OnOffActorConnector<T extends OnOffActor> extends ActorConnector<T> {

	void setState(OnOffState state);
	
}
