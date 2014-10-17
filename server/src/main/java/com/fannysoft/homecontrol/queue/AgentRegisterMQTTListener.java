package com.fannysoft.homecontrol.queue;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.Callback;
import org.fusesource.mqtt.client.CallbackConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;
import org.fusesource.mqtt.client.Topic;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class AgentRegisterMQTTListener implements InitializingBean {

	String user = "admin";
    String password = "password";
    String host = "localhost";
    int port = 1883;
    final String destination = "/topic/register";
	
	@Override
	public void afterPropertiesSet() throws Exception {
		startListener();
	}

	private void startListener() throws Exception {
		MQTT mqtt = new MQTT();
        mqtt.setHost(host, port);
        mqtt.setUserName(user);
        mqtt.setPassword(password);
        final CallbackConnection connection = mqtt.callbackConnection();
        
        connection.listener(new org.fusesource.mqtt.client.Listener() {
            public void onConnected() {
            	System.out.println("onConnected");
            }
            public void onDisconnected() {
            	System.out.println("onDisconnected");
            }
            public void onFailure(Throwable value) {
                System.out.println("onFailure");
            }
            public void onPublish(UTF8Buffer topic, Buffer msg, Runnable ack) {
            	System.out.println("onPublish");
                String body = msg.utf8().toString();
                
                
                System.out.println("received message " + body);
                ack.run();
            }
        });
        
        connection.connect(new Callback<Void>() {
            @Override
            public void onSuccess(Void value) {
                Topic[] topics = {new Topic(destination, QoS.EXACTLY_ONCE)};
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
}
