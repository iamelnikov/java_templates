package ru.imelnikov.template.concurrency;

import java.util.Random;

import org.apache.log4j.Logger;

public class CityMain {

	private static final String country = "Russia";
	private static final String region = "Moscow";
	private static final String cityName = "Moscow";
	
	public static final int DEPOT_ARRAY_SIZE = 15;
	public static final int BUS_STATION_COUNT = 3500;
	public static void main(String[] args) {
		Logger log = Logger.getLogger(CityMain.class);
		int[] busDepotArray = new int[DEPOT_ARRAY_SIZE];
		for(int i = 0; i < DEPOT_ARRAY_SIZE; i++) {
			busDepotArray[i] = new Random().nextInt(15) + 15;
		}
		City city = CityFactory.INSTANCE.createCity(country, region, cityName, busDepotArray, BUS_STATION_COUNT);
		log.info("Start City Day");
		city.startBusesFromDepots();
		city.stopBusesFromDepot();
		log.info("Stop City Day");
	}

}
