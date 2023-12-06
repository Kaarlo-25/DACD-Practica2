package org.CaballeroNillukka.control;


import org.CaballeroNillukka.model.Weather;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;


public class JMSWeatherStore implements WeatherStore {
	//Constructor
	private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;

	// default broker URL is : tcp://localhost:61616"
	private static String subject = "JCG_QUEUE"; // Queue Name.You can create any/many queue names as per your requirement.

	public void execute(){
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
		Connection connection = null;
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE); //Creating a non-transactional session to send/receive JMS message.
			Destination destination = session.createQueue(subject); //Destination represents here our queue 'JCG_QUEUE' on the JMS server.
			MessageProducer producer = session.createProducer(destination); // MessageProducer is used for sending messages to the queue.
			TextMessage message = session
					.createTextMessage("Hello !!! Welcome to the world of ActiveMQ.");
			producer.send(message);
			System.out.println("JCG printing@@ '" + message.getText() + "'");
			connection.close();
		} catch (JMSException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public void storeWeatherData(Weather weather) {


	}
}
