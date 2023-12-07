package control;

import java.io.*;

public class FileStorer implements EventStorer {
	//Constructor
	private final String eventsPath;

	public FileStorer(String eventsPath) {
		this.eventsPath = eventsPath;
	}

	@Override
	public void storeEvents(String event) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(eventsPath, true))) {
			writer.write(event);
			writer.newLine(); writer.newLine();
		} catch (IOException e) {
			System.err.println("ERROR: " + e);
		}
	}
}
