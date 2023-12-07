package control;

import com.google.gson.JsonObject;

import java.io.*;

public class EventsStore implements EventStorer {
	//Constructor
	private final String eventsPath;

	public EventsStore(String eventsPath) {
		this.eventsPath = eventsPath;
	}

	@Override
	public void storeEvents(String event) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventsPath, true))) {
			writer.write(event);
			writer.newLine();
			writer.newLine();
			System.out.println("Escritura en el archivo exitosa.");
		} catch (IOException e) {
			System.err.println("Error al escribir en el archivo: " + e);
		}
	}
}
