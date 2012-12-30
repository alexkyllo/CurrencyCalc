package calc;


public class CurrencyCalcMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CurrencyXMLParser parser = new CurrencyXMLParser();
		System.out.println(parser.getCurrencyList().toString());
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
					CurrencySwingGUI window = new CurrencySwingGUI();
					window.setFrameVisible();
			}
		});
	}

}
