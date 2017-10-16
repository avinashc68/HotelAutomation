package com.hotel.automation.criteria;

import com.hotel.automation.entity.Floor;
import com.hotel.automation.entity.Hotel;
import com.hotel.automation.entity.builder.HotelBuilder;

import junit.framework.TestCase;

public class PowerConsumptionCriteriaTest extends TestCase {

	public void testCriteriaMetFor(){
		//setup: Positive Case
		Hotel hotel = new HotelBuilder().addFloors(2).addMainCorridors(1).addSubCorridors(1).build();
		Floor floor = hotel.getFloors().get(0);
		PowerConsumptionCriteria criteria = new PowerConsumptionCriteria();

		//when:
		boolean isCriteriaMet = criteria.criteriaMetFor(floor);

		//then:
		assertTrue(isCriteriaMet);

		//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

		//when:
		floor.getSubCorridors().get(0).getAirConditioners().get(0).switchOn();;
		floor.getSubCorridors().get(0).getLightBulbs().get(0).switchOn();
		isCriteriaMet = criteria.criteriaMetFor(floor);

		//then:
		assertFalse(isCriteriaMet);
	}
}
