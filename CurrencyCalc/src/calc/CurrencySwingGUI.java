package calc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;

public class CurrencySwingGUI {

	private JFrame frame;
	private JButton button;
	private JFormattedTextField formattedTextField1;
	private JFormattedTextField formattedTextField2;
	private Double amount1 = 0.0;
	private Double amount2 = 0.0;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CurrencySwingGUI window = new CurrencySwingGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public CurrencySwingGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 453, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("114px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("55px"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("114px:grow"),
				FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
				ColumnSpec.decode("55px"),},
			new RowSpec[] {
				RowSpec.decode("29px"),
				RowSpec.decode("29px"),}));
		
		formattedTextField1 = new JFormattedTextField();
		frame.getContentPane().add(formattedTextField1, "2, 2, fill, default");
		formattedTextField1.setValue(new Double(amount1));
		
		JComboBox comboBox = new JComboBox();
		frame.getContentPane().add(comboBox, "4, 2, fill, bottom");
		
		button = new JButton("<=>");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		frame.getContentPane().add(button, "6, 2");
		
		formattedTextField2 = new JFormattedTextField();
		formattedTextField2.setValue(new Double(amount2));
		
		frame.getContentPane().add(formattedTextField2, "8, 2, fill, default");
		
		JComboBox comboBox_1 = new JComboBox();
		frame.getContentPane().add(comboBox_1, "10, 2, fill, bottom");
	} 
	public void setFrameVisible() {
		frame.setVisible(true);
	}
}
