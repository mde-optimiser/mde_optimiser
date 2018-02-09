package uk.ac.kcl.mdeoptimise.dataloader;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Listener used by a message consumer, which inserts every new message into the database.
 * 
 * @author Tamara
 *
 */
public class QueueMessageListener implements MessageListener {

	@Override
	public void onMessage(Message receivedMessage) {
		TextMessage message = (TextMessage) receivedMessage;
			try {
				Database.insertIntoDatabase(message.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}