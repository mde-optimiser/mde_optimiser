package uk.ac.kcl.mdeoptimise.dashboard.api.sender;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * 
 * Queue functionalities, such as establishing a connection and sending messages.
 * 
 * @author Tamara
 *
 */
public class Sender {
    private static final int DELIVERY_MODE = DeliveryMode.NON_PERSISTENT;
    final String INITIAL_CONTEXT_FACTORY = "org.apache.qpid.jms.jndi.JmsInitialContextFactory";
	final String CONNECTION_JNDI_NAME = "myFactoryLookup";
	final String QUEUE_JNDI_NAME = "myQueueLookup";
	final String QUEUE_NAME = "queue";
	
	// Use the following connection name when running via Docker.
	//final String CONNECTION_NAME = "amqp://queue:5672";
	
	// Use the following connection name when running locally.
	final String CONNECTION_NAME = "amqp://localhost:5672";


    public void sendMessage(String message) {

        try {
        	// Set the properties ...
        	Properties properties = new Properties();
        	properties.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        	properties.put("connectionfactory."+CONNECTION_JNDI_NAME , CONNECTION_NAME);
        	properties.put("queue."+QUEUE_JNDI_NAME , QUEUE_NAME);
        
        	// Create the initial context
        	Context context = new InitialContext(properties);

            ConnectionFactory factory = (ConnectionFactory) context.lookup("myFactoryLookup");
            Destination queue = (Destination) context.lookup("myQueueLookup");

            Connection connection = factory.createConnection("admin", "admin");
            connection.setExceptionListener(new MyExceptionListener());
            connection.start();

            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);
            
            TextMessage messageReady = session.createTextMessage(message);
            
            messageProducer.send(messageReady, DELIVERY_MODE, Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);
            
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static class MyExceptionListener implements ExceptionListener {
        @Override
        public void onException(JMSException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}