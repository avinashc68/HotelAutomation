package com.hotel.automation.sensor.hub;

import org.apache.commons.collections.MapUtils;

import com.hotel.automation.controller.MotionDataController;
import com.hotel.automation.criteria.PowerConsumptionCriteria;
import com.hotel.automation.entity.Hotel;
import com.hotel.automation.entity.Motion;
import com.hotel.automation.entity.builder.HotelBuilder;
import com.hotel.automation.sensor.data.processor.MotionDataProcessor;

import junit.framework.TestCase;

public class MotionSensorHubTest extends TestCase {

	public void testMotionSensorHub() {
		//setup: Setting up the elements to ensure event flow can be tested
		Hotel hotel = new HotelBuilder().addFloors(2).addMainCorridors(1).addSubCorridors(2).activateSensors().build();
		SensorHub hub = hotel.getHub();
		MotionDataController motionDataController  = new MotionDataController(hub, hotel);
		MotionDataProcessor dataProcessor = new MotionDataProcessor();
		dataProcessor.setConsumptionCriteria(new PowerConsumptionCriteria());
		motionDataController.setDataProcessor(dataProcessor);
		Motion detectedMotion  = new Motion(1, 1, true);

		//when: The hub recieves an input event
		hub.update(null, detectedMotion);

		//then: Ideally the subcorridor light of floor one should be switched on and AC-off
		assertFalse("The Adjacent SubCorridor AC should be switched off", hotel.getFloors().get(0).getSubCorridors().get(1).getAirConditioners().get(0).isSwitchedOn());
		assertTrue("The light in the corridor should be switched ON", hotel.getFloors().get(0).getSubCorridors().get(0).getLightBulbs().get(0).isSwitchedOn());
		assertTrue(MapUtils.isNotEmpty(dataProcessor.getSwitchOffTracker()));

		//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		//when: A no motion trigger is generated
		hub.update(null, new Motion(1, 1, false));

		//then:
		assertFalse(hotel.getFloors().get(0).getSubCorridors().get(0).getLightBulbs().get(0).isSwitchedOn());
		assertTrue(hotel.getFloors().get(0).getSubCorridors().get(1).getAirConditioners().get(0).isSwitchedOn());
	}
}
