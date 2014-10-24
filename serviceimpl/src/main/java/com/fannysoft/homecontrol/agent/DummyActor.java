package com.fannysoft.homecontrol.agent;

import java.io.Serializable;

public class DummyActor extends AbstractAgent implements OnOffActor, Serializable {

	private static final long serialVersionUID = 1L;

	private OnOffState state = OnOffState.OFF;
	
	public DummyActor() {
	}
	
	public DummyActor(String name, String description, int id) {
		super(name, description, id);
	}
	
	public boolean setState(OnOffState state) {
		this.state = state;
		switch (state) {
		case ON:
			System.out.println(getName() + " set to on");
			return true;
		case OFF:
			System.out.println(getName() + " set to off");
			return true;
		default:
			return false;
		}
	}

	@Override
	public OnOffState getState() {
		return state;
	}
}
