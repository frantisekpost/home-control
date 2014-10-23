package com.fannysoft.homecontrol.config;

public interface AgentConfiguration {

	String getHost();

	int getPort();

	String getUser();

	String getPassword();

	String getDestination();

	long getKeepaliveDelay();

}
