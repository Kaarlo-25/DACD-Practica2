package control;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.List;

public class JMSReciever implements EventSuscriptor{
	// Constructor
	private String brokerURL;
	private String subject;
	public JMSReciever(String url, String subject) {
		this.brokerURL = url;
		this.subject = subject;
	}

	@Override
	public List<String> getEvents() {
		try{
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(subject);
			MessageConsumer consumer = session.createConsumer(destination);
			Message message = consumer.receive();
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				System.out.println("\nReceived message '" + textMessage.getText() + "'\n");
			}
			connection.close();
		}catch (Exception e){
			System.out.println(e);
		}

		return null;
	}
}
