package com.hotel.automation.controller;

import java.util.Observable;

import com.hotel.automation.entity.Hotel;
import com.hotel.automation.entity.Motion;
import com.hotel.automation.sensor.data.processor.DataProcessor;
import com.hotel.automation.sensor.hub.SensorHub;

/**
 * Controller has the logic of what to do with the data.
 * This abstraction is useful because, we can have it process/throttle and filter events that need to be processed.
 * This controller listens to events from the sensor hub and decides what to do with them. Doesn't have too much built in functionality now
 * @author Avinash
 */
public class MotionDataController implements DataController {

	private Hotel hotel;
	private DataProcessor dataProcessor;

	public MotionDataController(SensorHub sensorHub, Hotel hotel) {
		super();
		this.hotel = hotel;
		sensorHub.registerController(this);
	}

	public void setDataProcessor(DataProcessor dataProcessor) {
		this.dataProcessor = dataProcessor;
	}

	@Override
	public void update(Observable o, Object arg) {
		Motion motion = (Motion) arg;
		dataProcessor.processData(motion, hotel);
	}

	@Override
	/**
	 * PlaceHolder method for now. The idea of having a controller instead of directly sending data
	 * to data processors, is to perform any operations that might be needed before we process it.
	 * Use case, we might not want to process all data that is sent from the sensor hub to avoid flickering of lights or inconvenience
	 */
	public void ingestData() {
		//scope to process data before forwarding it to the Data Processor.
		//Scope to do filtering, enhancing the data etc.etc.
	}
	
}
