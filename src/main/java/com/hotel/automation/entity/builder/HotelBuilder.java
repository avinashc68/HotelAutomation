package com.hotel.automation.entity.builder;

import java.util.ArrayList;
import java.util.List;

import com.hotel.automation.entity.Floor;
import com.hotel.automation.entity.Hotel;
import com.hotel.automation.entity.MainCorridor;
import com.hotel.automation.entity.SubCorridor;
import com.hotel.automation.sensor.MotionSensor;
import com.hotel.automation.sensor.Sensor;
import com.hotel.automation.sensor.SensorTypes;
import com.hotel.automation.sensor.hub.SensorHub;
import com.hotel.automation.sensor.hub.factory.SensorHubFactory;

/**
 * Hotel Builder to ease the construction of hotel object 
 */
public class HotelBuilder {

	private Hotel hotel;

	public HotelBuilder() {
		initialize();
	}

	/**
	 * This is the first method to be invoked to ensure the Hotel instance is
	 * setup before adding on other components to it.
	 * @return HotelBuilder
	 */
	private HotelBuilder initialize() {
		hotel = new Hotel("An Interesting Hotel Name!!!!");
		hotel.setFloors(new ArrayList<Floor>());
		return this;
	}

	/**
	 * Adds as many floors to the count as requested.
	 * @param floorCount
	 * @return
	 */
	public HotelBuilder addFloors(int floorCount) {
		for (int counter = 0; counter < floorCount; counter++) {
			Floor floor = new Floor(counter);
			floor.setMainCorridors(new ArrayList<MainCorridor>());
			floor.setSubCorridors(new ArrayList<SubCorridor>());
			hotel.getFloors().add(floor);
		}
		return this;
	}

	/**
	 * Adds as many MainCorridors to each floor as requested.
	 * 
	 * @param mainCorridorCount
	 * @return
	 */
	public HotelBuilder addMainCorridors(int mainCorridorCount) {
		int floorCount = hotel.getFloors().size();
		for (int floorCounter = 0; floorCounter < floorCount; floorCounter++) {
			for (int corridorCounter = 0; corridorCounter < mainCorridorCount; corridorCounter++) {
				MainCorridor mainCorridor = new MainCorridor(corridorCounter);
				hotel.getFloors().get(floorCounter).getMainCorridors()
						.add(mainCorridor);
			}
		}
		return this;
	}

	/**
	 * Adds as many SubCorridors to each floor as requested.
	 * 
	 * @param subCorridorCount
	 * @return
	 */
	public HotelBuilder addSubCorridors(int subCorridorCount) {
		int floorCount = hotel.getFloors().size();
		for (int floorCounter = 0; floorCounter < floorCount; floorCounter++) {
			for (int corridorCounter = 0; corridorCounter < subCorridorCount; corridorCounter++) {
				SubCorridor subCorridor = new SubCorridor(corridorCounter);
				hotel.getFloors().get(floorCounter).getSubCorridors()
						.add(subCorridor);
				subCorridor.setMotioSensor(new MotionSensor(floorCount, subCorridorCount));
			}
		}
		return this;
	}

	/**
	 * Takes a specific kind of sensor hub and equips the hotel with it.
	 * We can replace the sensor with a more advanced sensor tomorrow and not  change hotel code
	 * @param sensorHub
	 * @return
	 */
	public HotelBuilder activateSensors() {
		List<Sensor> sensors = new ArrayList<Sensor>();
		for (Floor floor: hotel.getFloors()) {
			for (SubCorridor subCorridor : floor.getSubCorridors()) {
				sensors.add(subCorridor.getMotioSensor());
			}
		}
		SensorHub sensorHub = SensorHubFactory.getSensorHub(SensorTypes.MOTION, sensors);
		hotel.setHub(sensorHub);
		return this;
	}
	/**
	 * Returns the hotel instance at this given instant.
	 * 
	 * @return
	 */
	public Hotel build() {
		return hotel;
	}

}
