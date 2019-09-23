import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel mainGUI;
	private final JLabel month = new JLabel("Month");
	private JTextField monthTextField;
	private JTextField dayTextField;
	private JLabel year;
	private JTextField yearTextField;
	private JLabel time;
	private JTextField hoursTextField;
	private JLabel colon;
	private JTextField minutesTextField;
	private JLabel latitude;
	private JTextField latTextField;
	private JLabel longitude;
	private JTextField longTextField;
	private JLabel minutes;
	private JTextField minTextField;
	
	private int dayInput;
	private int yearInput;
	private int monthInput;
	private int hourInput;
	private int minuteInput;
	private double longInput;
	private double latInput;
	private double minInput;
	
	InputUtils utils;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Boolean checkValues() {
		return true;
	}
	
	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 2500, 1500);
		mainGUI = new JPanel();
		mainGUI.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainGUI);
		mainGUI.setLayout(null);
		month.setFont(new Font("Tahoma", Font.PLAIN, 34));
		month.setBounds(26, 215, 115, 33);
		mainGUI.add(month);
		
		monthTextField = new JTextField();
		monthTextField.setFont(new Font("Tahoma", Font.PLAIN, 34));
		monthTextField.setBounds(142, 213, 52, 36);
		mainGUI.add(monthTextField);
		monthTextField.setColumns(10);
		
		JLabel day = new JLabel("Day");
		day.setFont(new Font("Tahoma", Font.PLAIN, 34));
		day.setBounds(26, 276, 115, 33);
		mainGUI.add(day);
		
		dayTextField = new JTextField();
		dayTextField.setFont(new Font("Tahoma", Font.PLAIN, 34));
		dayTextField.setBounds(142, 276, 52, 39);
		mainGUI.add(dayTextField);
		dayTextField.setColumns(10);
		
		year = new JLabel("Year");
		year.setFont(new Font("Tahoma", Font.PLAIN, 34));
		year.setBounds(26, 337, 115, 33);
		mainGUI.add(year);
		
		yearTextField = new JTextField();
		yearTextField.setBounds(142, 337, 52, 39);
		mainGUI.add(yearTextField);
		yearTextField.setColumns(10);
		
		time = new JLabel("Time:");
		time.setFont(new Font("Tahoma", Font.PLAIN, 34));
		time.setBounds(26, 399, 115, 33);
		mainGUI.add(time);
		
		hoursTextField = new JTextField();
		hoursTextField.setBounds(26, 441, 49, 39);
		mainGUI.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		colon = new JLabel(":");
		colon.setBounds(84, 444, 10, 33);
		mainGUI.add(colon);
		
		minutesTextField = new JTextField();
		minutesTextField.setBounds(101, 441, 49, 39);
		mainGUI.add(minutesTextField);
		minutesTextField.setColumns(10);
		
		latitude = new JLabel("Latitude");
		latitude.setFont(new Font("Tahoma", Font.PLAIN, 34));
		latitude.setBounds(21, 794, 173, 33);
		mainGUI.add(latitude);
		
		latTextField = new JTextField();
		latTextField.setBounds(186, 794, 52, 39);
		mainGUI.add(latTextField);
		latTextField.setColumns(10);
		
		longitude = new JLabel("Longitude");
		longitude.setFont(new Font("Tahoma", Font.PLAIN, 34));
		longitude.setBounds(22, 855, 154, 47);
		mainGUI.add(longitude);
		
		longTextField = new JTextField();
		longTextField.setBounds(186, 862, 52, 39);
		mainGUI.add(longTextField);
		longTextField.setColumns(10);
		
		minutes = new JLabel("Minutes");
		minutes.setFont(new Font("Tahoma", Font.PLAIN, 34));
		minutes.setBounds(22, 933, 150, 33);
		mainGUI.add(minutes);
		
		minTextField = new JTextField();
		minTextField.setBounds(186, 933, 52, 39);
		mainGUI.add(minTextField);
		minTextField.setColumns(10);
		
		JCheckBox starNames = new JCheckBox("Star Names");
		starNames.setFont(new Font("Tahoma", Font.PLAIN, 34));
		starNames.setBounds(2000, 343, 429, 41);
		mainGUI.add(starNames);
		
		JCheckBox constellations = new JCheckBox("Constellations");
		constellations.setFont(new Font("Tahoma", Font.PLAIN, 34));
		constellations.setBounds(2000, 412, 429, 41);
		mainGUI.add(constellations);
		
		JCheckBox planets = new JCheckBox("Planets");
		planets.setFont(new Font("Tahoma", Font.PLAIN, 34));
		planets.setBounds(2000, 473, 221, 41);
		mainGUI.add(planets);
		
		JCheckBox messierDeepSpace = new JCheckBox("Messier Deep Space Objects");
		messierDeepSpace.setFont(new Font("Tahoma", Font.PLAIN, 30));
		messierDeepSpace.setBounds(2000, 534, 429, 41);
		mainGUI.add(messierDeepSpace);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(2018, 155, 1, 1);
		mainGUI.add(verticalBox);
		
		JLabel lblNewLabel = new JLabel("Sky Map");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(0, 28, 2468, 67);
		mainGUI.add(lblNewLabel);
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//if(checkValues()) {
					dayInput = Integer.valueOf(dayTextField.getText());
					monthInput = Integer.valueOf(monthTextField.getText());
					yearInput = Integer.valueOf(yearTextField.getText());
					hourInput = Integer.valueOf(hoursTextField.getText());
					minuteInput = Integer.valueOf(minutesTextField.getText());
					
					longInput = Double.valueOf(longTextField.getText());
					latInput = Double.valueOf(latTextField.getText());
					minInput = Double.valueOf(minTextField.getText());
					
					utils.sendInputs(monthInput, dayInput, yearInput, hourInput, minuteInput, latInput, longInput, minInput);
				//}
					
			}
		});
		applyButton.setFont(new Font("Tahoma", Font.BOLD, 34));
		applyButton.setBounds(26, 1082, 212, 67);
		mainGUI.add(applyButton);
		
		JLabel lblEnterLocation = new JLabel("Enter Location:");
		lblEnterLocation.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblEnterLocation.setBounds(21, 726, 271, 33);
		mainGUI.add(lblEnterLocation);
		
		JLabel enterDate = new JLabel("Enter Date:");
		enterDate.setFont(new Font("Tahoma", Font.BOLD, 34));
		enterDate.setBounds(26, 155, 212, 33);
		mainGUI.add(enterDate);
		
		JLabel labels = new JLabel("Labels:");
		labels.setHorizontalAlignment(SwingConstants.CENTER);
		labels.setFont(new Font("Tahoma", Font.BOLD, 34));
		labels.setBounds(2000, 279, 429, 40);
		mainGUI.add(labels);
	}
	
}
