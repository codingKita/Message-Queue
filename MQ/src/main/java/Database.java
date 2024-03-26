import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.sql.*;
import java.sql.Connection;

public class Database {

    public void insertIntoDB(String message) throws SQLException, ParserConfigurationException, IOException, SAXException {

            Conn conn = new Conn();
            Connection dbConn = conn.dbConnection();

//            while (true) {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();
                    InputSource is = new InputSource(new StringReader(message));
                    Document document = builder.parse(is);

                    Element root = document.getDocumentElement();
                    // Get the 'id' element
                    NodeList idList = root.getElementsByTagName("id");
                    int id = -1; // Default value if 'id' element is not found or empty
                    if (idList.getLength() > 0) {
                        id = Integer.parseInt(idList.item(0).getTextContent());
                    } else {
                            System.out.println("id not found");
                    }

                    // Get the 'name' element
                    NodeList nameList = root.getElementsByTagName("name");
                    String name = ""; // Default value if 'name' element is not found or empty
                    if (nameList.getLength() > 0) { // Changed from '0' to '1' to avoid IndexOutOfBoundsException
                        name = nameList.item(0).getTextContent();
                    } else {
                            System.out.println("name not found");
                    }

                    // Get the 'div' element
                    NodeList divList = root.getElementsByTagName("div");
                    String div = ""; // Default value if 'div' element is not found or empty
                    if (divList.getLength() > 0) { // Changed from '0' to '2' to avoid IndexOutOfBoundsException
                        div = divList.item(0).getTextContent();
                    } else {
                            System.out.println("div not found");
                    }

                    String insertSql = "INSERT INTO employee VALUES (?, ?, ?)";
                    PreparedStatement preparedStatement = dbConn.prepareStatement(insertSql);
                    preparedStatement.setInt(1, id);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, div);
                    preparedStatement.executeUpdate();

                    // Close the connections
                    preparedStatement.close();
                    dbConn.close();

                }
        }
//    }
