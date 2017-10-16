package com.hotel.automation.entity.controllables;

public class AirConditioner extends ElectricalAppliance{

	public AirConditioner(int powerRating, int lightNumber, boolean switchedOn) {
		this.powerRating = powerRating;
		this.applianceNumber = lightNumber;
		this.switchedOn = switchedOn;
	}

	@Override
	public String toString() {
		return DOUBLE_SPACES + DOUBLE_SPACES + "AC : "
				+ (switchedOn ? "ON" : "OFF");
	}
}
