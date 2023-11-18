package org.CaballeroNillukka.control;

import org.CaballeroNillukka.model.Location;

import java.time.Instant;

public class WeatherController {

	//Constructure
	private Location location;
	private int days;
	private WeatherProvider weatherProvider;
	private WeatherStore weatherStore;
	public WeatherController(Location location, int days, WeatherProvider weatherProvider, WeatherStore weatherStore) {
		this.location = location;
		this.days = days;
		this.weatherProvider = weatherProvider;
		this.weatherStore = weatherStore;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public WeatherProvider getWeatherProvider() {
		return weatherProvider;
	}
	public void setWeatherProvider(WeatherProvider weatherProvider) {
		this.weatherProvider = weatherProvider;
	}
	public WeatherStore getWeatherStore() {
		return weatherStore;
	}
	public void setWeatherStore(WeatherStore weatherStore) {
		this.weatherStore = weatherStore;
	}

	//Methods
	public void execute(){
		getWeatherProvider().getWeatherData(location, Instant.now());
		// llamar open weather map y descarga los datos
		// guardar en sqlite
	}
}
