/**
 * 
 */
package com.hotel.automation.entity;

/**
 * @author asgs
 *
 */
public class MainCorridor extends Corridor {

	private CorridorType type;

	private boolean nightTime;

	private int corridorNumber;

	public MainCorridor(int corridorNumber) {
		super(CorridorType.MAIN);
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

	/**
	 * @return the nightTime
	 */
	public boolean isNightTime() {
		return nightTime;
	}

	/**
	 * @param nightTime
	 *            the nightTime to set
	 */
	public void setNightTime(boolean nightTime) {
		this.nightTime = nightTime;
	}

	@Override
	public String toString() {
		return "  " + "Main corridor " + (corridorNumber + 1);
	}

}
