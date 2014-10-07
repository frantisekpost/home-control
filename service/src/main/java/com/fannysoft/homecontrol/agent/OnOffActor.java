package com.fannysoft.homecontrol.agent;

public interface OnOffActor extends Actor {

	boolean setState(OnOffState state);
	
	OnOffState getState();
	
}
