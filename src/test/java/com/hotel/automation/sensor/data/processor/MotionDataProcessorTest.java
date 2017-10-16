package com.hotel.automation.sensor.data.processor;

import com.hotel.automation.criteria.PowerConsumptionCriteria;
import com.hotel.automation.entity.Hotel;
import com.hotel.automation.entity.Motion;
import com.hotel.automation.entity.builder.HotelBuilder;

import junit.framework.TestCase;

public class MotionDataProcessorTest extends TestCase {

	public void testProcessData() {
		//setup:
		MotionDataProcessor motionDataProcessor = new MotionDataProcessor();
		Motion motion = new Motion(1, 1, true);
		Hotel hotel = new HotelBuilder().addFloors(2).addMainCorridors(1).addSubCorridors(2).build();
		PowerConsumptionCriteria powerConsumptionCriteria = new PowerConsumptionCriteria();
		motionDataProcessor.setConsumptionCriteria(powerConsumptionCriteria);

		//when:
		motionDataProcessor.processData(motion, hotel);

		//then:
		assertTrue(hotel.getFloors().get(0).getSubCorridors().get(0).getLightBulbs().get(0).isSwitchedOn());
	}
}
