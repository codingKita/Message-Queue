import org.apache.activemq.ActiveMQConnectionFactory;
import org.xml.sax.SAXException;

import javax.jms.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class Conn {

    public void activeMQConnection() throws JMSException, SQLException, ParserConfigurationException, IOException, SAXException {

        ActiveMQConsumer activeMQConsumer = new ActiveMQConsumer();
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

        // Create a Connection
        javax.jms.Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Destination destination = session.createQueue("testQueue");

        // Create a MessageConsumer from the Session to the Topic or Queue
        MessageConsumer  mConsumer= session.createConsumer(destination);

        activeMQConsumer.getMessageConsumer(mConsumer);
    }

    public Connection dbConnection() {

        String url = "jdbc:sqlserver://localhost:1433;databaseName=MQ";
        String username = "MQadmin";
        String password = "admin";

        Connection connection = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //Connect to DB
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("success connect to db");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
