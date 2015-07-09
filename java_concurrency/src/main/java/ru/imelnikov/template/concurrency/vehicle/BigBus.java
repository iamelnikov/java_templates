package ru.imelnikov.template.concurrency.vehicle;

import java.util.LinkedList;

import ru.imelnikov.template.concurrency.infrastructure.BusStation;

public class BigBus extends AbstractBus{

	public BigBus(int maxAvaliablePlacesCount, VEHICLE_CONDITION condition) {
		super(maxAvaliablePlacesCount, condition);
	}
	
	public BigBus(int maxAvaliablePlacesCount, VEHICLE_CONDITION condition, LinkedList<BusStation> route) {
		super(maxAvaliablePlacesCount, condition, route);
	}

}
