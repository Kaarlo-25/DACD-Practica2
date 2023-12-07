package control;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.ArrayList;
import java.util.List;

public class JMSReciever implements EventSuscriptor {
	// Constructor
	private final String brokerURL;
	private final String subject;

	public JMSReciever(String url, String subject) {
		this.brokerURL = url;
		this.subject = subject;
	}

	@Override
	public List<String> getEvents() {
		List<String> events = new ArrayList<>();
		String event = null;
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
			Destination destination = session.createQueue(subject);
			MessageConsumer consumer = session.createConsumer(destination);
			while (true){
				Message message = consumer.receive(1000);
				if (message == null) {
					break;
				}
				if (message instanceof TextMessage) {
					TextMessage textMessage = (TextMessage) message;
					event = textMessage.getText();
					message.acknowledge();
					events.add(event);
				}
			}
			connection.close();
			return events;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
