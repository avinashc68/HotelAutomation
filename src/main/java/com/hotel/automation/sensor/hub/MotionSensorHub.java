package com.hotel.automation.sensor.hub;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.hotel.automation.entity.Motion;
import com.hotel.automation.sensor.Sensor;

/**
 * The Object acts as a Hub for events. Intentionally avoiding elaborate implementation.
 * Every time we parse the sensor input and set the motion object inside this class, all the observers are notified.
 * It listens to sensor events and delegates to respective DataControllers
 * @author Avinash
 */
public class MotionSensorHub extends Observable implements SensorHub {

	public MotionSensorHub(List<Sensor> sensors) {
		super();
		for (Sensor  sensor : sensors)
			sensor.registerForEvents(this);;
	}

	@Override
	public void update(Observable o, Object motion) {
		processEvent(motion);
	}

	@Override
	public void processEvent(Object motion) {
		setChanged();
		notifyObservers((Motion) motion);
	}

	@Override
	public void registerController(Observer observer) {
		addObserver(observer);
	}

}
