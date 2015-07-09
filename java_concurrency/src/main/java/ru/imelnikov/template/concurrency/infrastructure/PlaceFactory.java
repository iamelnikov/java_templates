package ru.imelnikov.template.concurrency.infrastructure;

public class PlaceFactory {

	public static PlaceFactory INSTANCE = new PlaceFactory();
	
	private PlaceFactory(){};
	
	public BusStation createBusStation(String busStationName, double xCoordinate, double yCoordinate){
		return new BusStation(busStationName, xCoordinate, yCoordinate);
	}
	
	public BusDepot createBusDepot(String busDepotName, double xCoordinate, double yCoordinate){
		return new BusDepot(busDepotName, xCoordinate, yCoordinate);
	}
}
