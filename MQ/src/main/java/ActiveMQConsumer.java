import org.xml.sax.SAXException;

import javax.jms.*;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class ActiveMQConsumer {
    public void getMessageConsumer(MessageConsumer mConsumer) throws JMSException, SQLException, ParserConfigurationException, IOException, SAXException {

        Database database = new Database();

        // Check if the message is a text message
        while (true) {
            // Wait for a message
            Message message = (Message) mConsumer.receive();
            if (message != null) {
                if (message instanceof TextMessage) {
                    TextMessage textMessage = (TextMessage) message;
                        System.out.println("Received: " + textMessage.getText());
                        String messageContent = textMessage.getText();
                        database.insertIntoDB(messageContent);
                } else {
                    System.out.println("Received: " + message);
                }
            }
        }
    }
}