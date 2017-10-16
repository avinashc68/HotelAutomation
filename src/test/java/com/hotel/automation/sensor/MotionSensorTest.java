package com.hotel.automation.sensor;

import com.hotel.automation.controller.MotionDataController;
import com.hotel.automation.criteria.Criteria;
import com.hotel.automation.criteria.PowerConsumptionCriteria;
import com.hotel.automation.entity.Hotel;
import com.hotel.automation.entity.builder.HotelBuilder;
import com.hotel.automation.sensor.data.processor.MotionDataProcessor;
import com.hotel.automation.sensor.hub.SensorHub;

import junit.framework.TestCase;

public class MotionSensorTest extends TestCase {

	public void testMotionSensor() {
		//setup: Setup a hotel with sensors armed
		Hotel hotel = new HotelBuilder().addFloors(1).addMainCorridors(1).addSubCorridors(1).activateSensors().build();
		MotionSensor sensor = (MotionSensor) hotel.getFloors().get(0).getSubCorridors().get(0).getMotioSensor();
		//Hub listens to the sensors of each floor
		SensorHub sensorHub = hotel.getHub();
		MotionDataController controller = new MotionDataController(sensorHub, hotel);
		MotionDataProcessor motionDataProcessor = new MotionDataProcessor();
		controller.setDataProcessor(motionDataProcessor);
		Criteria criteria = new PowerConsumptionCriteria();
		motionDataProcessor.setConsumptionCriteria(criteria);
		sensor.registerForEvents(sensorHub);

		//when:
		sensor.generateEvent(true);

		//then:
		assertFalse(hotel.getFloors().get(0).getSubCorridors().get(0).getAirConditioners().get(0).isSwitchedOn());
		assertTrue(hotel.getFloors().get(0).getSubCorridors().get(0).getLightBulbs().get(0).isSwitchedOn());
	}
}
