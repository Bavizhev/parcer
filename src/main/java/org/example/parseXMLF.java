package org.example;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static org.example.listToJson.listToJson;

public class parseXMLF {
    public static List<Employee> parseXML(String filename, String newfilename, String[] columnMapping) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filename));
            Node root = document.getDocumentElement();
            List<Employee> list = new ArrayList<>();
            entry(root, list, newfilename, columnMapping);
            return list;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void entry(Node root, List<Employee> list, String newfilename, String[] columnMapping) {

        NodeList nodeList = root.getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (Node.ELEMENT_NODE == node.getNodeType()) {
                Element employee = (Element) node;
                list.add(new Employee(parseInt(employee.getElementsByTagName(columnMapping[0]).item(0).getTextContent()),
                        employee.getElementsByTagName(columnMapping[1]).item(0).getTextContent(),
                        employee.getElementsByTagName(columnMapping[2]).item(0).getTextContent(),
                        employee.getElementsByTagName(columnMapping[3]).item(0).getTextContent(),
                        (parseInt(employee.getElementsByTagName(columnMapping[4]).item(0).getTextContent()))));
            }
        }
        listToJson(list, newfilename);
    }
}
