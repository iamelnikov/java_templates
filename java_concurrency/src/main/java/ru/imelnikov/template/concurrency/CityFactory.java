package ru.imelnikov.template.concurrency;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import ru.imelnikov.template.concurrency.Vehicle.VEHICLE_CONDITION;

public class CityFactory {
	public static CityFactory INSTANCE = new CityFactory();
	private static Logger LOG = Logger.getLogger(CityFactory.class);

	private CityFactory() {
	}

	private static class GenerateBusStationTask extends RecursiveAction {

		private static final long serialVersionUID = 7037400764019556084L;
		public static int THRESHOLD = 200;
		private City city;
		private int offset;
		private int size;
		private Random r = new Random(999L);

		public GenerateBusStationTask(City city, int busStationCount) {
			this.city = city;
			this.offset = 0;
			this.size = busStationCount;
		}
		
		public GenerateBusStationTask(City city, int startBusStationPosition, int busStationCount) {
			this.city = city;
			this.offset = startBusStationPosition;
			this.size = busStationCount;
		}

		@Override
		protected void compute() {
			if (size - offset<THRESHOLD){
				PlaceFactory placeFactory = PlaceFactory.INSTANCE;
				for (int i = offset; i<size; i++){
					BusStation bs = placeFactory.createBusStation(("Bus_Station_#_" + (i+1)), r.nextDouble(), r.nextDouble());
					city.addPlace(bs);
				}
			} else {
				int middleOfCount = (size - offset)/ 2;
				GenerateBusStationTask subTask1 = new GenerateBusStationTask(city, offset, offset + middleOfCount);
				subTask1.fork();
				GenerateBusStationTask subTask2 = new GenerateBusStationTask(city, offset + middleOfCount, size);
				subTask2.fork();
				
				subTask1.join();
				subTask2.join();
			}
			
		}
	}

	private void generateBusStationsForCity(City city, int busStationCount,
			int parallelThreadsCount) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(parallelThreadsCount);
		GenerateBusStationTask busStationGenerator = new GenerateBusStationTask(
				city, busStationCount);
		forkJoinPool.invoke(busStationGenerator);
		LOG.info("There Are " + city.getBusStationSet().size()
				+ " bus station has been generated for city " + city.getName());
	}

	private int getThreadCount(int busStationCount) {
		if (busStationCount > 1000)
			return busStationCount / 100;
		else if (busStationCount > 100)
			return busStationCount / 10;
		else
			return 10;
	}

	public City createCity(String country, String region, String cityName,
			int[] busDepotArray, int busStationCount) {
		City city = new City(country, region, cityName);

		generateBusStationsForCity(city, busStationCount, getThreadCount(busStationCount));
		Random r = new Random(999L);
		final PlaceFactory placeFactory = PlaceFactory.INSTANCE;
		final VehicleFactory vehicleFactory = VehicleFactory.INSTANCE;
		for (int i = 0; i < busDepotArray.length; i++) {
			BusDepot bDepot = placeFactory.createBusDepot("BusDepot_#_"
					+ (i + 1), r.nextDouble(), r.nextDouble());
			int busCount = busDepotArray[i];
			for (int b = 0; b < busCount; b++) {
				final VEHICLE_CONDITION condition = getRandomVehicleCondition();
				LinkedList<BusStation> route = generateBusRoute(city);
				SmallBus smallBus = vehicleFactory.createSmallBus(condition,
						route);
				bDepot.addBusToDepot(smallBus);
			}
			city.addPlace(bDepot);
		}
		return city;
	}

	private LinkedList<BusStation> generateBusRoute(City city) {
		LinkedList<BusStation> route = new LinkedList<BusStation>();
		Set<BusStation> cityBusStationSet = city.getBusStationSet();
		int bsCount = new Random().nextInt(20) + 20;
		for (int i = 0; i < bsCount; i++) {
			BusStation bs = getRandomBusStation(cityBusStationSet, route);
			route.add(bs);
		}
		return route;
	}

	private BusStation getRandomBusStation(Set<BusStation> cityBusStationSet,
			LinkedList<BusStation> alreadyAddedBusStation) {
		assert cityBusStationSet != null;
		assert alreadyAddedBusStation != null;
		List<BusStation> list = cityBusStationSet.parallelStream()
				.filter(it -> !alreadyAddedBusStation.contains(it))
				.collect(Collectors.toList());
		assert list.size() > 0;
		int bsPosition = new Random().nextInt(list.size());
		return list.get(bsPosition);
	}

	private VEHICLE_CONDITION getRandomVehicleCondition() {
		int vehicleConditionSize = VEHICLE_CONDITION.values().length;
		Random r = new Random();
		int randomI = r.nextInt(vehicleConditionSize);
		return VEHICLE_CONDITION.values()[randomI];
	}
}
