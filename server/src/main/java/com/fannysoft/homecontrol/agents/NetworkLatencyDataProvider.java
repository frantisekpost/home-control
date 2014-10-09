package com.fannysoft.homecontrol.agents;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.fannysoft.homecontrol.agent.AbstractAgent;
import com.fannysoft.homecontrol.agent.PeriodicAcquireDataProvider;
import com.fannysoft.homecontrol.data.DataType;
import com.fannysoft.homecontrol.data.LatencyDTO;
import com.fannysoft.homecontrol.util.LatencyChecker;

public class NetworkLatencyDataProvider extends AbstractAgent implements PeriodicAcquireDataProvider {

	private long period;
	
	private Timer timer;
	
	private TimerTask task;
	
	private List<Integer> minuteQueue;
	private List<Integer> hourQueue;
	private List<Integer> dayQueue;
	
	private int minuteCounter = 0;

	private int hourCounter;
	
	private static final int minuteQueueCapacity = 60;
	private static final int hourQueueCapacity = 24;
	private static final int dayQueueCapacity = 30;
	
	private static final long DEFAULT_PERIOD = 60000;
//	private static final long DEFAULT_PERIOD = 1;
	
	private static final LatencyChecker latencyChecker = LatencyChecker.getInstance();
	
	public NetworkLatencyDataProvider(String name, String description, int id) {
		super(name, description, id);

		task = new TimerTask() {
			
			@Override
			public void run() {
				int latency = (int)getLatency();
				addLatencyToMinuteQueue(latency);
			}
			
		};
		
		minuteQueue = new ArrayList<>();
		hourQueue = new ArrayList<>();
		dayQueue = new ArrayList<>();
		
		setPeriod(DEFAULT_PERIOD);
	}

	@Override
	public LatencyDTO getData() {
		return new LatencyDTO(minuteQueue, hourQueue, dayQueue);
	}

	@Override
	public DataType getDataType() {
		return DataType.LATENCY_DATA;
	}

	@Override
	public long getPeriod() {
		return period;
	}

	@Override
	public void setPeriod(long period) {
		this.period = period;
		if (timer != null) {
			stopTimer();
		} 
		startTimer();
	}

	private long getLatency() {
		return latencyChecker.doCheck();
	}
	
	private int computeAverage(List<Integer> queue, int count) {
		long sum = 0;
		for (Integer value : queue) {
			sum += value;
		}
		
		return (int)(sum / count);
	}
	
	private void addLatencyToMinuteQueue(int latency) {
		System.out.println("minute " + latency);
		minuteCounter++;
		if (minuteCounter == minuteQueueCapacity) {
			addLatencyToHourQueue(computeAverage(minuteQueue, minuteQueueCapacity));
			minuteCounter = 0;
		}
		
		minuteQueue.add(latency);
		if (minuteQueue.size() > minuteQueueCapacity) {
			minuteQueue.remove(0);
		}
	}
	
	private void addLatencyToHourQueue(int latency) {
		hourCounter++;
		if (hourCounter == hourQueueCapacity) {
			addLatencyToDayQueue(computeAverage(hourQueue, hourQueueCapacity));
			hourCounter = 0;
		}
		
		hourQueue.add(latency);
		if (hourQueue.size() > hourQueueCapacity) {
			hourQueue.remove(0);
		}
	}

	private void addLatencyToDayQueue(int latency) {
		System.out.println("day " + latency);
		dayQueue.add(latency);
		if (dayQueue.size() > dayQueueCapacity) {
			dayQueue.remove(0);
		}
	}

	private void stopTimer() {
		timer.cancel();
		timer = null;
	}
	
	private void startTimer() {
		timer = new Timer(getName()+"timer");
		timer.schedule(task, 0, period);
	}
	
}
