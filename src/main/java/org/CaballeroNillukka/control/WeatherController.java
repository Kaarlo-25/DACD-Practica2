package org.CaballeroNillukka.control;

import org.CaballeroNillukka.model.Location;
import org.CaballeroNillukka.model.Weather;
import java.util.List;

public class WeatherController {

	//Constructure
	private final List<Location> locations;
	private final WeatherProvider weatherProvider;
	private final WeatherStore weatherStore;
	public WeatherController(List <Location> locations, WeatherProvider weatherProvider, WeatherStore weatherStore) {
		this.locations = locations;
		this.weatherProvider = weatherProvider;
		this.weatherStore = weatherStore;
	}

	//Methods
	public static void executePreparation(){
		OpenWeatherMapProvider.loadLocationsFromFile("src/main/resources/Locations.tsv");
		SQLiteWeatherStore.createDatabase();
	}

	public void execute(){
		for (int i=0; i<8; i++){
			List <Weather> weathers = weatherProvider.getWeatherData(this.locations.get(i));
			System.out.printf("+ %s: \n\t- Success obtaining the data.\n", this.locations.get(i).getName());
			for (Weather weather: weathers){
				weatherStore.storeWeatherData(weather);
			}
			System.out.println("\t- Success storing the data.");
		}
	}
}
