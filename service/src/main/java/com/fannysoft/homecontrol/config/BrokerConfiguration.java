package com.fannysoft.homecontrol.config;

public interface BrokerConfiguration {

	String getHost();

	int getPort();

	String getUser();

	String getPassword();

	String getConsumeDestination();

	String getPublishDestination();

	long getKeepaliveDelay();

}
