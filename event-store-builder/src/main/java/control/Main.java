package control;

public class Main {
	public static String brokerURL;
	public static String brokerSubject;
	public static String eventsPath;
	public static void main(String[] args){
		brokerURL = args[0];
		brokerSubject = args[1];
		eventsPath = args[2];

		JMSReciever eventsSuscriptor = new JMSReciever(brokerURL, brokerSubject);
		FileStorer eventsStorer = new FileStorer(eventsPath);
		EventsController eventsController = new EventsController(eventsStorer, eventsSuscriptor);
		eventsController.execute();
	}
}
