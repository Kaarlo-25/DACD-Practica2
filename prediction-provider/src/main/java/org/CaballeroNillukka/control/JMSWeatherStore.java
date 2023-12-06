package org.CaballeroNillukka.control;

import org.CaballeroNillukka.model.Weather;
import org.apache.activemq.ActiveMQConnectionFactory;
import com.google.gson.Gson;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;


public class JMSWeatherStore implements EventPublisher {
	//Constructor
	private final String brokerURL;	// default broker URL is : tcp://localhost:61616"
	private final String subject; // Queue Name.You can create any/many queue names as per your requirement.
	public JMSWeatherStore(String brokerURL, String subject) {
		this.brokerURL = brokerURL;
		this.subject = subject;
	}

	//Methods
	@Override
	public void publishWeatherData(Weather weather) {
		Gson gson = new Gson();
		String event = gson.toJson(weather);
		try {
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);	//crea un objeto activeMQ
			Connection connection = connectionFactory.createConnection();	//establecer conexiones con un servidor de ActiveMQ.
			connection.start();	//Empieza la API
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //Creating a non-transactional session to send/receive JMS message.
			Destination destination = session.createQueue(subject); //Destination represents here our queue 'JCG_QUEUE' on the JMS server.
			MessageProducer producer = session.createProducer(destination); // MessageProducer is used for sending messages to the queue.
			TextMessage message = session.createTextMessage(event);
			producer.send(message);
			connection.close();
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}


}
