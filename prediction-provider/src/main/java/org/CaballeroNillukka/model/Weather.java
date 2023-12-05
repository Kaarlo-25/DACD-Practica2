package org.CaballeroNillukka.model;

import java.time.Instant;

public class Weather {

	//Constructure
	private final float temperature;
	private final int hummidity;
	private final float rain;
	private final float windSpeed;
	private final float clouds;
	private final Location location;
	private final Instant timeStamp;
	public Weather(float temperature, int hummidity, float rain, float windSpeed, float clouds, Location location, Instant timeStamp) {
		this.temperature = temperature;
		this.hummidity = hummidity;
		this.rain = rain;
		this.windSpeed = windSpeed;
		this.clouds = clouds;
		this.location = location;
		this.timeStamp = timeStamp;
	}
	public float getTemperature() {
		return temperature;
	}
	public int getHummidity() {
		return hummidity;
	}
	public float getRain() {
		return rain;
	}
	public float getWindSpeed() {
		return windSpeed;
	}
	public float getClouds() {
		return clouds;
	}
	public Location getLocation() {
		return location;
	}
	public Instant getTimeStamp() {
		return timeStamp;
	}

	//Methods
	@Override
	public String toString() {
		return "Weather{" +
				"temperature=" + temperature +
				", hummidity=" + hummidity +
				", rain=" + rain +
				", windSpeed=" + windSpeed +
				", clouds=" + clouds +
				", location=" + location +
				", timeStamp=" + timeStamp +
				'}';
	}
}
