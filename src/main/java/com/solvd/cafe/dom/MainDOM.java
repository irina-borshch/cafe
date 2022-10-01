package com.solvd.cafe.dom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainDOM {
    private static final Logger logger = LogManager.getLogger(MainDOM.class);
    public static void main(String[] args) {
        try {
            DocumentBuilderFactory DBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = DBFactory.newDocumentBuilder();
            Document document = docBuilder.parse(new File("src/main/java/com/solvd/cafe/dom/domMenu.xml"));
            document.getDocumentElement().normalize();

            Element rootElement = document.getDocumentElement();
            logger.info("Root Element: " + rootElement.getNodeName()); //menu

            // get <position>
            NodeList nodeList = document.getElementsByTagName("position");
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    logger.info("------------");
                    logger.info("id = " + element.getAttribute("id"));
                    logger.info("position name = " + element.getElementsByTagName("positionName").item(0).getTextContent());
                    logger.info("serving portion = " + element.getElementsByTagName("servingPortion").item(0).getTextContent());
                    logger.info("measurement unit = " + element.getElementsByTagName("measurementUnit").item(0).getTextContent());
                    logger.info("price = " + element.getElementsByTagName("price").item(0).getTextContent() + ("$"));
                }
            }
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }

        }


    }
