package control;


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
		eventSuscriptor.startListening(eventStorer);
	}
}
