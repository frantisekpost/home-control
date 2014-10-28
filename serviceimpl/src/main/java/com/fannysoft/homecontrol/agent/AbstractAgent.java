package com.fannysoft.homecontrol.agent;

import java.util.UUID;

public class AbstractAgent implements Agent {

	private String name;
	
	private Integer id;
	
	private String description;
	
	private long uuid = UUID.randomUUID().getMostSignificantBits();

	public AbstractAgent() {
	}
	
	public AbstractAgent(String name, String description) {
		this.name = name;
		this.description = description;
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

	@Override
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Override
	public Integer getId() {
		return id;
	}
	
	public long getUuid() {
		return uuid;
	}

}
