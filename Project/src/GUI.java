import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.JTextArea;
//import org.apache.commons.lang3.StringUtils;

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
	private JScrollPane scrollPane;
	private JComboBox latitudeComboBox;
	private JComboBox longitudeComboBox;
	private JCheckBox starNames;
	private JCheckBox constellations;
	private JCheckBox planets;
	private JCheckBox messierDeepSpace;
	
	private double dayInput;
	private double yearInput;
	private double monthInput;
	private double hourInput;
	private double minuteInput;
	private double longInput;
	private double latInput;
	private double minInput;
	private String latitudeDirection;
	private String longitudeDirection;
	private Boolean starNamesCB;
	private Boolean constellationsCB;
	private Boolean planetsCB;
	private Boolean messierCB;
	
	
	
	
	InputUtils utils;
	private JButton btnSaveToDisk;
	private JScrollPane scrollPane_1;
	

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

	public void checkValues() {
		
		checkDay();
		checkYear();
		checkMonth();
		checkHours();
		checkMinutes();
		
		checkLatitude();
		checkLongitude();
		checkMins();
		checkLatitudeDirection();
		checkLongitudeDirection();
		
		
		if(checkDay() && checkYear() && checkMonth() && checkHours() && checkMinutes()
				&& checkLatitude() && checkLongitude() && checkMins() && checkLatitudeDirection() && checkLongitudeDirection()) {
			setValidInputs();
		}
		else {
			JOptionPane.showMessageDialog(new JFrame(), "Please Enter Valid Inputs!", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
	}
	public void setValidInputs() {
		
	}
	public Boolean checkDay() {
		Boolean check = false;
		System.out.println(dayTextField.getText());
		
		if(dayTextField.getText() != null && !dayTextField.getText().isEmpty()) {
			try {
				dayInput = Double.valueOf(dayTextField.getText());
			}
			catch(Exception e) {
				dayTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			System.out.println(dayInput);
			
			if(dayInput < 1 || dayInput > 31) {	
				dayTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			else {
				check = true;
			    dayTextField.setBorder(new LineBorder(Color.BLACK, 1));
			}
		}
		else {
			
			dayTextField.setBorder(new LineBorder(Color.RED, 3));
		}
		return check;
	}
	
	public Boolean checkYear() {
		Boolean check = false;
		
		if(yearTextField.getText() != null && !yearTextField.getText().isEmpty()) {
			try {
				yearInput = Double.valueOf(yearTextField.getText());
			}
			catch(Exception e) {
				yearTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			System.out.println(yearInput);
			
			if(yearInput < 1900 || yearInput > 2100) {
				yearTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			else {
				check = true;
				yearTextField.setBorder(new LineBorder(Color.BLACK, 1));
			}
		}
		else {
			//JOptionPane.showMessageDialog(new JFrame(), "Please Enter a Valid Day", "Dialog",
			 //       JOptionPane.ERROR_MESSAGE);
			yearTextField.setBorder(new LineBorder(Color.RED, 3));
		}
		return check;
	}
	
	public Boolean checkMonth() {
		Boolean check = false;
		
		if(monthTextField.getText() != null && !monthTextField.getText().isEmpty()) {
			
			try {
			monthInput = Double.valueOf(monthTextField.getText());
			}
			catch(Exception e) {
				monthTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			System.out.println(monthInput);
			
			if(monthInput < 1 || monthInput > 12) {
				monthTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			else {
				check = true;
				monthTextField.setBorder(new LineBorder(Color.BLACK, 1));	
			}
		}
		else {
			monthTextField.setBorder(new LineBorder(Color.RED, 3));
		}
		return check;
	}
	
	public Boolean checkHours() {
		Boolean check = false;
		
		if(hoursTextField.getText() != null && !hoursTextField.getText().isEmpty()) {
			try {
				hourInput = Double.valueOf(hoursTextField.getText());
			}
			catch(Exception e) {
				hoursTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			if(hourInput < 1 || hourInput > 24) {
				hoursTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			else {
				check = true;
				hoursTextField.setBorder(new LineBorder(Color.BLACK, 1));
			}
		}
		else {
			hoursTextField.setBorder(new LineBorder(Color.RED, 3));
		}
		return check;
	}
	
	public Boolean checkMinutes() {
		Boolean check = false;
		
		if(minutesTextField.getText() != null && !minutesTextField.getText().isEmpty()) {
			try {
				minuteInput = Double.valueOf(minutesTextField.getText());
			}
			catch(Exception e) {
				minutesTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			
			if(minuteInput < 0 || minuteInput > 60) {
				minutesTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			else {
				check = true;
				minutesTextField.setBorder(new LineBorder(Color.BLACK, 1));
			}
		}
		else {
			minutesTextField.setBorder(new LineBorder(Color.RED, 3));
		}
		return check;
	}
	
	public Boolean checkLatitude() {
		Boolean check = false;
		
		if(latTextField.getText() != null && !latTextField.getText().isEmpty()) {
			try {
				latInput = Double.valueOf(latTextField.getText());
			}
			catch(Exception e) {
				latTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			
			if(latInput < 0 || latInput > 90) {
				latTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			else {
				check = true;
				latTextField.setBorder(new LineBorder(Color.BLACK, 1));
			}
		}
		else {
			latTextField.setBorder(new LineBorder(Color.RED, 3));
		}
		return check;
	}
	
	public Boolean checkLongitude() {
		Boolean check = false;
		
		if(longTextField.getText() != null && !longTextField.getText().isEmpty()) {
			try {
				longInput = Double.valueOf(longTextField.getText());
			}
			catch(Exception e) {
				longTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			
			if(longInput < 0 || longInput > 180) {
				longTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			else {
				check = true;
				longTextField.setBorder(new LineBorder(Color.BLACK, 1));
			}
		}
		else {
			longTextField.setBorder(new LineBorder(Color.RED, 3));
		}
		return check;
	}
	
	public Boolean checkMins() {
		Boolean check = false;
		
		if(minTextField.getText() != null && !minTextField.getText().isEmpty()) {
			try {
				minInput = Double.valueOf(minTextField.getText());
			}
			catch(Exception e) {
				minTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			
			if(minInput < 0 || minInput > 180) {
				minTextField.setBorder(new LineBorder(Color.RED, 3));
			}
			else {
				check = true;
				minTextField.setBorder(new LineBorder(Color.BLACK, 1));
			}
		}
		else {
			minTextField.setBorder(new LineBorder(Color.RED, 3));
		}
		return check;
	}
	
	public Boolean checkLatitudeDirection() {
		Boolean flag = false;
		
		if(latitudeComboBox.getSelectedItem().toString() == " ") {
			latitudeComboBox.setBorder(new LineBorder(Color.RED, 3));
		}
		else {
			if(latitudeComboBox.getSelectedItem().toString() == "North") {
				latitudeDirection = "North";
				latitudeComboBox.setBorder(new LineBorder(Color.BLACK, 1));
			}
			else if(latitudeComboBox.getSelectedItem().toString() == "South") {
				latitudeDirection = "South";
				latitudeComboBox.setBorder(new LineBorder(Color.BLACK, 1));
			}
			flag = true;
		}
		
		return flag;
	}
	
	public Boolean checkLongitudeDirection() {
		Boolean flag = false;
		
		if(longitudeComboBox.getSelectedItem().toString() == " ") {
			longitudeComboBox.setBorder(new LineBorder(Color.RED, 3));
		}
		else {
			if(longitudeComboBox.getSelectedItem().toString() == "West") {
				longitudeDirection = "West";
				longitudeComboBox.setBorder(new LineBorder(Color.BLACK, 1));
			}
			else if(longitudeComboBox.getSelectedItem().toString() == "East") {
				longitudeDirection = "East";
				longitudeComboBox.setBorder(new LineBorder(Color.BLACK, 1));
			}
			flag = true;
		}
		
		return flag;
	}
	
	public void intialize() {
		//Set to intial values
		dayInput = -1;
		yearInput = -1;
		monthInput = -1;
		hourInput = -1;
		minuteInput = -1;
		longInput = -1;
		latInput = -1;
		minInput = -1;
	}
	/**
	 * Create the frame.
	 */
	
	public void setLabels() {
	    starNamesCB = starNames.isSelected();
		constellationsCB = constellations.isSelected();
		planetsCB = planets.isSelected();
		messierCB = messierDeepSpace.isSelected();
	}
	
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 2500, 1500);
		mainGUI = new JPanel();
		mainGUI.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainGUI);
		mainGUI.setLayout(null);
		
		//Display display = Display.getDefault(); // this is required to come before the "Shell" we'll be editting
		//Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN ); // Says 'use the Windows display window with a close, title, and minimize' but since we aren't also putting SWT.MAX then it is grayed out in my other projects I'm doing
		//shell.setSize(600, 450); // Resolution in X, Y
		
		//Intialize the member variables
		intialize();
		
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
		latitude.setBounds(25, 751, 173, 33);
		mainGUI.add(latitude);
		
		latTextField = new JTextField();
		latTextField.setBounds(190, 751, 52, 39);
		mainGUI.add(latTextField);
		latTextField.setColumns(10);
		
		longitude = new JLabel("Longitude");
		longitude.setFont(new Font("Tahoma", Font.PLAIN, 34));
		longitude.setBounds(25, 879, 154, 47);
		mainGUI.add(longitude);
		
		longTextField = new JTextField();
		longTextField.setBounds(189, 886, 52, 39);
		mainGUI.add(longTextField);
		longTextField.setColumns(10);
		
		minutes = new JLabel("Minutes");
		minutes.setFont(new Font("Tahoma", Font.PLAIN, 34));
		minutes.setBounds(26, 1015, 150, 33);
		mainGUI.add(minutes);
		
		minTextField = new JTextField();
		minTextField.setBounds(190, 1015, 52, 39);
		mainGUI.add(minTextField);
		minTextField.setColumns(10);
		
		starNames = new JCheckBox("Star Names");
		starNames.setFont(new Font("Tahoma", Font.PLAIN, 34));
		starNames.setBounds(2000, 343, 429, 41);
		mainGUI.add(starNames);
		
		constellations = new JCheckBox("Constellations");
		constellations.setFont(new Font("Tahoma", Font.PLAIN, 34));
		constellations.setBounds(2000, 412, 429, 41);
		mainGUI.add(constellations);
		
		planets = new JCheckBox("Planets");
		planets.setFont(new Font("Tahoma", Font.PLAIN, 34));
		planets.setBounds(2000, 473, 221, 41);
		mainGUI.add(planets);
		
		messierDeepSpace = new JCheckBox("Messier Deep Space Objects");
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
		
		JLabel lblEnterLocation = new JLabel("Enter Location:");
		lblEnterLocation.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblEnterLocation.setBounds(14, 670, 271, 33);
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
		
		btnSaveToDisk = new JButton("Save To Disk");
		btnSaveToDisk.setFont(new Font("Tahoma", Font.BOLD, 34));
		btnSaveToDisk.setBounds(2000, 1095, 337, 67);
		mainGUI.add(btnSaveToDisk);
		
		latitudeComboBox = new JComboBox();
		latitudeComboBox.setBounds(25, 812, 217, 39);
		latitudeComboBox.addItem(" ");
		latitudeComboBox.addItem("North");
		latitudeComboBox.addItem("South");
		mainGUI.add(latitudeComboBox);
		
		longitudeComboBox = new JComboBox();
		longitudeComboBox.setBounds(25, 948, 217, 39);
		longitudeComboBox.addItem(" ");
		longitudeComboBox.addItem("East");
		longitudeComboBox.addItem("West");
		mainGUI.add(longitudeComboBox);
		
		JButton refreshButton = new JButton("Refresh SkyMap");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setLabels();
			}
		});
		refreshButton.setFont(new Font("Tahoma", Font.BOLD, 34));
		refreshButton.setBounds(2018, 667, 411, 49);
		mainGUI.add(refreshButton);
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				/*dayInput = Double.valueOf(dayTextField.getText());
				monthInput = Double.valueOf(monthTextField.getText());
				yearInput = Double.valueOf(yearTextField.getText());
				hourInput = Double.valueOf(hoursTextField.getText());
				minuteInput = Double.valueOf(minutesTextField.getText());
					
				longInput = Double.valueOf(longTextField.getText());
				latInput = Double.valueOf(latTextField.getText());
				minInput = Double.valueOf(minTextField.getText());
					*/
				//utils.sendInputs(monthInput, dayInput, yearInput, hourInput, minuteInput, latInput, longInput, minInput);
				}
				catch(IllegalArgumentException e) 
		        { 
		            System.out.println("IllegalArgumentException caught"); 
		        }
				checkValues();
				setLabels();
				
				
					
			}
		});
		applyButton.setFont(new Font("Tahoma", Font.BOLD, 34));
		applyButton.setBounds(26, 1082, 212, 67);
		mainGUI.add(applyButton);
		
		
		
		//JTextArea textArea = new JTextArea();
		//textArea.setBounds(341, 155, 1605, 1026);
		//mainGUI.add(textArea);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(341, 155, 1605, 1026);
		scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(Color.BLACK));
		
		ImageIcon ii = new ImageIcon("C:\\Users\\alexx\\OneDrive\\Documents\\Fall 2019\\CS 499\\SkyMap.png");
		scrollPane_1.setViewportView(new JLabel(ii));
		
		mainGUI.add(scrollPane_1);
		
		
		
	}
}
