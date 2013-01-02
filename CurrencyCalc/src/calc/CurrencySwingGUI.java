package calc;

import java.awt.Dimension;
import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class CurrencySwingGUI {

	private JFrame frmQuickCurrencyConverter;
	private JButton button;
	private JFormattedTextField formattedTextField1;
	private JFormattedTextField formattedTextField2;
	private JComboBox comboBox1;
	private JComboBox comboBox2;
	public Double amount1;
	public Double amount2;
	public String currency1;
	public String currency2;
	private JButton button_1;

	public CurrencySwingGUI() {
		amount1 = 0.0;
		amount2 = 0.0;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmQuickCurrencyConverter = new JFrame();
		frmQuickCurrencyConverter.setTitle("Quick Currency Converter");
		frmQuickCurrencyConverter.setBounds(100, 100, 500, 90);
		frmQuickCurrencyConverter.setMinimumSize(new Dimension(453,90));
		frmQuickCurrencyConverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmQuickCurrencyConverter.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("100px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("80px"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("60px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("80px"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("100px:grow"),},
			new RowSpec[] {
				RowSpec.decode("29px"),
				RowSpec.decode("29px"),}));
		
		button_1 = new JButton("<->");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object placeholder = comboBox1.getSelectedItem();
				comboBox1.setSelectedItem(comboBox2.getSelectedItem());
				comboBox2.setSelectedItem(placeholder);
				convertButtonAction();
			}
		});
		frmQuickCurrencyConverter.getContentPane().add(button_1, "6, 1");
		
		formattedTextField1 = new JFormattedTextField();
		frmQuickCurrencyConverter.getContentPane().add(formattedTextField1, "2, 2, fill, default");
		formattedTextField1.setValue(new Double(amount1));
		
		comboBox1 = new JComboBox();
		frmQuickCurrencyConverter.getContentPane().add(comboBox1, "4, 2, fill, bottom");
		
		
		button = new JButton("=>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				convertButtonAction();
			}
		});
		frmQuickCurrencyConverter.getContentPane().add(button, "6, 2");
		comboBox2 = new JComboBox();
		frmQuickCurrencyConverter.getContentPane().add(comboBox2, "8, 2, fill, bottom");
		
		
		formattedTextField2 = new JFormattedTextField();
		formattedTextField2.setEditable(false);
		formattedTextField2.setValue(new Double(amount2));
		
		frmQuickCurrencyConverter.getContentPane().add(formattedTextField2, "10, 2, fill, default");
		
		
		fillComboBoxes();
		
	} 
	public void setFrameVisible() {
		frmQuickCurrencyConverter.setVisible(true);
	}
	
	public void fillComboBoxes() {
		Object[] currencycodes = CurrencyCalcMain.getCurrencyArray();
		for (int i=0;i<currencycodes.length;i++) {
			comboBox1.addItem(currencycodes[i]);
			comboBox2.addItem(currencycodes[i]);
			
		}
		comboBox1.setSelectedItem("USD");
		comboBox2.setSelectedItem("EUR");
	}
	
	public void convertButtonAction() {
		amount1 = (Double) formattedTextField1.getValue();
		currency1 = (String) comboBox1.getSelectedItem();
		currency2 = (String) comboBox2.getSelectedItem();
		Double result = CurrencyCalcMain.convertButtonPressed(amount1, currency1, currency2);
		formattedTextField2.setText(result.toString());
	}
}
