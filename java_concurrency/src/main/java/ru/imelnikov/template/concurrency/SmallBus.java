package ru.imelnikov.template.concurrency;

import java.util.LinkedList;

public class SmallBus extends AbstractBus{

	public SmallBus(int maxAvaliablePlacesCount, VEHICLE_CONDITION condition) {
		super(maxAvaliablePlacesCount, condition);
	}
	
	public SmallBus(int maxAvaliablePlacesCount, VEHICLE_CONDITION condition, LinkedList<BusStation> route) {
		super(maxAvaliablePlacesCount, condition, route);
	}
	
}
