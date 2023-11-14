package org.CaballeroNillukka.control;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.CaballeroNillukka.model.Location;
import org.CaballeroNillukka.model.Weather;


public class Main {
	public static List<Location> locationsList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		// cargar locations
		loadLocationsFromFile("C:\\Users\\valko\\IdeaProjects\\DACD-Practica1\\src\\main\\resources\\Locations.tsv");

		// Crear base de datos y tablas vacias
		SQLiteWeatherStore.createDatabase();

		//llamar controller, 5 dias
		WeatherProvider weatherProvider = new OpenWeatherMapProvider();
		WeatherStore weatherStore = new SQLiteWeatherStore();
		for (int i=0; i<8; i++){
			WeatherController weatherController = new WeatherController(locationsList.get(i), 5, weatherProvider, weatherStore);
		}
	}

	public static void loadLocationsFromFile(String filePath) throws IOException {
		try {
			List<String> lines = Files.readAllLines(Path.of(filePath));
			for (String line : lines) {
				List<String> words = List.of(line.split("\t"));
				String name = words.get(0);
				float latitude = Float.parseFloat(words.get(1));
				float longitude = Float.parseFloat(words.get(2));
				locationsList.add(new Location(name, latitude, longitude));
			}
			System.out.println("Locations from file saved succesfully.");
		} catch (Exception e) {
			System.out.println("There was an error getting locations from files.\n");
		}
	}
}

