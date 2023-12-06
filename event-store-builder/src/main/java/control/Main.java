package control;

import org.apache.activemq.ActiveMQConnection;

public class Main {
	public static void main(String[] args){
		JMSReciever eventsSuscriptor = new JMSReciever(ActiveMQConnection.DEFAULT_BROKER_URL, "JCG_QUEUE");
		eventsSuscriptor.getEvents();
		//EventsStore eventsStorer = new EventsStore();
		//EventsController eventsController = new EventsController(event, eventsStorer, eventsSuscriptor);
		//eventsController.execute();
		//TODO change execute to not static
	}
}
