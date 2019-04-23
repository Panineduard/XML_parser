package classes.services;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import classes.models.PageAccesses;
import classes.models.Profile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class XML_Parser {
    public static Profile parse(File fXmlFile) {
        Profile profile = new Profile();
        profile.setPageAccesses(new ArrayList<PageAccesses>());
        try {
            Document doc = getDocument(fXmlFile);
            profile.setName(fXmlFile.getName().split("\\.")[0]);

            NodeList nList = doc.getElementsByTagName("pageAccesses");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                PageAccesses pageAccesses = new PageAccesses();
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    pageAccesses.setApexPage(eElement.getElementsByTagName("apexPage").item(0).getTextContent());
                    pageAccesses.setEnabled(eElement.getElementsByTagName("enabled").item(0).getTextContent().equals("true"));
                    profile.getPageAccesses().add(pageAccesses);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return profile;
    }

    public static List<String> parseLayout(File fXmlFile) {
        List<String> layouts = new ArrayList<>();
        try {
            Document doc = getDocument(fXmlFile);
            NodeList nList = doc.getElementsByTagName("layoutAssignments");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    layouts.add(eElement.getElementsByTagName("layout").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return layouts;
    }

    public static List<String> getButtons(File file) {
        List<String> buttons = new ArrayList<>();
        try {
            Document doc = getDocument(file);
            NodeList nList = doc.getElementsByTagName("customButtons");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    buttons.add(nNode.getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buttons;
    }

    private static Document getDocument(File file) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);

        doc.getDocumentElement().normalize();
        return doc;
    }

    public static String getSvgPath(File file) {
       String path = null;
        try {
            Document doc = getDocument(file);
            NodeList nList = doc.getElementsByTagName("svg");

            if(nList.getLength() > 0) {
                Node nNode = nList.item(0);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    path = nNode.getAttributes().getNamedItem("d").getNodeValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return path;
    }
}
