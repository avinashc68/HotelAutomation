package com.hotel.automation.entity.controllables;

public class ElectricalAppliance implements Controllable{

	protected int powerRating;

	protected String powerRatingUnit;

	protected boolean switchedOn;

	protected int applianceNumber;
 
	public int getPowerRating() {
		return powerRating;
	}

	public void setPowerRating(int powerRating) {
		this.powerRating = powerRating;
	}

	public String getPowerRatingUnit() {
		return powerRatingUnit;
	}

	public void setPowerRatingUnit(String powerRatingUnit) {
		this.powerRatingUnit = powerRatingUnit;
	}

	public boolean isSwitchedOn() {
		return switchedOn;
	}

	public void setSwitchedOn(boolean switchedOn) {
		this.switchedOn = switchedOn;
	}

	public int getApplianceNumber() {
		return applianceNumber;
	}

	public void setApplianceNumber(int applianceNumber) {
		this.applianceNumber = applianceNumber;
	}

	@Override
	public void switchOn() {
		switchedOn = true;
	}

	@Override
	public void switchOff() {
		switchedOn = false;
	}
}
