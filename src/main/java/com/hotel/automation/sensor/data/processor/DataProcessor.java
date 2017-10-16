package com.hotel.automation.sensor.data.processor;

import com.hotel.automation.entity.Hotel;

public interface DataProcessor {
	void processData(Object object, Hotel hotel);
}
