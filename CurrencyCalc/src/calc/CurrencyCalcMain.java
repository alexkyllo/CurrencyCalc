package calc;

import java.util.Set;


public class CurrencyCalcMain {

	/**
	 * @param args
	 */
	private static CurrencyXMLParser parser = new CurrencyXMLParser();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 /*
		CurrencyXMLParser parser = new CurrencyXMLParser();
		TreeMap<String,Double> currencylist = parser.getCurrencyList();
		
		System.out.println(getCurrencyArray().toString());
		System.out.println(parser.convertCurrency(1.0, "USD", "CNY"));
		System.out.println(parser.convertCurrency(1.0, "CNY", "USD"));
		System.out.println(parser.convertCurrency(1.0, "EUR", "CNY"));
		System.out.println(parser.convertCurrency(1.0, "CNY", "EUR")); 
		*/
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
					CurrencySwingGUI window = new CurrencySwingGUI();
					window.setFrameVisible();
			}
		});
	}
	
	public static Double convertButtonPressed(Double amount1, String currency1, String currency2) {
		Double result = parser.convertCurrency(amount1,currency1,currency2);
		return result;
	}
	
	public static Object[] getCurrencyArray() {
		Set<String> currencyset = parser.getCurrencyList().keySet();
		return currencyset.toArray();
	}

}
