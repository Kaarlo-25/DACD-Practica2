package org.CaballeroNillukka.control;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.CaballeroNillukka.model.Location;


public class Main {
	public static List<Location> locationsList = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		// cargar locations - Done
		System.out.println("ENUNCIADO: Cada 6H consultar la API para obtener la predicción meteorológica de las 8 islas para los 5 próximos días a las 12pm de cada día.\n");
		loadLocationsFromFile("C:\\Users\\valko\\IdeaProjects\\DACD-Practica1\\src\\main\\resources\\Locations.tsv");

		// Crear base de datos y tablas vacias - Done
		SQLiteWeatherStore.createDatabase();

		//llamar controller, 5 dias
		WeatherProvider weatherProvider = new OpenWeatherMapProvider();
		WeatherStore weatherStore = new SQLiteWeatherStore();

		for (int i=0; i<8; i++){
			WeatherController weatherController = new WeatherController(locationsList.get(i), 5, weatherProvider, weatherStore);
			weatherController.execute();
		}
	}

	public static void loadLocationsFromFile(String filePath){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filePath));
			String fileLine;
			while ((fileLine = reader.readLine()) != null && !fileLine.equals("\n")) {
				String name = List.of(fileLine.split("\t")).get(0);
				float latitude = Float.parseFloat(List.of(fileLine.split("\t")).get(1));
				float longitude = Float.parseFloat(List.of(fileLine.split("\t")).get(2));
				Location location = new Location(name, latitude, longitude);
				locationsList.add(location);
			}
			System.out.println("Locations from file saved succesfully.");
		} catch (Exception e) {
			System.out.println(e.getMessage()+"There was an error getting locations from files.\n");
		}
	}
}

