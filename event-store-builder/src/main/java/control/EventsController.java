package control;

import com.google.gson.JsonObject;

public class EventsController {
	//Constructor
	private final JsonObject event;
	private final EventStorer weatherStorer;
	private final EventSuscriptor weatherSuscriptor;
	public EventsController(JsonObject event, EventStorer weatherstorer, EventSuscriptor weatherSuscriptor) {
		this.event = event;
		this.weatherStorer = weatherstorer;
		this.weatherSuscriptor = weatherSuscriptor;
	}

	//Methods
	public static void execute(){

	}

}
