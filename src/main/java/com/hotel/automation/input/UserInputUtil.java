package com.hotel.automation.input;

import java.util.Scanner;

import com.hotel.automation.input.entity.HotelBuilderInput;
import com.hotel.automation.input.entity.SensorInput;

public class UserInputUtil {

	private static final String FLOOR_COUNT = "Number of floors: ";
	private static final String MAIN_CORRIDOR_COUNT = "Main Corridors per floor: ";
	private static final String SUB_CORRIDOR_COUNT = "Sub Corridors per floor: ";
	private static final String INPUT_STRING_EXIT = "exit";
	private static final String INPUT_STRING_INVALID_INTEGER = "Please enter an integer.";
	private static final String INPUT_STRING_MALFORMED_FORMAT = "Input malformed. Please try again.";
	private static final Scanner SCANNER = new Scanner(System.in);

	public static HotelBuilderInput parseInitialInputs() {
		System.out.println(FLOOR_COUNT);
		validateInput();
		int floorCount = Integer.parseInt(SCANNER.nextLine());
		System.out.println(MAIN_CORRIDOR_COUNT);
		validateInput();
		int mainCorridorCount = Integer.parseInt(SCANNER.nextLine());
		System.out.println(SUB_CORRIDOR_COUNT);
		validateInput();
		int subCorridorCount = Integer.parseInt(SCANNER.nextLine());
		HotelBuilderInput hotelBuilderInput = new HotelBuilderInput(floorCount,
				mainCorridorCount, subCorridorCount);
		return hotelBuilderInput;
	}

	public static SensorInput parseSensorInputs() {
		System.out.println("Please enter the sensor inputs as comma sepearate values :: true,1,1");
		System.out.println("Where  first item is motion, second is floor, third is SubCorridor");
		String sensorInputLine = SCANNER.nextLine();
		if (sensorInputLine.isEmpty()) {
			throw new IllegalArgumentException(INPUT_STRING_MALFORMED_FORMAT);
		} else if (sensorInputLine.equalsIgnoreCase(INPUT_STRING_EXIT)) {
			closeInputStream();
			System.out.println("Terminating...");
			System.exit(0);
		} else if (!sensorInputLine.contains(",")) {
			throw new IllegalArgumentException(INPUT_STRING_MALFORMED_FORMAT);
		}
		String[] sensorInputArray = sensorInputLine.split(",");
		if (sensorInputArray.length != 3) {
			throw new IllegalArgumentException(INPUT_STRING_MALFORMED_FORMAT);
		}
		boolean isMotionDetected = Boolean.valueOf(sensorInputArray[0]);
		int floorNumber = Integer.valueOf(sensorInputArray[1]);
		int subCorridorNumber = Integer.valueOf(sensorInputArray[2]);
		SensorInput sensorInput = new SensorInput(floorNumber, subCorridorNumber, isMotionDetected);
		return sensorInput;
	}

	private static void validateInput() {
		while (!SCANNER.hasNextInt()) {
			System.err.println(INPUT_STRING_INVALID_INTEGER);
			SCANNER.nextLine();
		}
	}

	private static void closeInputStream() {
		SCANNER.close();
	}
}
