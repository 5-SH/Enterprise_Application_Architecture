package object_relation.structure.serialized_lob;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer extends DomainObject {
  private String name;
  private List departments = new ArrayList();

  public Customer(String name, List departments) {
    super(new Long(-1));
    this.name = name;
    this.departments = departments;
  }

  private Long findNextDatabaseId() {
    PreparedStatement stmt = null;
    Long result = null;
    try {
      stmt = Registry.DB().prepareStatement("SELECT IFNULL(MAX(id) + 1, 0) AS nextID FROM customers");
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = rs.getLong("nextID");
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return result;
  }

  private final static String insertStatementString = "INSERT INTO customers VALUES (?, ?, ?);";

  public Long insert() {
    PreparedStatement stmt = null;
    try {
      stmt = Registry.DB().prepareStatement(insertStatementString);
      setId(findNextDatabaseId());
      stmt.setInt(1, getId().intValue());
      stmt.setString(2, name);
      stmt.setString(3, xmlStringer());
      stmt.execute();
      Registry.addCustomer(this);
    } catch (SQLException | ParserConfigurationException | TransformerException e) {
      e.printStackTrace();
    }

    return getId();
  }

  public String xmlStringer() throws ParserConfigurationException, TransformerException {
    Document doc = Registry.getDocument();
    doc.appendChild(departmentsToXmlElement(doc));

    TransformerFactory transFactory = TransformerFactory.newInstance();
    Transformer transformer = transFactory.newTransformer();
    transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
    StringWriter writer = new StringWriter();
    transformer.transform(new DOMSource(doc), new StreamResult(writer));
    String result = writer.getBuffer().toString();

    return result;
  }

  public Element departmentsToXmlElement(Document doc) {
    Element root = doc.createElement("departmentList");
    Iterator i = departments.iterator();
    while (i.hasNext()) {
      Department dep = (Department) i.next();
      root.appendChild(dep.toXmlElement(doc));
    }
    return root;
  }


}
