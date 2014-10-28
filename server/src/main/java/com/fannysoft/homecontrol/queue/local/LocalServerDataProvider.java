package com.fannysoft.homecontrol.queue.local;

import com.fannysoft.homecontrol.agent.DataProvider;
import com.fannysoft.homecontrol.data.DataType;

public class LocalServerDataProvider extends LocalServerAgent<DataProvider> implements DataProvider {

	public LocalServerDataProvider(DataProvider dataProvider) {
		super(dataProvider);
	}

	@Override
	public Object getData() {
		return agent.getData();
	}

	@Override
	public DataType getDataType() {
		return agent.getDataType();
	}
	
}
