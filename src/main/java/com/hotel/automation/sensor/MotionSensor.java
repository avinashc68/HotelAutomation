package com.hotel.automation.sensor;

import java.util.Observable;
import java.util.Observer;

import com.hotel.automation.entity.Motion;

public class MotionSensor extends Observable  implements Sensor {
	private int floorNumber;
	private int subCorridorNumber;

	public MotionSensor(int floorNumber, int subCorridorNumber) {
		super();
		this.floorNumber = floorNumber;
		this.subCorridorNumber = subCorridorNumber;
	}

	@Override
	public void generateEvent(boolean IsMotionDetected) {
		//monitor() usually sets a motion object if it detects something/nothing for a specified amount of time
		Motion motion  = new Motion(floorNumber, subCorridorNumber, IsMotionDetected);
		setChanged();
		notifyObservers(motion);
	}

	@Override
	public void monitor() {
		//Do some monitoring stuff here and raise an event when we detect something	
		//calls generateEvent(). I have omitted the logic for detecting motion or lack of motion
		generateEvent(true);
	}

	@Override
	public void registerForEvents(Observer listener) {
		addObserver(listener);
	}


}
