package com.fannysoft.homecontrol.config;

public class ImplicitAgentConfiguration implements AgentConfiguration {

	private String host;
	private int port;
	private String user;
	private String password;
	private String destination;
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
	public String getDestination() {
		return destination;
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

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public void setKeepaliveDelay(int keepaliveDelay) {
		this.keepaliveDelay = keepaliveDelay;
	}
	
	

}
