package com.fannysoft.homecontrol.config;

public class ImplicitBrokerConfiguration implements BrokerConfiguration {

	private String host;
	private int port;
	private String user;
	private String password;
	private String consumeDestination;
	private String publishDestination;
	private int keepaliveDelay;
	
	@Override
	public String getHost() {
		return host;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public String getUser() {
		return user;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public long getKeepaliveDelay() {
		return keepaliveDelay;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setKeepaliveDelay(int keepaliveDelay) {
		this.keepaliveDelay = keepaliveDelay;
	}

	public String getConsumeDestination() {
		return consumeDestination;
	}

	public void setConsumeDestination(String consumeDestination) {
		this.consumeDestination = consumeDestination;
	}

	public String getPublishDestination() {
		return publishDestination;
	}

	public void setPublishDestination(String publishDestination) {
		this.publishDestination = publishDestination;
	}
	
}
