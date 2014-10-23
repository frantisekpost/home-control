package com.fannysoft.homecontrol.queue;

import java.net.URISyntaxException;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;

import com.fannysoft.homecontrol.config.BrokerConfiguration;

public abstract class AbstractMessageConsumer {

    protected MQTT mqtt;
	
	void init(final BrokerConfiguration agentConfiguration) throws URISyntaxException {
		mqtt = new MQTT();
        mqtt.setHost(agentConfiguration.getHost(), agentConfiguration.getPort());
		mqtt.setUserName(agentConfiguration.getUser());
		mqtt.setPassword(agentConfiguration.getPassword());
        
        final CallbackConnection connection = mqtt.callbackConnection();
        
        connection.listener(new org.fusesource.mqtt.client.Listener() {
            
        	public void onConnected() {
            }
            
        	public void onDisconnected() {
            }
            
        	public void onFailure(Throwable value) {
        		value.printStackTrace();
            }
            
        	public void onPublish(UTF8Buffer topic, Buffer msg, Runnable ack) {
                String body = msg.utf8().toString();
                acceptMessage(body);
                ack.run();
            }
        	
        });
        
        connection.connect(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                Topic[] topics = {new Topic(agentConfiguration.getConsumeDestination(), QoS.EXACTLY_ONCE)};
                connection.subscribe(topics, new Callback<byte[]>() {
                    
                	public void onSuccess(byte[] qoses) {
                    }
                    
                	public void onFailure(Throwable value) {
                        value.printStackTrace();
                    }
                });
            }
            
            @Override
            public void onFailure(Throwable value) {
                value.printStackTrace();
            }
            
        });
	}
	
	abstract void acceptMessage(String message);
	
}
