package com.hotel.automation.sensor.hub.factory;

import java.util.List;

import com.hotel.automation.sensor.Sensor;
import com.hotel.automation.sensor.SensorTypes;
import com.hotel.automation.sensor.hub.MotionSensorHub;
import com.hotel.automation.sensor.hub.SensorHub;

/**
 * Factory to manufacture various sensor hubs. Provides some dependency inversion
 * @author avinash
 */
public class SensorHubFactory {

	public static SensorHub getSensorHub(SensorTypes sensorType, List<Sensor> sensors) {
		if (SensorTypes.MOTION.equals(sensorType)) {
			SensorHub sensorHub = new MotionSensorHub(sensors);
			return sensorHub;
		}
		return null;
	}
}
