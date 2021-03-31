package object_relation.structure.serialized_lob;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Customer extends DomainObject {
  private String name;
  private List departments = new ArrayList();

  public Customer(Long id, String name) {
    super(id);
    this.name = name;
  }

  public Customer(String name, List departments) {
    super(new Long(-1));
    this.name = name;
    this.departments = departments;
  }


  private Long findNextDatabaseId() {
    PreparedStatement stmt = null;
    Long result = null;
    try {
      stmt = Registry.DB().prepareStatement("SELECT IFNULL(MAX(id) + 1, 1) AS nextID FROM customers");
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

  private final static String findStatementString = "SELECT id, name, departments FROM customers WHERE id = ?";

  public static Customer find(Long id) {
    Customer result = null;
    try {
      PreparedStatement stmt = Registry.DB().prepareStatement(findStatementString);
      stmt.setLong(1, id);
      ResultSet rs = stmt.executeQuery();
      rs.next();
      result = load(rs);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  public static Customer load(ResultSet rs) throws Exception {
    Long id = new Long(rs.getLong("id"));
    Customer result = (Customer) Registry.getCustomer(id);
    if (result != null) return result;

    String name = rs.getString("name");
    String departmentLob = rs.getString("departments");
    result = new Customer(id, name);
    result.readDepartments(readXml(departmentLob));


    return result;
  }

  public static Element readXml(String departmentLob) throws Exception {
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
    Document doc = docBuilder.parse(new ByteArrayInputStream(departmentLob.getBytes(StandardCharsets.UTF_8)));
    Element result = (Element) doc.getElementsByTagName("departmentList");
    return result;
  }

  public void readDepartments(Element source) {
    NodeList depList = source.getElementsByTagName("department");
    for (int i = 0; i < depList.getLength(); i++) {
      Element element = (Element) depList.item(i);
      departments.add(Department.readXml(element));
    }
  }

  @Override
  public String toString() {
    return "Customer{" +
      "name='" + name + '\'' +
      ", departments=" + departments +
      '}';
  }
}
