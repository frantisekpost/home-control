package com.fannysoft.homecontrol.agent;

import com.fannysoft.homecontrol.data.DataType;

public interface DataProvider extends Agent {

	Object getData();
	
	DataType getDataType();
	
}
