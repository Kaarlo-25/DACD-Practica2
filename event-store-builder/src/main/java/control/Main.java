package control;

public class Main {
	public static String brokerURL = "tcp://localhost:61616";
	public static String brokerSubject = "Topic:prediction.Weather";
	public static String eventsPath = "eventstore/prediction.Weather/%s/%s.events";
	public static void main(String[] args){
		JMSReceiver eventsSuscriptor = new JMSReceiver(brokerURL, brokerSubject);
		FileStorer eventsStorer = new FileStorer(eventsPath);
		EventsController eventsController = new EventsController(eventsStorer, eventsSuscriptor);
		eventsController.execute();
	}
}
