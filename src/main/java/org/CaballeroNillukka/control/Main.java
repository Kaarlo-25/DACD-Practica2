package org.CaballeroNillukka.control;

import static org.CaballeroNillukka.control.OpenWeatherMapProvider.*;


public class Main {
	public static void main(String[] args){
		System.out.println("\nENUNCIADO: Obtener cada 6H la predicción meteorológica de los 5 próximos días a las 12:00H para cada una de las 8 islas.\n");

		WeatherController.executePreparation();
		WeatherProvider weatherProvider = new OpenWeatherMapProvider();
		WeatherStore weatherStore =  new SQLiteWeatherStore();
		WeatherController weatherController = new WeatherController(locationsList, weatherProvider, weatherStore);
		weatherController.execute();
	}
}

