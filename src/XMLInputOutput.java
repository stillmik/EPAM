import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import park.ParkObject;
import plants.Plant;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;

class XMLInputOutput {

    private static final String PATH_TO_OUTPUT_FILE = "C:\\Users\\User\\IdeaProjects\\plant\\src\\output\\languages.xml";
    private static final String PATH_TO_INPUT_FILE = "C:\\Users\\User\\IdeaProjects\\plant\\src\\input\\basic.xml";
    private static final String SOURCE_ELEMENT ="plant";

    static ArrayList<Plant> getInfFromXML() {
        ArrayList<Plant> plants = new ArrayList<>();

        File xmlFile = new File(PATH_TO_INPUT_FILE);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);//DOM obj
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName(SOURCE_ELEMENT);

            for (int i = 0; i < nodeList.getLength(); i++) {
                plants.add(getPlant(nodeList.item(i)));
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return plants;
    }

    private static Plant getPlant(Node node) {
        Plant plant = new Plant();
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            plant.setName(getTagValue("name", element));
            plant.setGroup(getTagValue("group", element));
            plant.setPlace(getTagValue("place", element));
            plant.setHeight(getTagValue("height", element));
        }
        return plant;
    }

    static void getPlantedParkXML(ArrayList<ParkObject> parkObjects) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("Park");
            doc.appendChild(rootElement);
            for (ParkObject parkObject : parkObjects) {
                Element parkObjElem = doc.createElement(parkObject.getPlace());
                rootElement.appendChild(parkObjElem);
                for (int j = 0; j < parkObject.plants.size(); j++) {
                    parkObjElem.appendChild(getPlant(doc, parkObject.plants.get(j).getName(), parkObject.plants.get(j).getHeight(), parkObject.plants.get(j).getGroup()));
                }
            }
            fillOutputFile(doc);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    private static void fillOutputFile(Document doc) {
        try {
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File(PATH_TO_OUTPUT_FILE));
            transformer.transform(source, file);
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

    private static Node getPlant(Document doc, String name, String height, String group) {
        Element plant = doc.createElement(SOURCE_ELEMENT);

        plant.appendChild(getPlantElements(doc, "name", name));
        plant.appendChild(getPlantElements(doc, "height", height));
        plant.appendChild(getPlantElements(doc, "group", group));
        return plant;
    }

    private static Node getPlantElements(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }


}

