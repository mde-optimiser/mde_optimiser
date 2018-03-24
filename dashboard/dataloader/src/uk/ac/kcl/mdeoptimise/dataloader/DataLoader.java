package uk.ac.kcl.mdeoptimise.dataloader;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
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
    
	public static void main(String[] args) {
		try {
			// Set the properties ...
        	Properties properties = new Properties();
        	properties.put(Context.INITIAL_CONTEXT_FACTORY, Consts.INITIAL_CONTEXT_FACTORY);
        	properties.put("connectionfactory."+ Consts.CONNECTION_JNDI_NAME , Consts.QUEUE_CONNECTION_NAME);
        	properties.put("queue."+ Consts.QUEUE_JNDI_NAME , Consts.QUEUE_NAME);
        
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

			System.out.println("[DataLoader] Connected to queue");
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