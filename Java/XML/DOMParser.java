/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 *
 * @author p3090142
 */
public final class DOMParser {

    DocumentBuilderFactory dbFactory;
    DocumentBuilder dBuilder;
    FileInputStream stream;
    Document doc;
    XPath xPath;

    public void MakeParser() throws ParserConfigurationException {
        dbFactory = DocumentBuilderFactory.newInstance();
        dBuilder = dbFactory.newDocumentBuilder();
    }

    public void Parse() throws SAXException, IOException {
        doc = dBuilder.parse(stream);
    }

    public void makestream(String file) throws FileNotFoundException {
        stream = new FileInputStream(file);
    }

    public void MakeXpath() {
        xPath = XPathFactory.newInstance().newXPath();
    }

    public NodeList ReadnodeList(String expression) throws XPathExpressionException {
        return (NodeList) xPath.compile(expression).evaluate(doc, XPathConstants.NODESET);
    }

    public DOMParser(String file) throws ParserConfigurationException, FileNotFoundException, SAXException, IOException {
        MakeParser();
        makestream(file);
        Parse();
        MakeXpath();
    }
}
