package ru.imelnikov.template.concurrency.vehicle;

import java.util.concurrent.Semaphore;

import ru.imelnikov.template.concurrency.WorldSettings;

public abstract class Vehicle implements Runnable {
	
	protected final int maxAvailablePlacesCount;
	protected VEHICLE_CONDITION condition;
	protected Semaphore semaphore;
	
	public Vehicle(int maxAvailablePlacesCount, VEHICLE_CONDITION condition){
		this.maxAvailablePlacesCount = maxAvailablePlacesCount;
		this.condition = condition;
		this.semaphore = new Semaphore(maxAvailablePlacesCount, WorldSettings.INSTANCE.isManInTheWorldBecomeCrazy());
	}
	
	public int getCurrentlyAvailablePlacesCount(){
		return this.semaphore.availablePermits();
	}
	
	public Boolean putOnePassenger(){
		return this.semaphore.tryAcquire();
	}
	
	public Boolean putManyPassenger(int passengerCount){
		return this.semaphore.tryAcquire(passengerCount);
	}
	
	public VEHICLE_CONDITION getCondition() {
		return condition;
	}

	public void setCondition(VEHICLE_CONDITION condition) {
		this.condition = condition;
	}

	static enum VEHICLE_CONDITION{
		NEW, EXCELLENT, VERY_GOOD,GOOD, AVERAGE, BELOW_AVERAGE, BROKEN 
	}
}
