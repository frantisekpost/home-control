package com.fannysoft.homecontrol.agent;

import java.io.Serializable;

public class DummyActor implements OnOffActor, Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	
	public DummyActor(String name) {
		this.name = name;
	}
	
	public boolean setState(OnOffState state) {
		switch (state) {
		case ON:
			System.out.println(name + " set to on");
			return true;
		case OFF:
			System.out.println(name + " set to off");
			return true;
		default:
			return false;
		}
	}

	@Override
	public String getName() {
		return name;
	}

}
