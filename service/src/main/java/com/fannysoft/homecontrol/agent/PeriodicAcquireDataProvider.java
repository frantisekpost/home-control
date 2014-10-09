package com.fannysoft.homecontrol.agent;

public interface PeriodicAcquireDataProvider extends DataProvider {
	
	long getPeriod();
	
	void setPeriod(long period);

}
