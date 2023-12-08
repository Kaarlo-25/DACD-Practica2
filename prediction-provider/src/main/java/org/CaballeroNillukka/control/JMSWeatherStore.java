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
import java.sql.SQLOutput;


public class JMSWeatherStore implements EventPublisher {
	//Constructor
	private final String brokerURL;
	private final String subject;
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
			ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(subject);
			MessageProducer producer = session.createProducer(destination);
			TextMessage message = session.createTextMessage(event);
			producer.send(message);
			connection.close();
		} catch (JMSException e) {
			System.out.println("ERROR: "+e);
		}
	}

}
