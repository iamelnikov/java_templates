package ru.imelnikov.template.concurrency;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class BusStation {

	private Set<Man> mansOnBusStop = new LinkedHashSet<Man>();

	public BusStation() {

	}

	public void vehicleArrive(Vehicle vehicle){
		
	}
	
	public void vehicleLeave(Vehicle vehicle){
		
	}
	
	public void passengerArrive(Man man) {
		assert man != null;
		this.mansOnBusStop.add(man);
	}

	public void passengerArrive(Collection<Man> manCol) {
		assert manCol != null;
		if (!manCol.isEmpty())
			this.mansOnBusStop.addAll(manCol);
	}

	public void passengerLeave(Man man) {
		assert man != null;
		this.mansOnBusStop.remove(man);
	}
	
	public void passengerLeave(Collection<Man> manCol) {
		assert manCol != null;
		this.mansOnBusStop.removeAll(manCol);
	}
}
