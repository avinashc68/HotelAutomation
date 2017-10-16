package com.hotel.automation.entity.controllables;

public class LightBulb extends ElectricalAppliance{

	public LightBulb(int powerRating, int lightNumber, boolean switchedOn) {
		this.powerRating = powerRating;
		this.applianceNumber = lightNumber;
		this.switchedOn = switchedOn;
	}

	@Override
	public String toString() {
		return DOUBLE_SPACES + DOUBLE_SPACES + "Light "
				+ (applianceNumber + 1) + " : " + (switchedOn ? "ON" : "OFF");
	}
}
