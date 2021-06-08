package proiect;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

class GUI {
	private static final String ICON_PATH = "/Icon.png";
	protected static JFrame frame = new JFrame();												// creeaza o fereastra JFrame
	private static JLabel label1 = new JLabel("Enter Google Sheets Link:");					// creeaza o eticheta JLabel
	protected static JTextField textfield1 = new JTextField();								// creeaza un camp de text JTextField
	protected static JButton button1 = new JButton("Next");									// creeaza un buton JButton
	private static JLabel label2 = new JLabel("Select Sheet:");								// creeaza o eticheta JLabel
	protected static JComboBox<String> comboBox1 = new JComboBox<>();							// creeaza o caseta combo JCombo
	protected static JButton button2 = new JButton("Next");									// creeaza un buton JButton
	private static JLabel label3 = new JLabel("Select Names Column:");						// creeaza o eticheta JLabel
	protected static JComboBox<String> comboBox2 = new JComboBox<>();							// creeaza o caseta combo JCombo
	private static JLabel label4 = new JLabel("Enter Input Value:");						// creeaza o eticheta JLabel
	private static JTextField textfield2 = new JTextField();								// creeaza un camp de text JTextField
	private static JLabel label5 = new JLabel("Select Laboratories Columns and Dates:");	// creeaza o eticheta JLabel
	private static JComboBox<String> comboBox3 = new JComboBox<>();							// creeaza o caseta combo JCombo
	@SuppressWarnings("serial")
	private static Properties properties = new Properties() {{ put("text.today", "Today"); put("text.month", "Month"); put("text.year", "Year"); }};;
	
	protected static UtilDateModel model1 = new UtilDateModel();
	private static JDatePickerImpl datePicker1 = new JDatePickerImpl(new JDatePanelImpl(
			model1, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox4 = new JComboBox<>();							// creeaza o caseta combo JCombo
	protected static UtilDateModel model2 = new UtilDateModel();
	private static JDatePickerImpl datePicker2 = new JDatePickerImpl(new JDatePanelImpl(
			model2, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox5 = new JComboBox<>();							// creeaza o caseta combo JCombo
	protected static UtilDateModel model3 = new UtilDateModel();
	private static JDatePickerImpl datePicker3 = new JDatePickerImpl(new JDatePanelImpl(
			model3, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox6 = new JComboBox<>();							// creeaza o caseta combo JCombo
	protected static UtilDateModel model4 = new UtilDateModel();
	private static JDatePickerImpl datePicker4 = new JDatePickerImpl(new JDatePanelImpl(
			model4, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox7 = new JComboBox<>();							// creeaza o caseta combo JCombo
	protected static UtilDateModel model5 = new UtilDateModel();
	private static JDatePickerImpl datePicker5 = new JDatePickerImpl(new JDatePanelImpl(
			model5, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox8 = new JComboBox<>();							// creeaza o caseta combo JCombo
	protected static UtilDateModel model6 = new UtilDateModel();
	private static JDatePickerImpl datePicker6 = new JDatePickerImpl(new JDatePanelImpl(
			model6, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox9 = new JComboBox<>();							// creeaza o caseta combo JCombo
	protected static UtilDateModel model7 = new UtilDateModel();
	private static JDatePickerImpl datePicker7 = new JDatePickerImpl(new JDatePanelImpl(
			model7, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox10 = new JComboBox<>();						// creeaza o caseta combo JCombo
	protected static UtilDateModel model8 = new UtilDateModel();
	private static JDatePickerImpl datePicker8 = new JDatePickerImpl(new JDatePanelImpl(
			model8, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox11 = new JComboBox<>();						// creeaza o caseta combo JCombo
	protected static UtilDateModel model9 = new UtilDateModel();
	private static JDatePickerImpl datePicker9 = new JDatePickerImpl(new JDatePanelImpl(
			model9, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox12 = new JComboBox<>();						// creeaza o caseta combo JCombo
	protected static UtilDateModel model10 = new UtilDateModel();
	private static JDatePickerImpl datePicker10 = new JDatePickerImpl(new JDatePanelImpl(
			model10, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox13 = new JComboBox<>();						// creeaza o caseta combo JCombo
	protected static UtilDateModel model11 = new UtilDateModel();
	private static JDatePickerImpl datePicker11 = new JDatePickerImpl(new JDatePanelImpl(
			model11, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox14 = new JComboBox<>();						// creeaza o caseta combo JCombo
	protected static UtilDateModel model12 = new UtilDateModel();
	private static JDatePickerImpl datePicker12 = new JDatePickerImpl(new JDatePanelImpl(
			model12, properties), new DateLabelFormatter());
	private static JComboBox<String> comboBox15 = new JComboBox<>();						// creeaza o caseta combo JCombo
	protected static UtilDateModel model13 = new UtilDateModel();
	private static JDatePickerImpl datePicker13 = new JDatePickerImpl(new JDatePanelImpl(
			model13, properties), new DateLabelFormatter());
	protected static JButton button3 = new JButton("Next");									// creeaza un buton JButton									
	protected static ArrayList<String> informations = new ArrayList<String>();
	protected static JTextArea textarea = new JTextArea();
	private static JScrollPane scroll = new JScrollPane(textarea);
	@SuppressWarnings({ "rawtypes" })
	protected static JComboBox[] comboBoxes = {comboBox3, comboBox4, comboBox5, comboBox6, comboBox7, comboBox8, comboBox9, comboBox10,
		comboBox11, comboBox12, comboBox13, comboBox14, comboBox15};
	private static JDatePickerImpl[] datePickers = {datePicker1, datePicker2, datePicker3, datePicker4, datePicker5, datePicker6, datePicker7, 
		datePicker8, datePicker9, datePicker10, datePicker11, datePicker12, datePicker13};
	protected static void firstInitializationGUI() throws IOException {
		/* Initializarea ferestrei JFrame */
		frame.setTitle("PRESENCE UPDATER");									// seteaza titlul ferestrei	JFrame
		frame.setBounds(100, 100, 535, 650);								// seteaza limitele ferestrei JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);				// seteaza inchiderea ferestrei JFrame - inchidere aplicatie
		frame.setResizable(false);											// seteaza redimensionarea ferestrei JFrame - fara redimensionare
		frame.getContentPane().setLayout(null);								// seteaza amplasarea in cadrul ferestrei JFrame - fara amplasare
		frame.setIconImage(new ImageIcon(PresenceUpdater.class.getResource(ICON_PATH)).getImage());
		
		/* Initializarea primei etichete JLabel */
		label1.setBounds(150, 10, 215, 20);									// seteaza limitele primei etichete JLabel
		label1.setFont(new Font("Tahoma", Font.BOLD, 16));					// seteaza fontul textului din cadrul primei etichete JLabel
		frame.getContentPane().add(label1);									// adauga prima eticheta JLabel in cadrul ferestrei JFrame	

		/* Initializarea primului camp de text JTextField */
		textfield1.setBounds(10, 35, 495, 30);								// seteaza limitele primului campului de text JTextField 
		textfield1.setBackground(new Color(240,240,240));					// seteaza culoarea de fundal al primului camp de text JTextField 
		textfield1.setFont(new Font("Tahoma", Font.BOLD, 8));				// seteaza fontul textului din cadrul primului camp de text JTextField 
		textfield1.setForeground(Color.RED);								// seteaza culoarea textului din cadrul primului camp de text JTextField
		frame.getContentPane().add(textfield1);								// adauga primul campul de text JTextField in cadrul ferestrei JFrame

		/* Initializarea primului buton JButton */
		button1.setBounds(405, 545, 100, 35);								// seteaza limitele butonului JButton
		button1.setFont(new Font("Tahoma", Font.BOLD, 16));					// seteaza fontul textului din cadrul butonului JButton
		frame.getContentPane().add(button1);								// adauga butonul JButton in cadrul ferestrei JFrame
	}

	protected static void secondInitializationGUI() {
		textfield1.setEditable(false);
		frame.getContentPane().remove(button1);

		/* Initializarea celei de-a doua etichete JLabel */
		label2.setBounds(10, 65, 110, 30);									// seteaza limitele celei de-a doua etichete JLabel
		label2.setFont(new Font("Tahoma", Font.BOLD, 16));					// seteaza fontul textului din cadrul celei de-a doua etichete JLabel
		frame.getContentPane().add(label2);									// adauga a doua eticheta JLabel in cadrul ferestrei JFrame

		/* Initializarea primei casete combo JComboBox */
		comboBox1.setBounds(10, 95, 100, 25);
		comboBox1.setSelectedIndex(0);
		comboBox1.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox1.setForeground(Color.RED);
		frame.add(comboBox1);

		/* Initializarea celui de-al doilea buton JButton */
		button2.setBounds(405, 545, 100, 35);								// seteaza limitele butonului JButton
		button2.setFont(new Font("Tahoma", Font.BOLD, 16));					// seteaza fontul textului din cadrul butonului JButton
		frame.getContentPane().add(button2);								// adauga butonul JButton in cadrul ferestrei JFrame
	}

	@SuppressWarnings("rawtypes")
	protected static void thirdInitializationGUI() {
		frame.getContentPane().remove(button2);
		comboBox1.setEnabled(false);

		/* Initializarea celei de-a treia etichete JLabel */
		label3.setBounds(150, 65, 190, 30);									// seteaza limitele celei de-a treia etichete JLabel
		label3.setFont(new Font("Tahoma", Font.BOLD, 16));					// seteaza fontul textului din cadrul celei de-a treia etichete JLabel
		frame.getContentPane().add(label3);									// adauga a treia eticheta JLabel in cadrul ferestrei JFrame

		/* Initializarea celei de-a doua casete combo JComboBox */
		comboBox2.setBounds(200, 95, 90, 25);
		comboBox2.setFont(new Font("Tahoma", Font.BOLD, 14));
		comboBox2.setForeground(Color.RED);
		frame.add(comboBox2);

		/* Initializarea celei de-a patra etichete JLabel */				
		label4.setBounds(355, 65, 150, 30);									
		label4.setFont(new Font("Tahoma", Font.BOLD, 16));					
		frame.getContentPane().add(label4);									

		/* Initializarea celui de-al doilea camp de text JTextField */							
		textfield2.setBounds(380, 95, 92, 25);								
		textfield2.setBackground(new Color(240,240,240));	
		textfield2.setFont(new Font("Tahoma", Font.BOLD, 14));	
		textfield2.setForeground(Color.RED);
		textfield2.setText("P");
		frame.getContentPane().add(textfield2);								

		/* Initializarea celei de-a cincea etichete JLabel */	
		label5.setBounds(90, 120, 330, 30);										
		label5.setFont(new Font("Tahoma", Font.BOLD, 16));						
		frame.getContentPane().add(label5);										

		/* Initializarea celor 13 casete combo JComboBox */
		int y = 150;
		for (JComboBox comboBox : comboBoxes) {
			comboBox.setBounds(150, y, 90, 25);
			y = y + 30;
			comboBox.setFont(new Font("Tahoma", Font.BOLD, 14));
			comboBox.setForeground(Color.RED);
			frame.add(comboBox);
		}

		/* Initializarea celor 13 casete de date JDatePick */
		y = 150;
		for (JDatePickerImpl datePicker : datePickers) {
			datePicker.setBounds(260, y, 110, 30);
			y = y + 30;
			frame.add(datePicker);	
		}

		/* Initializarea celui de-al doilea buton JButton */
		button3.setBounds(405, 545, 100, 35);									// seteaza limitele butonului JButton
		button3.setFont(new Font("Tahoma", Font.BOLD, 16));						// seteaza fontul textului din cadrul butonului JButton
		frame.getContentPane().add(button3);									// adauga butonul JButton in cadrul ferestrei JFrame
	}

	@SuppressWarnings("rawtypes")
	protected static void fourthInitializationGUI() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			informations.add(comboBox2.getSelectedItem().toString());
		}
		catch (NullPointerException e) {
			informations.add("");
		}
		informations.add(textfield2.getText());
		for (JComboBox comboBox : comboBoxes) {
			try {
				informations.add(comboBox.getSelectedItem().toString());
			}
			catch (NullPointerException e) {
				informations.add("");
			}

		}
		int i = 2;
		for (JDatePickerImpl datePicker : datePickers) {
			informations.set(i, informations.get(i) + ". " + formatter.format(datePicker.getModel().getValue()));
			i++;
		}

		frame.getContentPane().removeAll();

		/* Initializarea zonei de text JTextArea */							
		textarea.setFont(new Font("Tahoma", Font.BOLD, 14));
		textarea.setBackground(new Color(240,240,240));	
		textarea.setText("0 data updated!");
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(10, 10, 500, 580);
		frame.getContentPane().add(scroll);
	}
}