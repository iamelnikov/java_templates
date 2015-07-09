package ru.imelnikov.template.concurrency.vehicle;

import java.util.LinkedList;

import ru.imelnikov.template.concurrency.infrastructure.BusStation;

public class VehicleFactory {

	public static VehicleFactory INSTANCE = new VehicleFactory();
	
	private VehicleFactory(){};
	
	private static final int smallBusSeatsCount = 16;
	private static final int bigBusSeatsCount = 64;
	
	public SmallBus createSmallBus(VEHICLE_CONDITION condition, LinkedList<BusStation> route){
		return new SmallBus(smallBusSeatsCount, condition, route);
	}
	
	public BigBus createBigBus(VEHICLE_CONDITION condition, LinkedList<BusStation> route){
		return new BigBus(bigBusSeatsCount, condition, route);
	}
	
	public Taxi createTaxi(int maxAvaliablePlacesCount, VEHICLE_CONDITION condition, LinkedList<BusStation> route){
		return new Taxi(maxAvaliablePlacesCount, condition);
	}
}
