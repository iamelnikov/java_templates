package ru.imelnikov.template.concurrency;

import java.util.concurrent.Semaphore;

public abstract class Vehicle implements Runnable {
	
	protected final int maxAvaliablePlacesCount;
	protected Semaphore semaphore;
	
	public Vehicle(int maxAvaliablePlacesCount){
		this.maxAvaliablePlacesCount = maxAvaliablePlacesCount;
		this.semaphore = new Semaphore(maxAvaliablePlacesCount, WorldSettings.INSTANCE.isManInTheWorldBecomeCrazy());
	}
	
	public int getCurrentlyAvaliablePlacesCount(){
		return this.semaphore.availablePermits();
	}
	
	public Boolean putOnePassenger(){
		return this.semaphore.tryAcquire();
	}
	
	public Boolean putManyPassenger(int passengerCount){
		return this.semaphore.tryAcquire(passengerCount);
	}
}
