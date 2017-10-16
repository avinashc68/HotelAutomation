/**
 * 
 */
package com.hotel.automation.entity;

import java.util.Observable;
import java.util.Observer;

/**
 * It's an object representing a Human's motion in some floor/corridor. It's an
 * Observable, so such an event will be notified to all observers subscribed to
 * this event. It will also generate a event if there is no motion
 * @author Avinash
 */
public class Motion extends Observable {

	private int floorNumber;

	private int subCorridorNumber;

	private boolean isMotionDetected;

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public void setSubCorridorNumber(int subCorridorNumber) {
		this.subCorridorNumber = subCorridorNumber;
	}

	@Override
	public synchronized void addObserver(Observer o) {
		super.addObserver(o);
	}

	@Override
	public void notifyObservers() {
		this.notifyObservers(null);
	}

	@Override
	public void notifyObservers(Object object) {
		setChanged();
		super.notifyObservers(object);
	}

	/**
	 * Default constructor.
	 */
	public Motion(int floorNumber, int subCorridorNumber,boolean isMotionDetected) {
		this.floorNumber = floorNumber;
		this.subCorridorNumber = subCorridorNumber;
		this.isMotionDetected = isMotionDetected;
	}

	public boolean isMotionDetected() {
		return isMotionDetected;
	}

	public void setMotionDetected(boolean isMotionDetected) {
		this.isMotionDetected = isMotionDetected;
	}

	/**
	 * @return the floorNumber
	 */
	public int getFloorNumber() {
		return floorNumber;
	}

	/**
	 * @return the subCorridorNumber
	 */
	public int getSubCorridorNumber() {
		return subCorridorNumber;
	}

}
