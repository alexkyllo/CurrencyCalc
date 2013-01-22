package calc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class CurrencyXMLParser{
	private DocumentBuilderFactory domFactory;
	private DocumentBuilder builder;
	private XPathFactory factory;
	private XPath xpath;
	private XPathExpression expr;
	private TreeMap<String, Double> currencyhash = new TreeMap<String, Double>();
	private final String url = "http://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml"; //for production
	private String cacheFileName = System.getProperty("java.io.tmpdir") + "ExchangeRates.xml";
	private File cacheFile;
	private boolean cacheFileExists = false;
	private boolean cacheFileExpired = false;
	private Date lastupdated;
	
	public CurrencyXMLParser() {
		super();
		checkCacheFile(); 
		try {
			parseXML();
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

	public void parseXML() throws ParserConfigurationException, SAXException, 
    IOException, XPathExpressionException {
		domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(true);
		builder = domFactory.newDocumentBuilder();
		Document doc = builder.parse(cacheFileName);
		
		factory = XPathFactory.newInstance();
		xpath = factory.newXPath();
		expr = xpath.compile("//@rate|//@currency");
		
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		for (int i = 0; i < nodes.getLength(); i++) {
	        String currencycode = nodes.item(i).getNodeValue();
	        Double exrate = Double.valueOf(nodes.item(i+1).getNodeValue());
			currencyhash.put(currencycode, exrate);
			currencyhash.put("EUR", 1.00);
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
		
		result = Math.floor(amount1 / rate1 * rate2 * 100) / 100 ;
		return result;
	}
	
	private void checkCacheFile() {
		System.out.println("Java temp cache directory is:" + System.getProperty("java.io.tmpdir"));
		cacheFile = new File(cacheFileName);
		//This method checks if the cache file exists and if it is expired; if it does not exist or is expired, calls update
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		Date onedayago = cal.getTime();
		
		if (cacheFile.isFile()) {
			cacheFileExists = true;
			System.out.println("cache file existed");
			Calendar lmcal = Calendar.getInstance();
			lmcal.setTimeInMillis(cacheFile.lastModified());
			lastupdated = lmcal.getTime();
		} else {
			cacheFileExists = false;
			System.out.println("cache file did not exist");
			Calendar lmcal = Calendar.getInstance();
			lmcal.add(Calendar.DATE, -2);
			lastupdated = lmcal.getTime();
		}
		
		if (onedayago.after(lastupdated)) {
			cacheFileExpired = true;
			System.out.println("cache file was expired");
		} else {
			cacheFileExpired = false;
			System.out.println("cache file was not expired");
		}
		
		if (!cacheFileExists || cacheFileExpired) {
			createCacheFile();
			System.out.println("cache file has been created");
		}
	}
	
	private void createCacheFile() {
		try {
			URL ecbratesurl = new URL(url);
			InputStreamReader in = new InputStreamReader(ecbratesurl.openStream());
			FileWriter out = new FileWriter(cacheFile);
			int charcount;
			while ((charcount = in.read()) != -1) {
				out.write(charcount);
			}
            in.close();
            out.close();
            Calendar cal = Calendar.getInstance();
            lastupdated = cal.getTime();
            cacheFileExists = true;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
