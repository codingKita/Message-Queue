import org.xml.sax.SAXException;

import javax.jms.JMSException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws JMSException, SQLException, ParserConfigurationException, IOException, SAXException {
        Conn conn = new Conn();
        conn.activeMQConnection();
    }
}
