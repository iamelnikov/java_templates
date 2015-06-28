package ru.imelnikov.template.concurrency;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

public class City {

	private static Logger LOG = Logger.getLogger(City.class);

	private String country;
	private String region;
	private String name;

	private Set<Place> places;

	public City(String country, String region, String cityName) {
		super();
		this.country = country;
		this.region = region;
		this.name = cityName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public synchronized <T extends Place> void addPlace(T place) {
		checkPlaceSet();
		this.places.add(place);
	}

	public Set<BusStation> getBusStationSet() {
		return this.places.parallelStream()
				.filter(it -> BusStation.class.isAssignableFrom(it.getClass()))
				.map(it -> (BusStation) it).collect(Collectors.toSet());
	}

	private static final String startBusesMsg = "Start Buses In City %s ";

	public void startBusesFromDepots() {
		LOG.info(String.format(startBusesMsg, name));
		this.places.parallelStream()
				.filter(it -> BusDepot.class.isAssignableFrom(it.getClass()))
				.map(it -> (BusDepot) it)
				.forEach(it -> {
								LOG.info("Start Buses From Depot " + it.getName());
								it.startBuses();});
	}
	
	private static final String stopBusesMsg = "Stop Buses In City %s ";
	public void stopBusesFromDepot(){
		LOG.info(String.format(stopBusesMsg, name));
		this.places.parallelStream()
				.filter(it -> BusDepot.class.isAssignableFrom(it.getClass()))
				.map(it -> (BusDepot) it)
				.forEach(it -> {it.closeDepotAfterAllBusesArrive();});
	}
	
	private void checkPlaceSet() {
		if (this.places == null) {
			this.places = new HashSet<Place>();
		}
	}
}
