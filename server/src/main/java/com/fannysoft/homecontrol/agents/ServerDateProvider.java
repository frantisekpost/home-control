package com.fannysoft.homecontrol.agents;

import java.util.Date;

import com.fannysoft.homecontrol.agent.AbstractAgent;
import com.fannysoft.homecontrol.agent.DataProvider;
import com.fannysoft.homecontrol.data.DataType;

public class ServerDateProvider extends AbstractAgent implements DataProvider {

	public ServerDateProvider(int id) {
		super("Dummy data provider", "provides current date", id);
	}

	@Override
	public Object getData() {
		return new Date();
	}

	@Override
	public DataType getDataType() {
		return DataType.DATE;
	}

}
