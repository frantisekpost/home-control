package com.fannysoft.homecontrol.agent;

import java.util.Date;

public class DummyDataProvider extends AbstractAgent implements DataProvider {

	public DummyDataProvider(int id) {
		super("Dummy data provider", "provides current date", id);
	}

	@Override
	public Object getData() {
		return new Date();
	}

}
