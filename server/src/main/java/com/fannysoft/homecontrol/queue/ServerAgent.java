package com.fannysoft.homecontrol.queue;

import com.fannysoft.homecontrol.agent.Agent;
import com.fannysoft.homecontrol.agent.OnOffState;

public interface ServerAgent extends Agent {

	void sendState(OnOffState state);
	
}
