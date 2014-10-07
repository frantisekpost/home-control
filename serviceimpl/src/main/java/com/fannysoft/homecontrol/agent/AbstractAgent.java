package com.fannysoft.homecontrol.agent;

public class AbstractAgent implements Agent {

	private String name;
	
	private int id;
	
	private String description;

	public AbstractAgent(String name, String description, int id) {
		this.name = name;
		this.description = description;
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String getDescription() {
		return description;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public int getId() {
		return id;
	}

}
