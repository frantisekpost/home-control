package com.fannysoft.homecontrol.agent;

public interface Agent {

	String getName();
	
	String getDescription();
	
	Integer getId();
	
	void setId(Integer id);
	
	long getUuid();
	
	void setDescription(String description); 
	
	void setName(String name);
	
}
