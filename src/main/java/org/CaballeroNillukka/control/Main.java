package org.CaballeroNillukka.control;

import java.util.Scanner;

import static org.CaballeroNillukka.control.OpenWeatherMapProvider.*;


public class Main {
	public static String apiKey;
	public static String databasePath;
	public static void main(String[] args) {
		//apiKey = args[0];
		//databasePath = args[1];
		System.out.print("\nENUNCIADO: Obtener cada 6H la predicción meteorológica de los 5 próximos días a las 12:00H para cada una de las 8 islas.\n");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Please, introduce your API Key: ");
		apiKey = scanner.nextLine();
		System.out.print("Please, introduce your database path: ");
		databasePath = scanner.nextLine();

		//TODO how to read locations file once the code is compiled
		//TODO eliminate as much static methods as possible

		WeatherProvider weatherProvider = new OpenWeatherMapProvider();
		WeatherStore weatherStore = new SQLiteWeatherStore();
		WeatherController.executePreparation();
		WeatherController weatherController = new WeatherController(locationsList, weatherProvider, weatherStore);
		weatherController.execute();
	}
}

