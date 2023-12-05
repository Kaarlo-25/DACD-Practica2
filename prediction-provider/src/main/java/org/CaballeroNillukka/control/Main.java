package org.CaballeroNillukka.control;

import org.CaballeroNillukka.model.Location;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
	public static String apiKey;
	public static String databasePath;
	public static List<Location> locationsList = new ArrayList<>();
	public static void main(String[] args) {
		apiKey = args[0];
		databasePath = args[1];
		System.out.println("\nENUNCIADO: Obtener cada 6H la predicción meteorológica de los 5 próximos días a las 12:00H para cada una de las 8 islas.\n");

		List<Location> locationsList = loadLocations();
		OpenWeatherMapProvider weatherProvider = new OpenWeatherMapProvider(apiKey);
		SQLiteWeatherStore weatherStore = new SQLiteWeatherStore(new File(databasePath));
		weatherStore.init(locationsList);
		WeatherController weatherController = new WeatherController(locationsList, weatherProvider, weatherStore);
		weatherController.execute();

		//TODO sustituir SQLiteWeatherStore por JMSWeatherStore
		//TODO conectar JMSWeatherStore con un broker
		//TODO llamar JMSWeatherStore desde aqui, el Main
		//TODO comprimir modulo de la practica 1 y meterlo en el pom
	}

	public static List<Location> loadLocations(){
		String locationsFile = "Locations.tsv";
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(locationsFile);
		if (inputStream != null) {
			try {
				int charactersRead;
				String fileLine = "";
				while ((charactersRead = inputStream.read()) != -1) {
					char character = (char) charactersRead;
					fileLine = fileLine + character;
					if (String.valueOf(character).equals("\n")){
						String name = List.of(fileLine.split("\t")).get(0);
						float latitude = Float.parseFloat(List.of(fileLine.split("\t")).get(1));
						float longitude = Float.parseFloat(List.of(fileLine.split("\t")).get(2));
						Location location = new Location(name, latitude, longitude);
						locationsList.add(location);
						fileLine = "";
					}
				}
				inputStream.close();
				System.out.println("Success getting locations.\n");
				return locationsList;
			} catch (IOException e) {
				System.out.println(e+"Failure reading Locations.tsv file.\n");
			}
		} else {
			System.out.println("It is not possible to find Locations.tsv file.\n");
		}
		return null;
	}
}

