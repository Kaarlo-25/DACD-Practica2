package org.CaballeroNillukka.control;

import org.CaballeroNillukka.model.Location;
import org.CaballeroNillukka.model.Weather;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class WeatherController {

	//Constructure
	private final List<Location> locationsList;
	private final WeatherProvider weatherProvider;
	private final WeatherStore weatherStore;
	public WeatherController(List<Location> locationsList, WeatherProvider weatherProvider, WeatherStore weatherStore) {
		this.locationsList = locationsList;
		this.weatherProvider = weatherProvider;
		this.weatherStore = weatherStore;
	}

	public void execute() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				for (int i = 0; i < 8; i++) {
					List<Weather> weathers = weatherProvider.getWeatherData(locationsList.get(i));
					System.out.printf("+ %s: \n\t- Success obtaining the data.\n", locationsList.get(i).getName());
					for (Weather weather : weathers) {
						weatherStore.storeWeatherData(weather);
					}
					System.out.println("\t- Success storing the data.");
				}
			}
		};
		long periodicity = 6 * 60 * 60 * 1000;
		timer.scheduleAtFixedRate(task, 0, periodicity);
	}
}

