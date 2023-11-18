package org.CaballeroNillukka.control;

import org.CaballeroNillukka.model.Location;
import org.CaballeroNillukka.model.Weather;

import java.time.Instant;

public interface WeatherStore {
	void storeWeatherData(Weather weather);
}
