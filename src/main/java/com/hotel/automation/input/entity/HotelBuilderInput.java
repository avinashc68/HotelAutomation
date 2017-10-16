package com.hotel.automation.input.entity;

public class HotelBuilderInput {
	private int floors;
	private int mainCorridors;
	private int subCorridors;

	public HotelBuilderInput(int floors, int mainCorridors, int subCorridors) {
		super();
		this.floors = floors;
		this.mainCorridors = mainCorridors;
		this.subCorridors = subCorridors;
	}

	public int getFloors() {
		return floors;
	}

	public int getMainCorridors() {
		return mainCorridors;
	}

	public int getSubCorridors() {
		return subCorridors;
	}

}
