package ru.imelnikov.template.concurrency.vehicle;

public class Taxi extends Vehicle{

	public Taxi(int maxAvailablePlacesCount, VEHICLE_CONDITION condition) {
		super(maxAvailablePlacesCount, condition);
	}

	@Override
	public void run() {
		
	}
}
