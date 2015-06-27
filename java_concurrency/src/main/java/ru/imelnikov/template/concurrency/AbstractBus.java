package ru.imelnikov.template.concurrency;

import java.util.LinkedList;
import java.util.Random;
import java.util.SortedSet;
import java.util.concurrent.CountDownLatch;

public abstract class AbstractBus extends Vehicle {

	private BusStation currentBusStation;
	private CountDownLatch isAllPassengersWasSeatedLatch;
	private boolean isDoorsOpen = false;
	private LinkedList<BusStation> route;
	private ROUTE_DIRECTION routeDirection;

	public AbstractBus(int maxAvaliablePlacesCount) {
		super(maxAvaliablePlacesCount);
	}

	static enum ROUTE_DIRECTION {
		DIRECT, REVERSE
	}

	public void run() {

	}

	protected void openTheDoors() {
		this.isDoorsOpen = true;
	}

	protected void closeTheDoors() {
		this.isDoorsOpen = false;
	}

	private BusStation getNextBusStation() {
		assert this.route != null;
		if (this.currentBusStation == null) {
			switch (this.routeDirection) {
			case DIRECT:
				return this.route.getFirst();
			case REVERSE:
				return this.route.getLast();
			default: throw new IllegalStateException(
					"Could not define current route direction");
			}
		} else {
			int curBsInd = this.route.indexOf(currentBusStation);
			if (curBsInd != -1) {
				switch (this.routeDirection) {
				case DIRECT: {
					if (curBsInd == this.route.size()) {
						this.routeDirection = ROUTE_DIRECTION.REVERSE;
						return null;
					} else {
						curBsInd++;
						return this.route.get(curBsInd);
					}
				}
				case REVERSE: {
					if (curBsInd == 0) {
						this.routeDirection = ROUTE_DIRECTION.DIRECT;
						return null;
					} else {
						curBsInd--;
						return this.route.get(curBsInd);
					}
				}
				default: throw new IllegalStateException(
						"Could not define current route direction");
				}
			} else
				throw new IllegalStateException(
						"Could not find Current Bus Station in Bus Route");
		}
	}

	private void stopOnTheBusStation() throws InterruptedException {
		this.currentBusStation = getNextBusStation();
		if (this.currentBusStation != null) {
			this.openTheDoors();
			this.isAllPassengersWasSeatedLatch.await();
		} else
			relaxBeforeNextRoute();
	}

	private void runToTheNextBusStation() throws InterruptedException {
		this.closeTheDoors();
		Random r = new Random();
		Thread.sleep(r.nextInt(7) * 1000);

	}

	private void relaxBeforeNextRoute() throws InterruptedException {
		Thread.sleep(15000);
	}
}
