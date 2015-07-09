package ru.imelnikov.template.concurrency.vehicle;

import java.util.LinkedList;

import ru.imelnikov.template.concurrency.infrastructure.BusStation;

public class SmallBus extends AbstractBus{

	public SmallBus(int maxAvaliablePlacesCount, VEHICLE_CONDITION condition) {
		super(maxAvaliablePlacesCount, condition);
	}
	
	public SmallBus(int maxAvaliablePlacesCount, VEHICLE_CONDITION condition, LinkedList<BusStation> route) {
		super(maxAvaliablePlacesCount, condition, route);
	}
	
}
