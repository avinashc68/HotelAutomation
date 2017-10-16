package com.hotel.automation.entity;

import com.hotel.automation.sensor.Sensor;

public class SubCorridor extends Corridor {

	private CorridorType type;

	private int corridorNumber;

	private Sensor motionSensor;

	public Sensor getMotioSensor() {
		return motionSensor;
	}

	public void setMotioSensor(Sensor motionSensor) {
		this.motionSensor = motionSensor;
	}

	public void setCorridorNumber(int corridorNumber) {
		this.corridorNumber = corridorNumber;
	}

	public SubCorridor(int corridorNumber) {
		super(CorridorType.SUB);
		this.corridorNumber = corridorNumber;
	}

	/**
	 * @return the corridorNumber
	 */
	public int getCorridorNumber() {
		return corridorNumber;
	}

	@Override
	public CorridorType getType() {
		return type;
	}

	@Override
	public void setType(CorridorType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "  " + "Sub corridor " + (corridorNumber + 1);
	}

}
