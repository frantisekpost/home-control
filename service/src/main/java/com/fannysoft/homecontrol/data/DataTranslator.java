package com.fannysoft.homecontrol.data;

public interface DataTranslator<T> {

	T translate(Object data);
	
}
