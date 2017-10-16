package com.hotel.automation.input.entity;

public class SensorInput {
	int floorNumber;
	int subCorridorNumber;
	boolean isMotionDetected;

	public SensorInput(int floorNumber, int subCorridorNumber,
			boolean isMotionDetected) {
		super();
		this.floorNumber = floorNumber;
		this.subCorridorNumber = subCorridorNumber;
		this.isMotionDetected = isMotionDetected;
	}

	public int getFloorNumber() {
		return floorNumber;
	}

	public void setFloorNumber(int floorNumber) {
		this.floorNumber = floorNumber;
	}

	public int getSubCorridorNumber() {
		return subCorridorNumber;
	}

	public void setSubCorridorNumber(int subCorridorNumber) {
		this.subCorridorNumber = subCorridorNumber;
	}

	public boolean isMotionDetected() {
		return isMotionDetected;
	}

	public void setMotionDetected(boolean isMotionDetected) {
		this.isMotionDetected = isMotionDetected;
	}

	@Override
	public String toString() {
		return "SensorInput [floorNumber=" + floorNumber
				+ ", subCorridorNumber=" + subCorridorNumber
				+ ", isMotionDetected=" + isMotionDetected + "]";
	}

}
