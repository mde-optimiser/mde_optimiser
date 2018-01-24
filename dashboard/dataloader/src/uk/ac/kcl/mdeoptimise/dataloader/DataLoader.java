package uk.ac.kcl.mdeoptimise.dataloader;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * Listens for new data from the queue and updates the database.
 * 
 * @author Tamara
 *
 */
public class DataLoader {
    private static final int DELIVERY_MODE = DeliveryMode.NON_PERSISTENT;
    static final String INITIAL_CONTEXT_FACTORY = "org.apache.qpid.jms.jndi.JmsInitialContextFactory";

    static final String CONNECTION_JNDI_NAME = "myFactoryLookup";
    static final String CONNECTION_NAME = "amqp://queue:5672";

	static final String QUEUE_JNDI_NAME = "myQueueLookup";
	static final String QUEUE_NAME = "queue";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
            MessageConsumer messageConsumer = session.createConsumer(queue);
            messageConsumer.setMessageListener(new QueueMessageListener());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static class MyExceptionListener implements ExceptionListener {
        @Override
        public void onException(JMSException exception) {
            System.out.println("Connection ExceptionListener fired, exiting.");
            exception.printStackTrace(System.out);
            System.exit(1);
        }
    }
}
