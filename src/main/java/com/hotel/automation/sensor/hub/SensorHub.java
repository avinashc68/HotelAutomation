package com.hotel.automation.sensor.hub;

import java.util.Observer;

public interface SensorHub extends Observer{
	void processEvent(Object object);
	void registerController(Observer observer);
}
