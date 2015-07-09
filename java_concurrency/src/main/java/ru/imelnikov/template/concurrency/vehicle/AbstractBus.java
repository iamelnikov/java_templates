package ru.imelnikov.template.concurrency.vehicle;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

import org.apache.log4j.Logger;

import ru.imelnikov.template.concurrency.infrastructure.BusDepot;
import ru.imelnikov.template.concurrency.infrastructure.BusStation;
import ru.imelnikov.template.concurrency.person.Person;

public abstract class AbstractBus extends Vehicle {

	private static Logger LOG = Logger.getLogger(AbstractBus.class);

	private BusStation currentBusStation;
	private BusDepot depot = null;
	// private CountDownLatch busStopAndDoorsAreOpen = new CountDownLatch(1);
	private boolean areDoorsOpen = false;
	private LinkedList<BusStation> route;
	private Set<Person> passengers = new LinkedHashSet<Person>(
			maxAvailablePlacesCount);

	protected ROUTE_DIRECTION routeDirection;
	private int routeNumber;

	public AbstractBus(int maxAvaliablePlacesCount, VEHICLE_CONDITION condition) {
		super(maxAvaliablePlacesCount, condition);
	}

	public AbstractBus(int maxAvaliablePlacesCount,
			VEHICLE_CONDITION condition, LinkedList<BusStation> route) {
		super(maxAvaliablePlacesCount, condition);
		this.route = route;
		this.routeDirection = ROUTE_DIRECTION.DIRECT;
	}

	private final static String directRouteName = "Route Number: %s, From: %s - To: %s";
	private final static String reverseRouteName = "Route Number: %s - reverse, From: %s - To: %s";

	public String getRouteNumberAsString() {
		assert this.depot != null;
		assert this.routeNumber != -1;
		return "Bus #" + this.depot.getName() + "_" + this.routeNumber;
	}

	int getRouteNumber() {
		return this.routeNumber;
	}

	public void setBusDepot(BusDepot depot, int routeNumber) {
		assert depot != null;
		assert routeNumber > 0;
		this.depot = depot;
		this.routeNumber = routeNumber;
	}

	public void removeBusDepot() {
		this.depot = null;
		this.routeNumber = -1;
	}

	public String getRouteName() {
		final BusStation firstBs = route.getFirst();
		final BusStation lastBs = route.getLast();
		final String routeName;
		final String startBusStation;
		final String finishBusStation;
		switch (routeDirection) {
		case DIRECT: {
			routeName = directRouteName;
			startBusStation = firstBs.getName();
			finishBusStation = lastBs.getName();
			break;
		}
		case REVERSE: {
			routeName = reverseRouteName;
			startBusStation = lastBs.getName();
			finishBusStation = firstBs.getName();
			break;
		}
		default:
			throw new IllegalStateException(
					"Could not define current route direction");
		}
		return String.format(routeName, getRouteNumberAsString(),
				startBusStation, finishBusStation);
	}

	private int routeCount = 2;

	public void run() {
		try {
			while (routeCount > 0)
				runToTheNextBusStation();

			runToDepot();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	public void runToDepot() {
		LOG.info("Bus #" + this.routeNumber + " Run to depot "
				+ this.depot.getName());
		assert depot != null;
		this.depot.busReturnToDepot(this);
	}

	public boolean areDoorsOpen() {
		return this.areDoorsOpen;
	}

	protected void openTheDoors() {
		this.areDoorsOpen = true;
	}

	protected void closeTheDoors() {
		this.areDoorsOpen = false;
	}

	private BusStation getNextBusStation() {
		assert this.route != null;
		if (this.currentBusStation == null) {
			switch (this.routeDirection) {
			case DIRECT:
				return this.route.getFirst();
			case REVERSE:
				return this.route.getLast();
			default:
				throw new IllegalStateException(
						"Could not define current route direction");
			}
		} else {
			int curBsInd = this.route.indexOf(currentBusStation);
			if (curBsInd != -1) {
				switch (this.routeDirection) {
				case DIRECT: {
					if (curBsInd == this.route.size() - 1) {
						LOG.info("Bus " + getRouteNumberAsString()
								+ " Goes To Reverse Route");
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
						this.routeCount--;
						LOG.info("Bus " + getRouteNumberAsString()
								+ " Goes To Direct Route. " + this.routeCount
								+ " route count left");
						return null;
					} else {
						curBsInd--;
						return this.route.get(curBsInd);
					}
				}
				default:
					throw new IllegalStateException(
							"Could not define current route direction");
				}
			} else
				throw new IllegalStateException(
						"Could not find Current Bus Station in Bus Route");
		}
	}

	@SuppressWarnings("unused")
	private int getAvailablePlacesCount() {
		return this.maxAvailablePlacesCount - this.passengers.size();
	}

	private static final String busArrivedToStation = "The Bus №%s arrived to Bus Station: %s";

	// private static final String busArrivedToDepot =
	// "The Bus №%s Arrived To Depot";

	// private CountDownLatch isAllPassengersWasSeatedLatch;
	private void stopOnTheBusStation() throws InterruptedException {
		LOG.info(String.format(busArrivedToStation, this.routeNumber,
				this.currentBusStation.getName()));
		this.currentBusStation.busArriveToStation(this);
		this.openTheDoors();
		Random r = new Random();
		Thread.sleep((r.nextInt(5) + 1) * 1000);
		// this.isAllPassengersWasSeatedLatch = new
		// CountDownLatch(getAvailablePlacesCount());
		// this.isAllPassengersWasSeatedLatch.await();
		this.runToTheNextBusStation();
	}

	public void putPassenger(Person man) {
		// assert isAllPassengersWasSeatedLatch!=null;
		assert passengers != null;
		this.passengers.add(man);
		// isAllPassengersWasSeatedLatch.countDown();
	}

	private static final String busStartRunningToNextStation = "The Bus №%s start running to station %s";

	private void runToTheNextBusStation() throws InterruptedException {
		this.closeTheDoors();
		if (this.currentBusStation != null) {
			LOG.info("Bus " + this.routeNumber + " start driving "
					+ getRouteName());
			this.currentBusStation.busLeaveFromStation(this);
		}
		this.currentBusStation = getNextBusStation();
		if (this.currentBusStation != null) {
			LOG.info(String.format(busStartRunningToNextStation,
					this.routeNumber, this.currentBusStation.getName()));
			Random r = new Random();
			Thread.sleep((r.nextInt(5) + 1) * 1000);
			this.stopOnTheBusStation();
		} else
			relaxBeforeNextRoute();
	}

	private void relaxBeforeNextRoute() throws InterruptedException {
		LOG.info("Relax Before Next Route For Bus "
				+ this.getRouteNumberAsString());
		Thread.sleep(5000);
		LOG.info("Start New Route " + this.getRouteNumberAsString());
	}

	static enum ROUTE_DIRECTION {
		DIRECT, REVERSE
	}
}
