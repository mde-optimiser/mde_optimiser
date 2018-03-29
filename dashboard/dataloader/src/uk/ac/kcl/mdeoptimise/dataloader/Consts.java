package uk.ac.kcl.mdeoptimise.dataloader;

/**
 * Constants of general utility.
 *
 * @author Tamara
 *
 */
public final class Consts {

    static final String INITIAL_CONTEXT_FACTORY = "org.apache.qpid.jms.jndi.JmsInitialContextFactory";
    static final String CONNECTION_JNDI_NAME = "myFactoryLookup";
	static final String QUEUE_JNDI_NAME = "myQueueLookup";
	static final String QUEUE_NAME = "queue";

	/** Use the following setting when running via Docker. */
	static final String QUEUE_CONNECTION_NAME = "amqp://queue:5672";
    static final String DATABASE_CONNECTION_URL = "jdbc:h2:./test";
	static final String DATABASE_USERNAME = "sa";
    static final String DATABASE_PASSWORD = "";

	/** Use the following setting when running locally. */
    /*static final String QUEUE_CONNECTION_NAME = "amqp://localhost:5672";
	static final String DATABASE_CONNECTION_URL = "jdbc:h2:tcp://localhost/~/test";
	static final String DATABASE_USERNAME = "mdeo";
    static final String DATABASE_PASSWORD = "mdeo";*/
}
