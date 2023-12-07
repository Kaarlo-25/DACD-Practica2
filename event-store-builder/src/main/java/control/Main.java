package control;

import org.apache.activemq.ActiveMQConnection;

public class Main {
	public static String brokerURL;
	public static String subject;
	public static String eventsPath;
	public static void main(String[] args){
		brokerURL = ActiveMQConnection.DEFAULT_BROKER_URL;
		subject = "JCG_QUEUE";
		eventsPath = "C:\\Users\\valko\\OneDrive\\Documentos\\UNIVERSIDAD\\DACD\\EveryEvent.events";

		JMSReciever eventsSuscriptor = new JMSReciever(brokerURL, subject);
		EventsStore eventsStorer = new EventsStore(eventsPath);
		EventsController eventsController = new EventsController(eventsStorer, eventsSuscriptor);
		eventsController.execute();
	}
}
