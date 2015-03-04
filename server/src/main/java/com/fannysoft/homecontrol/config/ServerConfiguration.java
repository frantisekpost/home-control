package com.fannysoft.homecontrol.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerConfiguration {

	@Bean
	BrokerConfiguration brokerConfiguration() {
		ImplicitBrokerConfiguration brokerConfiguration = new ImplicitBrokerConfiguration();
		brokerConfiguration.setConsumeDestination(Destinations.SERVER_REGISTRATION_PATH);
		brokerConfiguration.setPublishDestination(Destinations.AGENT_CONTROL_PATH);
//		brokerConfiguration.setHost("192.168.111.132");
		brokerConfiguration.setHost("localhost");
		brokerConfiguration.setPassword("password");
		brokerConfiguration.setPort(1883);
		brokerConfiguration.setUser("admin");
		
		return brokerConfiguration;
	}
	
}
