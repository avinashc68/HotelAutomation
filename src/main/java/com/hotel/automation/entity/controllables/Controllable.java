package com.hotel.automation.entity.controllables;

/**
 * Interface that all controllable applicances must implement.
 * @author avinash
 *
 */
public interface Controllable {

	String DOUBLE_SPACES = "  ";
	void switchOn();
	void switchOff();
}
