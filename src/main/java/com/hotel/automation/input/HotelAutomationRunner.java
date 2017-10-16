package com.hotel.automation.input;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.hotel.automation.controller.MotionDataController;
import com.hotel.automation.criteria.PowerConsumptionCriteria;
import com.hotel.automation.entity.Hotel;
import com.hotel.automation.entity.builder.HotelBuilder;
import com.hotel.automation.input.entity.HotelBuilderInput;
import com.hotel.automation.input.entity.SensorInput;
import com.hotel.automation.sensor.data.processor.MotionDataProcessor;
import com.hotel.automation.entity.Motion;

/**
 * The class is intended to be a runner for the application, but the sensor inputs are not generated from the sensors in the corridors,
 * rather they are user inputs. These are directly passed to the controller and the controller modifies the hotel state
 * @author avinash
 *
 */
public class HotelAutomationRunner {

	public static void main(String[] args) {
		System.out.println("Hello and Welcome to Hotel Automation Application!");
		HotelBuilderInput builderInput = UserInputUtil.parseInitialInputs();
		Hotel hotel = new HotelBuilder().addFloors(builderInput.getFloors()).addMainCorridors(builderInput.getMainCorridors())
				.addSubCorridors(builderInput.getSubCorridors()).activateSensors().build();
		MotionDataProcessor dataProcessor = new MotionDataProcessor();
		dataProcessor.setConsumptionCriteria(new PowerConsumptionCriteria());
		MotionDataController motionDataController = new MotionDataController(hotel.getHub(), hotel);
		motionDataController.setDataProcessor(dataProcessor);
				ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
				scheduledExecutorService.scheduleAtFixedRate(() -> {
									try {
										SensorInput state = UserInputUtil.parseSensorInputs();
										if (state != null) {
											Motion motion = new Motion(state.getFloorNumber(), state.getSubCorridorNumber(), state.isMotionDetected());
											hotel.getHub().update(null, motion);
											System.out.println(hotel.toString());
											//passing the motion event directly to the sensor hub,as if generated from hotel sensors
										}
										
									} catch (IllegalArgumentException exception) {
										System.err.println(exception.getMessage());
									}
								}, 3,3, TimeUnit.SECONDS);

				destroy(scheduledExecutorService);
	}

	private static void destroy(
			ScheduledExecutorService scheduledExecutorService) {
		Runtime.getRuntime().addShutdownHook(
				new Thread(() -> scheduledExecutorService.shutdownNow()));
	}
}
