package control;

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
		try{
			List<String> events =  eventSuscriptor.getEvents();
			if (events.isEmpty()){
				System.out.println("\n\t- Events list is empty.");
			} else {
				System.out.println("\n\t- Events received correctly.");
				for (String event : events){
					eventStorer.storeEvents(event);
				}
				System.out.println("\n\t- Events stored correctly in Downloads folder.\n");
			}
		}catch(Exception e){
			System.out.println("ERROR: " + e);
		}
	}
}
