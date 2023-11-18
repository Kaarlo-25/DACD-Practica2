package org.CaballeroNillukka.model;

import java.time.Instant;

public class Weather {

	//Constructure
	private float temperature;
	private int hummidity;
	private float rain;
	private float windSpeed;
	private float clouds;
	private Location location;
	private Instant timeStamp;
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
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	public int getHummidity() {
		return hummidity;
	}
	public void setHummidity(int hummidity) {
		this.hummidity = hummidity;
	}
	public float getRain() {
		return rain;
	}
	public void setRain(float rain) {
		this.rain = rain;
	}
	public float getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}
	public float getClouds() {
		return clouds;
	}
	public void setClouds(float clouds) {
		this.clouds = clouds;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Instant getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Instant timeStamp) {
		this.timeStamp = timeStamp;
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
