package com.fannysoft.homecontrol.data;

import java.util.List;

public class LatencyDTO {

	private List<Integer> minuteQueue;
	private List<Integer> hourQueue;
	private List<Integer> dayQueue;
	
	public LatencyDTO(List<Integer> minuteQueue, List<Integer> hourQueue,
			List<Integer> dayQueue) {
		super();
		this.minuteQueue = minuteQueue;
		this.hourQueue = hourQueue;
		this.dayQueue = dayQueue;
	}

	public List<Integer> getMinuteQueue() {
		return minuteQueue;
	}

	public List<Integer> getHourQueue() {
		return hourQueue;
	}

	public List<Integer> getDayQueue() {
		return dayQueue;
	}
	
}
