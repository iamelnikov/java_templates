package ru.imelnikov.template.concurrency;

import java.util.LinkedList;

public class BigBus extends AbstractBus{

	public BigBus(int maxAvaliablePlacesCount, VEHICLE_CONDITION condition) {
		super(maxAvaliablePlacesCount, condition);
	}
	
	public BigBus(int maxAvaliablePlacesCount, VEHICLE_CONDITION condition, LinkedList<BusStation> route) {
		super(maxAvaliablePlacesCount, condition, route);
	}

}
