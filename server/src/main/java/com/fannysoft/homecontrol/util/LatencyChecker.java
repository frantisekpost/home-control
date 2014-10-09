package com.fannysoft.homecontrol.util;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Checks connection availability.
 * 
 * <p>
 * It uses {@link java.net.InetAddress#getByName(java.lang.String) to identify if machine is connected to internet, and <br>
 * {@link java.net.Socket} to check, if address www.openstreetmap.org is accessible.
 * </p>
 * 
 * <p>
 * Check is running periodically in a {@link java.util.Timer} with period 5000 ms.
 * </p>
 * 
 * <p>
 * When status changes (from online to offline and vice versa) {@link java.beans.PropertyChangeListener} is fired.
 * </p>
 * 
 * @author Frantisek Post
 */
public class LatencyChecker {
    
    private static final String HOST_NAME_DEFAULT = "www.google.com";
    private static final int PORT_NUMBER_DEFAULT = 80;
    private static final LatencyChecker instance = new LatencyChecker();
    
    private String hostName;
    private int portNumber;
    
    private LatencyChecker() {
        hostName = HOST_NAME_DEFAULT;
        portNumber = PORT_NUMBER_DEFAULT;
    }
    
    public long doCheck() {
        boolean online = true;
    	long startTime = System.nanoTime();
    	long stopTime = startTime;
        if (isOnlineFastCheck()) {
            try (Socket socket = new Socket(hostName, portNumber)) {
                online = (socket.getInputStream() != null);
                stopTime = System.nanoTime();
            } catch (Throwable e) {
                online = false;
            }
        } else {
            online = false;
        }

        return online ? (stopTime-startTime)/1000000 : -1;
    }
    
    private boolean isOnlineFastCheck() {
        boolean check = false; 
        try {
            InetAddress.getByName(hostName);
            check = true;
        } catch (UnknownHostException e) {
            //noop
        }
        return check;
    }
    
    public static LatencyChecker getInstance() {
        return instance;
    }
            
    /**
     * Return hostname, that is used to checked online status
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Set hostname to check online status
     * <p>
     * To apply new settings call {@link #restart()}
     * </p>
     * @param hostName 
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Return checked port number
     * @return 
     */
    public int getPortNumber() {
        return portNumber;
    }

    /**
     * Set port number
     * <p>
     * To apply new settings call {@link #restart()}
     * </p>
     * @param portNumber 
     */
    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }
    
}
