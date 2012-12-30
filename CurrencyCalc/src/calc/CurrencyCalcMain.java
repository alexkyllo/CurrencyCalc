package calc;

import java.util.TreeMap;


public class CurrencyCalcMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CurrencyXMLParser parser = new CurrencyXMLParser();
		TreeMap<String,Double> currencylist = parser.getCurrencyList();
		System.out.println(currencylist.toString());
		
		System.out.println(parser.convertCurrency(1.0, "USD", "CNY"));
		System.out.println(parser.convertCurrency(1.0, "CNY", "USD"));
		System.out.println(parser.convertCurrency(1.0, "EUR", "CNY"));
		System.out.println(parser.convertCurrency(1.0, "CNY", "EUR"));
		
		/*java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
					CurrencySwingGUI window = new CurrencySwingGUI();
					window.setFrameVisible();
			}
		});*/
	}
	

}
