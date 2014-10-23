package com.fannysoft.homecontrol.queue;

import java.net.URISyntaxException;

import org.fusesource.hawtbuf.AsciiBuffer;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.QoS;

import com.fannysoft.homecontrol.config.BrokerConfiguration;

public abstract class AbstractMessageTransporter extends AbstractMessageConsumer {

	private UTF8Buffer topic;
	
	private FutureConnection sendConnection;
	
	@Override
	void init(BrokerConfiguration brokerConfiguration) throws URISyntaxException {
		super.init(brokerConfiguration);
		
		sendConnection = mqtt.futureConnection();
		try {
			sendConnection.connect().await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        topic = new UTF8Buffer(brokerConfiguration.getPublishDestination());
	}
	
	public void sendMessage(String message) {
		Buffer msg = new AsciiBuffer(message);
        sendConnection.publish(topic, msg, QoS.EXACTLY_ONCE, false);
	}
	
}
