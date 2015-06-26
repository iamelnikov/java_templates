package ru.imelnikov.template.concurrency;

import java.util.Set;

public class SmallBus extends AbstractBus{


	public SmallBus(int maxAvaliablePlacesCount) {
		super(maxAvaliablePlacesCount);
	}
	
	public SmallBus(int maxAvaliablePlacesCount, Set<BusStation> route) {
		super(maxAvaliablePlacesCount);
		this.routeDirection = ROUTE_DIRECTION.DIRECT;
	}
	
}
