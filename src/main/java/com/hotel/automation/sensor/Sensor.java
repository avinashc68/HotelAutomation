package com.hotel.automation.sensor;

import java.util.Observer;

public interface Sensor {
	void monitor();
	void generateEvent(boolean IsMotionDetected);
	void registerForEvents(Observer listener);
}
