package control;

import com.google.gson.JsonObject;

import java.util.List;

public class EventsController {
	//Constructor
	private final EventStorer eventStorer;
	private final EventSuscriptor eventSuscriptor;
	public EventsController(EventStorer eventStorer, EventSuscriptor eventSuscriptor) {
		this.eventStorer = eventStorer;
		this.eventSuscriptor = eventSuscriptor;
	}

	//Methods
	public void execute(){
		List<String> events =  eventSuscriptor.getEvents();
		for (String event : events){
			System.out.println(event);
			eventStorer.storeEvents(event);
			System.out.println("\n\t- Event stored correctly.\n");
		}
	}
}
