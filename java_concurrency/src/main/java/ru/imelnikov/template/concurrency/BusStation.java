package ru.imelnikov.template.concurrency;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class BusStation extends Place{

	private static Logger LOG = Logger.getLogger(BusStation.class);
	private Set<AbstractBus> busOnStation = new LinkedHashSet<AbstractBus>(); 
	private Set<Person> mansOnBusStop = new LinkedHashSet<Person>();
	private String name;

	public BusStation(String name, double xCoordinate, double yCoordinate) {
		super(xCoordinate, yCoordinate);
		this.name = name;
	}

/*	public void vehicleArrive(Vehicle vehicle){
		
	}
	
	public void vehicleLeave(Vehicle vehicle){
		
	}*/
	
	public void passengerArrive(Person man) {
		assert man != null;
		this.mansOnBusStop.add(man);
	}

	public void passengerArrive(Collection<Person> manCol) {
		assert manCol != null;
		if (!manCol.isEmpty())
			this.mansOnBusStop.addAll(manCol);
	}

	public void passengerLeave(Person man) {
		assert man != null;
		this.mansOnBusStop.remove(man);
	}
	
	public void passengerLeave(Collection<Person> manCol) {
		assert manCol != null;
		this.mansOnBusStop.removeAll(manCol);
	}

	public String getName() {
		return name;
	}
	
	public void busArriveToStation(AbstractBus bus) {
		LOG.info(bus.getRouteNumberAsString() +  " arrive to station " + this.name);
		busOnStation.add(bus);
	}
	
	public void busLeaveFromStation(AbstractBus bus) {
		LOG.info(bus.getRouteNumberAsString() +  " leave the station " + this.name);
		busOnStation.remove(bus);
	}
}
