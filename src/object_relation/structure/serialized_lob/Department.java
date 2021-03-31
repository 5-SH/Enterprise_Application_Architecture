package object_relation.structure.serialized_lob;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Department {
  private String name;
  private List subsidiaries = new ArrayList();

  public Department(String name) {
    this.name = name;
  }

  public Department(String name, List subsidiaries) {
    this.name = name;
    this.subsidiaries = subsidiaries;
  }

  public Element toXmlElement(Document doc) {
    Element root = doc.createElement("department");
    root.setAttribute("name", name);

    Iterator i = subsidiaries.iterator();
    while (i.hasNext()) {
      Department dep = (Department) i.next();
      root.appendChild(dep.toXmlElement(doc));
    }

    return root;
  }

  public static Department readXml(Element source) {
    String name = source.getAttribute("name");
    Department result = new Department(name);
    NodeList depList = source.getElementsByTagName("department");
    for (int i = 0; i < depList.getLength(); i++) {
      Element element = (Element) depList.item(i);
      result.subsidiaries.add(Department.readXml(element));
    }

    return result;
  }

  @Override
  public String toString() {
    return "Department{" +
      "name='" + name + '\'' +
      ", subsidiaries=" + subsidiaries +
      '}';
  }
}
