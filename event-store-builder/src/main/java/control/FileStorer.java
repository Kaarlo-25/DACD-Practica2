package control;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorer implements EventStorer {
	//Constructor
	private String eventsPath;

	public FileStorer(String eventsPath) {
		this.eventsPath = eventsPath;
	}

	@Override
	public void storeEvents(String event) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(getEventsPath(event), true))) {
			writer.write(event);
			writer.newLine(); writer.newLine();
		} catch (IOException e) {
			System.err.println("ERROR: " + e);
		}
	}
	public String getEventsPath(String event) throws IOException {
		String fileName = event.substring(233, 243).replace("-", "");
		eventsPath = String.format(eventsPath, "OpenWeatherMapProvider", fileName);
		String fullPath = System.getProperty("user.home") + "/Downloads" + "/" + eventsPath;
		Path absolutePath = Paths.get(fullPath);
		Files.createDirectories(absolutePath.getParent());
		return String.valueOf(absolutePath);
	}
}
