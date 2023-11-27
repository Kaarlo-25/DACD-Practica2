package org.CaballeroNillukka.control;

import org.CaballeroNillukka.model.Weather;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SQLiteWeatherStore implements WeatherStore {
	public static String dbPath = "database.db";
	public static void createDatabase(){
		try (Connection connection = connect(dbPath)){
			Statement statement = connection.createStatement();
			createTables(statement);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection connect(String dbPath) {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:" + dbPath;
			conn = DriverManager.getConnection(url);
			return conn;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	static void createTables(Statement statement) throws SQLException {
		for (int i=0; i<8; i++){
			String name = OpenWeatherMapProvider.locationsList.get(i).getName();
			statement.execute(String.format("CREATE TABLE IF NOT EXISTS `%s` (" +
					"Day TEXT PRIMARY KEY," +
					"Temperature TEXT," +
					"Humidity TEXT," +
					"Rain TEXT," +
					"WindSpeed TEXT," +
					"Clouds TEXT," +
					"Latitude TEXT," +
					"Longitude TEXT);", name));
		}
	}
	@Override
	public void storeWeatherData(Weather weather) {
		try (Connection connection = connect(dbPath)){
			Statement statement = connection.createStatement();
			updateTable(statement, prepareTableValues(weather));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private static void updateTable(Statement statement, List<String> listOfValues) throws SQLException {
		// TODO resolve date format when updating
		statement.execute(String.format("INSERT OR REPLACE INTO `%s` (Day, Temperature, Rain, Humidity, Clouds, WindSpeed, Latitude, Longitude) " +
						"VALUES (%s, %s, %s, %s, %s, %s, %s, %s);", listOfValues.get(0), listOfValues.get(1), listOfValues.get(2),
				listOfValues.get(3), listOfValues.get(4), listOfValues.get(5), listOfValues.get(6), listOfValues.get(7), listOfValues.get(8)));
	}

	private static List<String> prepareTableValues(Weather weather){
		String name = weather.getLocation().getName();

		Instant instant = weather.getTimeStamp();
		LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String day = formatter.format(localDate);

		String temperature = Float.toString(weather.getTemperature());
		String humidity = Integer.toString(weather.getHummidity());
		String rain = Float.toString(weather.getRain());
		String windSpeed = Float.toString(weather.getWindSpeed());
		String clouds = Float.toString(weather.getClouds());
		String latitude = Float.toString(weather.getLocation().getLatitude());
		String longitude = Float.toString(weather.getLocation().getLongitude());
		return new ArrayList<>(List.of(name, day, temperature, rain, humidity, clouds, windSpeed, latitude, longitude));
	}

}
