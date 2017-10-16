package com.hotel.automation.sensor.data.processor;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.hotel.automation.criteria.Criteria;
import com.hotel.automation.entity.Floor;
import com.hotel.automation.entity.Hotel;
import com.hotel.automation.entity.Motion;
import com.hotel.automation.entity.SubCorridor;

public class MotionDataProcessor implements DataProcessor {

	private Criteria consumptionCriteria;
	private Map<String, SubCorridor> switchOffTracker;

	public Map<String, SubCorridor> getSwitchOffTracker() {
		return switchOffTracker;
	}

	public void setSwitchOffTracker(Map<String, SubCorridor> switchOffTracker) {
		this.switchOffTracker = switchOffTracker;
	}

	public void setConsumptionCriteria(Criteria consumptionCriteria) {
		this.consumptionCriteria = consumptionCriteria;
	}

	@Override
	public void processData(Object object, Hotel hotel) {
		Motion motion = (Motion) object;
		Floor matchingFloor = findMatchingFloor(hotel, motion);
		SubCorridor matchingSubCorridor = findMatchingSubCorridor(motion,
				matchingFloor);
		
		if (motion.isMotionDetected()) {
			if (matchingSubCorridor.getLightBulbs().get(0).isSwitchedOn()) {
				return;
			}

			matchingSubCorridor.getLightBulbs().forEach(
					lightBulb -> lightBulb.switchOn());

			if (!consumptionCriteria.criteriaMetFor(matchingFloor)) {
				SubCorridor someOtherSubCorridor = findSomeOtherSubCorridor(motion,
						matchingFloor, matchingSubCorridor);
				someOtherSubCorridor.getAirConditioners().get(0).switchOff();
				if (switchOffTracker == null) {
					switchOffTracker = new HashMap<String, SubCorridor>();
					switchOffTracker.put(motion.getFloorNumber() + "_" + motion.getSubCorridorNumber(), someOtherSubCorridor);
				} else {
					switchOffTracker.put(motion.getFloorNumber() + "_" + motion.getSubCorridorNumber(), someOtherSubCorridor);
				}
			}
		} else {
			//This is the corridor where we would have switched off the AC because of motion
			SubCorridor corridor = switchOffTracker.get(motion.getFloorNumber() + "_" + motion.getSubCorridorNumber());
			matchingSubCorridor.getLightBulbs().forEach(
					lightBulb -> lightBulb.switchOff());
			if (corridor != null) {
				corridor.getAirConditioners().get(0).switchOn();
				switchOffTracker.remove(motion.getFloorNumber() + "_" + motion.getSubCorridorNumber());
			} else {
				matchingSubCorridor.getAirConditioners().forEach(ac -> ac.switchOn());
			}
		}
	}

	protected boolean isLightBulbSwitchedOnAtSubCorridor(SubCorridor subCorridor) {
		return subCorridor.getLightBulbs().get(0).isSwitchedOn();
	}

	protected Floor findMatchingFloor(Hotel hotel, Motion motion) {
		Optional<Floor> matchingFloorOptional = hotel
				.getFloors()
				.stream()
				.filter(floor -> floor.getFloorNumber() == (motion
						.getFloorNumber() - 1)).findFirst();

		return matchingFloorOptional.get();
	}

	protected SubCorridor findMatchingSubCorridor(Motion motion,
			Floor matchingFloor) {
		Optional<SubCorridor> matchingSubCorridorOptional = matchingFloor
				.getSubCorridors()
				.stream()
				.filter(subCorridor -> subCorridor.getCorridorNumber() == (motion
						.getSubCorridorNumber() - 1)).findFirst();

		return matchingSubCorridorOptional.get();
	}

	protected SubCorridor findSomeOtherSubCorridor(Motion motion,
			Floor matchingFloor, SubCorridor matchingSubCorridor) {
		Optional<SubCorridor> otherSubCorridorOptional = matchingFloor
				.getSubCorridors()
				.stream()
				.filter(subCorridor -> subCorridor.getCorridorNumber() != (motion
						.getSubCorridorNumber() - 1)).findFirst();
		SubCorridor someOtherSubCorridor;
		try {
			someOtherSubCorridor = otherSubCorridorOptional.get();
		} catch (NoSuchElementException exception) {
			someOtherSubCorridor = matchingSubCorridor;
		}
		return someOtherSubCorridor;
	}
}
