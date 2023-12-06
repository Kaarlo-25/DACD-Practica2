package org.CaballeroNillukka.control;

import jakarta.jms.JMSException;
import org.CaballeroNillukka.model.Location;
import org.apache.activemq.ActiveMQConnection;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Main {
	public static String apiKey;
	public static List<Location> locationsList = new ArrayList<>();
	public static void main(String[] args) throws JMSException {
		apiKey = "6c4a8333e1cf9e8ad0c584569528f8b8"; //args[0];
		System.out.println("\nENUNCIADO: Obtener cada 6H la predicción meteorológica de los 5 próximos días a las 12:00H para cada una de las 8 islas.\n");

		List<Location> locationsList = loadLocations();
		OpenWeatherMapProvider weatherProvider = new OpenWeatherMapProvider(apiKey);
		JMSWeatherStore weatherStore = new JMSWeatherStore(ActiveMQConnection.DEFAULT_BROKER_URL, "JCG_QUEUE");

		WeatherController weatherController = new WeatherController(locationsList, weatherProvider, weatherStore);
		weatherController.execute();

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

