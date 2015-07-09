package ru.imelnikov.template.concurrency.infrastructure;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import ru.imelnikov.template.concurrency.vehicle.AbstractBus;

public class BusDepot extends Place {

	private static Logger LOG = Logger.getLogger(BusDepot.class);
	private String name;

	private ExecutorService busesThreadPool;
	private CountDownLatch canCloseDepotLatch;
	private Set<AbstractBus> busSet;

	public BusDepot(String depotName, double x, double y) {
		super(x, y);
		this.name = depotName;
		this.busSet = new LinkedHashSet<AbstractBus>();
		this.busesThreadPool = Executors.newCachedThreadPool();
	}

	public String getName() {
		return this.name;
	}

	private boolean isBusSetNull(boolean initIfNull) {
		boolean isNull = false;
		if (this.busSet == null) {
			isNull = true;

			if (initIfNull)
				this.busSet = new LinkedHashSet<AbstractBus>();
		}
		return isNull;
	}

	public void addBusToDepot(AbstractBus bus) {
		isBusSetNull(true);
		bus.setBusDepot(this, this.generateRouteNumber());
		this.busSet.add(bus);
	}

	public void removeBusFromDepot(AbstractBus bus) {
		if (!isBusSetNull(false) && this.busSet.contains(bus))
			this.busSet.remove(bus);
		bus.removeBusDepot();
	}

	public int generateRouteNumber() {
		Set<Integer> existingRouteNumberSet = getExistingBusRouteNumbers();

		Random r = new Random();
		int genRN;
		if (existingRouteNumberSet == null || existingRouteNumberSet.isEmpty())
			genRN = r.nextInt(900) + 1;
		else {
			do {
				genRN = r.nextInt(900) + 1;
			} while (!existingRouteNumberSet.contains(genRN));
		}
		return genRN;

	}

	public void busReturnToDepot(AbstractBus bus) {
		LOG.info("Bus " + bus.getRouteNumber() + " return to Depot " + this.getName());
		this.canCloseDepotLatch.countDown();
	}

	public void startBuses() {
		assert busSet != null;
		this.canCloseDepotLatch = new CountDownLatch(this.busSet.size());
		busSet.stream().forEach(it -> {
			LOG.info("Start " + it.getRouteName());
			this.busesThreadPool.execute(it);
		});
	}

	private Set<Integer> getExistingBusRouteNumbers() {
		assert this.busSet != null;
		return this.busSet.parallelStream().map(it -> it.getRouteNumber())
				.collect(Collectors.toSet());
	}

	public void closeDepotAfterAllBusesArrive() {
		LOG.info("Trying To Close Bus Depot  " + this.getName());
		try {
			LOG.info("Waiting For All Buses Return To Depot ");
			this.canCloseDepotLatch.await();
			LOG.info("All Buses Return To Depot. Close Depot " + this.getName());
			this.busesThreadPool.shutdown();
		} catch (InterruptedException ie) {
			LOG.error("Could not close bus depot", ie);
			ie.printStackTrace();
		}
	}
}
