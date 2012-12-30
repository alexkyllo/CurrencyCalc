package calc;

import java.io.*;
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
	private TreeMap<String, Double> currencyhash = new TreeMap<String, Double>();
	private final String uri = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml"; //for production
	//private final String uri = "/Users/kyllo/Downloads/eurofxref-daily.xml"; //for testing
	
	public CurrencyXMLParser() {
		super();
		try {
			parseXML(uri);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void parseXML(String uri) throws ParserConfigurationException, SAXException, 
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
	
	public TreeMap<String,Double> getCurrencyList() {
		return currencyhash;
	}
	
	public Double convertCurrency(Double amount1, String currency1, String currency2) {
		Double result = 0.0;
		Double rate1 = 0.0;
		Double rate2 = 0.0;
		if (currency1 == "EUR"){	
			rate1 = 1.0;
		} else {
			rate1 = currencyhash.get(currency1);	
		}
		
		if (currency2 == "EUR"){
			rate2 = 1.0;
		} else {
			rate2 = currencyhash.get(currency2);
		}
		
		result = amount1 / rate1 * rate2;
		return result;
	}
}
