package calc;

import java.io.*;
//import java.util.HashMap;
import java.util.TreeMap;

import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.xpath.*;

public class CurrencyXMLParser{
	private DocumentBuilderFactory domFactory;
	private DocumentBuilder builder;
	private XPathFactory factory;
	private XPath xpath;
	private XPathExpression expr;
	public final TreeMap<String, Double> currencyhash = new TreeMap<String, Double>();
	
	public CurrencyXMLParser(String uri) {
		super();
	}

	public void parse(String uri) throws ParserConfigurationException, SAXException, 
    IOException, XPathExpressionException {
		domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(uri);
		
		factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		expr = xpath.compile("//@rate|//@currency");
		
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
	        String currencycode = nodes.item(i).getNodeValue();
	        Double exrate = Double.valueOf(nodes.item(i+1).getNodeValue());
			currencyhash.put(currencycode, exrate);
			i++;
	    }
	}
}
