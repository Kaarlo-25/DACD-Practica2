package org.CaballeroNillukka.control;

import org.CaballeroNillukka.model.Location;
import org.CaballeroNillukka.model.Weather;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;

import static org.CaballeroNillukka.control.Main.locationsList;

public class SQLiteWeatherStore implements WeatherStore {
	public static void createDatabase(){
		String dbPath = "database.db";
		try (Connection connection = connect(dbPath)){
			Statement statement = connection.createStatement();
			createTable(statement);
			//insert(statement);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection connect(String dbPath) {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:" + dbPath;
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
			return conn;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	static void createTable(Statement statement) throws SQLException {
		for (int i=0; i<8; i++){
			String name = locationsList.get(i).getName();
			statement.execute(String.format("CREATE TABLE IF NOT EXISTS `%s` (" +
					"Instant INTEGER NOT NULL,\n" +
					"Temperature FLOAT NOT NULL,\n" +
					"Humidity INTEGER NOT NULL,\n" +
					"Rain FLOAT NOT NULL,\n" +
					"WindSpeed FLOAT NOT NULL,\n" +
					"Clouds FLOAT NOT NULL,\n" +
					"Location STRING NOT NULL\n" +
					");", name));
		}
	}

	private static boolean insert(Statement statement) throws SQLException {
		return statement.execute("INSERT INTO products (id,name ,prize)\n" +
				"VALUES(1, 'hibike', 1995.9),(2, 'orbea', 995.5);");
	}

	private static void update(Statement statement) throws SQLException {
		statement.execute("UPDATE products\n" +
				"SET name = 'orbea500' \n" +
				"WHERE" + " name='orbea';");
		System.out.println("Table products updated");
	}

	@Override
	public void storeWeatherData(Weather weather, Location location, Instant timestamp) {

	}

	/*private static void delete(Statement statement) throws SQLException {
		statement.execute("DELETE FROM products\n" +
				"WHERE id=1;");
	}*/

}
