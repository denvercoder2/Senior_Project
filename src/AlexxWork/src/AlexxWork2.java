import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JCheckBox;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.ScrollPane;
import java.awt.Scrollbar;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextArea;

import JohnWork.main.SkyMap_Formulae_J;
import JohnWork.main.SpaceObj;
import net.miginfocom.swing.MigLayout;

import javax.swing.JFrame;


//import org.apache.commons.lang3.StringUtils;

public class AlexxWork2 extends JFrame {

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
	private JScrollPane scrollPane;
	private JComboBox latitudeComboBox;
	private JComboBox longitudeComboBox;
	private JCheckBox starNames;
	private JCheckBox constellations;
	private JCheckBox planets;
	private JCheckBox messierDeepSpace;
	
	DrawingSky drawing;
	ImageIcon screenshot;
	
	private double dayInput;
	private double yearInput;
	private double monthInput;
	private double hourInput;
	private double minuteInput;
	private double longInput;
	private double latInput;
	private double minInput;
	
	private double dayValid;
	private double yearValid;
	private double monthValid;
	private double hourValid;
	private double minuteValid;
	private double longValid;
	private double latValid;
	private double minValid;
	
	private String latitudeDirection;
	private String longitudeDirection;
	public static Boolean starNamesCB;
	public static Boolean constellationsCB;
	public static Boolean planetsCB;
	public static Boolean messierCB;
	
	public static ArrayList<SpaceObj> spaceObjList;
	
	private JButton btnSaveToDisk;
	private JScrollPane scrollPane_1;
	private JButton refreshButton;
	private int count = 0;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlexxWork2 frame = new AlexxWork2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void checkValues() throws NullPointerException, Exception {
		
		checkDay();
		checkYear();
		checkMonth();
		checkHours();
		checkMinutes();
		checkDate();
		
		checkLatitude();
		checkLongitude();
		checkLatitudeDirection();
		checkLongitudeDirection();
		
		
		if(checkDay() && checkYear() && checkMonth() && checkHours() && checkMinutes()
				&& checkLatitude() && checkLongitude() && checkLatitudeDirection() && checkLongitudeDirection() && checkDate()) {
			setValidInputs();
			//ImageIcon ii = new ImageIcon("C:\\Users\\alexx\\OneDrive\\Documents\\Fall 2019\\CS 499\\SkyMap.png");
			//scrollPane_1.setViewportView(new JLabel(ii));
			setLabels();
			getSpaceObjects();
			drawSky();
			
		}
		else {
			JOptionPane.showMessageDialog(new JFrame(), "Please Enter Valid Inputs!", "Dialog",
			        JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void setValidInputs() {
		
		System.out.println(latInput);
		System.out.println(longInput);
		System.out.println(latitudeDirection);
		System.out.println(longitudeDirection);
		dayValid = dayInput;
		yearValid = yearInput;
		monthValid = monthInput;
		hourValid = hourInput;
		minuteValid = minuteInput;
		//if(longitudeDirection.equals("South")) {
		//	longValid = longInput * -1;
			
		//}
		//if(longitudeDirection.equals("North")) {
			longValid = longInput;
			//System.out.println("***"+longValid);
		//}
		
		//if(latitudeDirection.equals("West")) {
			//latValid = latInput * -1;
			//System.out.println("******"+latValid);
		//}
		//if(latitudeDirection.equals("East")) {
			latValid = latInput;
		//}
		minValid = minInput;
		
		System.out.println(yearValid);
		System.out.println(monthValid);
		System.out.println(dayValid);
		System.out.println(hourValid);
		System.out.println(minuteValid);
		System.out.println(latValid);
		System.out.println(longValid);
		
	}
	
	public void getSpaceObjects() throws NullPointerException, Exception {
		
		/*spaceObjList = SkyMap_Formulae_J.getSpace(String.valueOf((int)yearValid), String.valueOf((int)monthValid), 
				String.valueOf((int)dayValid), String.valueOf((int)hourValid), 
				String.valueOf((int)minuteValid), String.valueOf(0), 
				String.valueOf((int)latValid), String.valueOf((int)longValid));
				*/
		//ImageIcon ii = new ImageIcon("C:\\Users\\alexx\\OneDrive\\Documents\\Fall 2019\\CS 499\\waiting.jpg");
		//scrollPane_1.setViewportView(new JLabel(ii));
		spaceObjList = SkyMap_Formulae_J.getSpace(String.valueOf((int)2019), String.valueOf((int)11), 
				String.valueOf((int)23), String.valueOf((int)13), 
				String.valueOf((int)05), String.valueOf(0), 
				String.valueOf((int)37), String.valueOf((int)-114));
		//System.out.println("2222222222222222222222222222\n");
    	//System.out.println(spaceObjList.size()+"\2222222222222222222222222222\n");
    	
		
	}
	public void drawSky() {
		drawing = new DrawingSky();
		screenshot = drawing.draw();

		scrollPane_1.setViewportView(new JLabel(screenshot));
		btnSaveToDisk.setEnabled(true);
		refreshButton.setEnabled(true);
		//fc.setSelectedFile(new java.io.File(screenshot));
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
			if(hourInput < 0 || hourInput > 24) {
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
			
			if(latInput < -90 || latInput > 90) {
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
			
			if(longInput < -180 || longInput > 180) {
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
	
	public Boolean checkLatitudeDirection() {
		Boolean flag = false;
		/*
		if(latitudeComboBox.getSelectedItem().toString().equals(" ")) {
			latitudeComboBox.setBorder(new LineBorder(Color.RED, 3));
		}
		else {
			if(latitudeComboBox.getSelectedItem().toString().equals("North")) {
				latitudeDirection = "North";
				latitudeComboBox.setBorder(new LineBorder(Color.BLACK, 1));
			}
			else if(latitudeComboBox.getSelectedItem().toString().equals("South")) {
				latitudeDirection = "South";
				latitudeComboBox.setBorder(new LineBorder(Color.BLACK, 1));
			}
			flag = true;
		}
		*/
		flag = true;
		return flag;
	}
	
	public Boolean checkLongitudeDirection() {
		Boolean flag = false;
		/*
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
		*/
		flag = true;
		return flag;
	}
	
	public Boolean checkDate() {
		Boolean flag = true;
		Boolean leapYear = false;
		
		if(checkMonth() && checkDay() && checkYear()) {
			if(yearInput % 4 == 0) {
				leapYear = true;
			}
			
			//Check Feb
			if(monthInput == 2) {
				if(leapYear && dayInput > 29) {
					dayTextField.setBorder(new LineBorder(Color.RED, 3));
					flag = false;
				}
				else if(!leapYear && dayInput > 28) {
					dayTextField.setBorder(new LineBorder(Color.RED, 3));
					flag = false;
				}
			}
			
			if(monthInput == 4 || monthInput == 6 || monthInput == 9 || monthInput == 11) {
				if(dayInput > 30) {
					dayTextField.setBorder(new LineBorder(Color.RED, 3));
					flag = false;
				}
			}
		}
		else {
			flag = false;
		}
		
		if(flag) {
			dayTextField.setBorder(new LineBorder(Color.BLACK, 1));
		}
		
		return flag;
	}
	
	public void copyFile(File source, File dest) throws IOException {
		//Files.copy(source.toPath(), dest.toPath());
		File f;
	    f = dest;
	    if (!f.exists())
	    {
	    	Files.copy(source.toPath(), dest.toPath());
	    }
	    else
	    {
	      count++;
	      copyFile(f,
	    		  new File(FileSystemView.getFileSystemView().getDefaultDirectory().toString() + "\\spaceGUI\\yourImage" + count + ".jpeg"));
	    }
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
	
	public void setLabels() {
		//CB is checked if true
	    starNamesCB = starNames.isSelected();
		constellationsCB = constellations.isSelected();
		planetsCB = planets.isSelected();
		messierCB = messierDeepSpace.isSelected();
	}
	
	public double getDay() {
		return dayValid;
	}
	
	public double getYear() {
		return yearValid;
	}
	
	public double getMonth() {
		return monthValid;
	}
	
	public double getHour() {
		return hourValid;
	}
	
	public double getMinute() {
		return minuteValid;
	}
	
	public double getLong() {
		return longValid;
	}
	
	public double getLat() {
		return latValid;
	}
	
	public double getMin() {
		return minValid;
	}
	
	public String getLatitudeDirection() {
		return latitudeDirection;
	}
	
	public String getLongitudeDirection() {
		return longitudeDirection;
	}
	
	
	/**
	 * Create the frame.
	 */
	
	public AlexxWork2() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		

		double screenHeight = screenSize.height;
		double screenWidth = screenSize.width;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, (int)(screenWidth*0.75), (int)(screenHeight*0.75));
		mainGUI = new JPanel();
		mainGUI.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainGUI);
		
		//Display display = Display.getDefault(); // this is required to come before the "Shell" we'll be editting
		//Shell shell = new Shell(display, SWT.CLOSE | SWT.TITLE | SWT.MIN ); // Says 'use the Windows display window with a close, title, and minimize' but since we aren't also putting SWT.MAX then it is grayed out in my other projects I'm doing
		//shell.setSize(600, 450); // Resolution in X, Y
		
		//Intialize the member variables
		intialize();
		mainGUI.setLayout(new MigLayout("", "[75px][][9px][10px][7px][76.00px][105.00px][5px][-1.00px][-76.00px][850.00px,grow,fill][2.00][329.00px,right][]", "[67px][1px][4px][40px][13px][52px][11px][58px][3px][58px][4px][33px][9px][39px][3px][17px][76px][49px][101px][33px][48px][39px][22px][39px][28px][47px][22px][39px][28px][39px][28px][67px]"));
		 
		month.setFont(new Font("Tahoma", Font.PLAIN, 28));
		mainGUI.add(month, "cell 0 5,alignx left,aligny top");
		
		monthTextField = new JTextField();
		monthTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
					System.out.println("ILLEGAL ACTION");
					e.consume();
				}
			}
		});
		monthTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		mainGUI.add(monthTextField, "cell 5 5 2 1,growx,aligny top");
		monthTextField.setColumns(10);
		
		JLabel day = new JLabel("Day");
		day.setFont(new Font("Tahoma", Font.PLAIN, 28));
		mainGUI.add(day, "flowx,cell 0 7 7 1,growx,aligny top");
		
		year = new JLabel("Year");
		year.setFont(new Font("Tahoma", Font.PLAIN, 28));
		mainGUI.add(year, "flowx,cell 0 9 7 1,growx,aligny top");
		
		time = new JLabel("Time (Military Format):");
		time.setFont(new Font("Tahoma", Font.PLAIN, 28));
		mainGUI.add(time, "cell 0 11 7 1,alignx left,growy");
		
		hoursTextField = new JTextField();
		hoursTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
					System.out.println("ILLEGAL ACTION");
					e.consume();
				}
			}
		});
		hoursTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		mainGUI.add(hoursTextField, "cell 0 13,grow");
		hoursTextField.setColumns(10);
		
		colon = new JLabel(":");
		colon.setFont(new Font("Tahoma", Font.PLAIN, 24));
		mainGUI.add(colon, "cell 1 13,alignx center,growy");
		
		minutesTextField = new JTextField();
		minutesTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
					System.out.println("ILLEGAL ACTION");
					e.consume();
				}
			}
		});
		minutesTextField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		mainGUI.add(minutesTextField, "cell 5 13,grow");
		minutesTextField.setColumns(10);
		
		latitude = new JLabel("Latitude");
		latitude.setFont(new Font("Tahoma", Font.PLAIN, 34));
		mainGUI.add(latitude, "flowx,cell 0 21 7 1,alignx left,growy");
		
		longitude = new JLabel("Longitude");
		longitude.setFont(new Font("Tahoma", Font.PLAIN, 34));
		mainGUI.add(longitude, "flowx,cell 0 25 7 1,alignx left,growy");
		
		starNames = new JCheckBox("Star Names");
		starNames.setFont(new Font("Tahoma", Font.PLAIN, 28));
		mainGUI.add(starNames, "cell 12 5,alignx left,aligny bottom");
		
		constellations = new JCheckBox("Constellations");
		constellations.setFont(new Font("Tahoma", Font.PLAIN, 28));
		mainGUI.add(constellations, "cell 12 7,alignx left,aligny bottom");
		
		planets = new JCheckBox("Planets");
		planets.setFont(new Font("Tahoma", Font.PLAIN, 28));
		mainGUI.add(planets, "cell 12 9,alignx left,aligny bottom");
		
		messierDeepSpace = new JCheckBox("Messier Objects");
		messierDeepSpace.setFont(new Font("Tahoma", Font.PLAIN, 28));
		mainGUI.add(messierDeepSpace, "cell 12 11 1 3,alignx left,aligny center");
		
		Box verticalBox = Box.createVerticalBox();
		mainGUI.add(verticalBox, "cell 12 1,alignx left,growy");
		
		JLabel lblNewLabel = new JLabel("Sky Map");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		mainGUI.add(lblNewLabel, "cell 0 0 13 1,grow");
		
		JLabel lblEnterLocation = new JLabel("Enter Location:");
		lblEnterLocation.setFont(new Font("Tahoma", Font.BOLD, 34));
		mainGUI.add(lblEnterLocation, "flowx,cell 0 19 9 1,alignx left,growy");
		
		JLabel enterDate = new JLabel("Enter Date:");
		enterDate.setFont(new Font("Tahoma", Font.BOLD, 30));
		mainGUI.add(enterDate, "cell 0 1 7 3,alignx center,aligny top");
		
		JLabel labels = new JLabel("Labels:");
		labels.setHorizontalAlignment(SwingConstants.CENTER);
		labels.setFont(new Font("Tahoma", Font.BOLD, 34));
		mainGUI.add(labels, "cell 12 3,growx,aligny center");
		
		btnSaveToDisk = new JButton("Save Image");
		btnSaveToDisk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String savedImageDirectory = FileSystemView.getFileSystemView().getDefaultDirectory().toString() + "\\spaceGUI\\yourImage.jpeg";
				File saveLocFolder = new File(savedImageDirectory.substring(0,savedImageDirectory.indexOf("yourImage.jpeg")));
				File saveLoc = new File(savedImageDirectory);
				int saveChecker = 0;
				
				// Check to see if screen shot folder does not exist, if it does not then make it.
				if(!saveLocFolder.exists()) {
					saveLocFolder.mkdir();
					saveChecker += 1;
				}
				else
					saveChecker += 1;
				
				// Check to see if the screen shot does not exist.
				// If the screen shot does not exist  then disable save button, change text to display no image found, and return image button to defaults
				if(!saveLoc.exists()) {
					btnSaveToDisk.setEnabled(false);
					btnSaveToDisk.setText("No Image Found");
					new java.util.Timer().schedule( 
					        new java.util.TimerTask() {
					            @Override
					            public void run() {
									btnSaveToDisk.setText("Re-Apply");
					            }
					        }, 
					        3000 
					);
				}
				else
					saveChecker += 1;
				
				if (saveChecker == 2) {
					// If the file and the folder exists, then open the directory.
					try {
						Desktop.getDesktop().open(new File(saveLocFolder.toString()));
						count = 0;
						copyFile(saveLoc,
					    		  new File(FileSystemView.getFileSystemView().getDefaultDirectory().toString() + "\\spaceGUI\\yourImage" + "1" + ".jpeg"));
						
						
						
						
						//
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
					saveChecker = 0;
			}
		});
		btnSaveToDisk.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnSaveToDisk.setEnabled(false);
		mainGUI.add(btnSaveToDisk, "cell 12 31,growx");
		
		latitudeComboBox = new JComboBox();
		latitudeComboBox.addItem(" ");
		latitudeComboBox.addItem("North");
		latitudeComboBox.addItem("South");
		mainGUI.add(latitudeComboBox, "cell 0 23 7 1,grow");
		
		longitudeComboBox = new JComboBox();
		longitudeComboBox.addItem(" ");
		longitudeComboBox.addItem("East");
		longitudeComboBox.addItem("West");
		mainGUI.add(longitudeComboBox, "cell 0 27 7 1,grow");
		
		refreshButton = new JButton("Refresh SkyMap");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//try {
					//ImageIcon ii = new ImageIcon("C:\\Users\\alexx\\OneDrive\\Documents\\Fall 2019\\CS 499\\waiting.jpg");
					//scrollPane_1.setViewportView(new JLabel(ii));
					setLabels();
					drawSky();
					//getSpaceObjects();
				//} catch () {
					// TODO Auto-generated catch block
				//	e.printStackTrace();
				//}
				setLabels();
			}
		});
		refreshButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		refreshButton.setEnabled(false);
		mainGUI.add(refreshButton, "cell 12 17,growx,aligny top");
		
		JButton applyButton = new JButton("Apply");
		applyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					//checkValues();
					setLabels();
					getSpaceObjects();
					drawSky();
					btnSaveToDisk.setText("Save Image");
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NullPointerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
						
			}
		});
		applyButton.setFont(new Font("Tahoma", Font.BOLD, 34));
		mainGUI.add(applyButton, "cell 0 31 7 1,growx");
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setViewportBorder(new LineBorder(Color.BLACK));
		//ImageIcon ii = new ImageIcon("C:\\Users\\alexx\\OneDrive\\Documents\\Fall 2019\\CS 499\\pluto.jpg");
		//scrollPane_1.setViewportView(new JLabel(ii));
		mainGUI.add(scrollPane_1, "cell 10 1 1 31,grow");
		
		JLabel lblHrsMins = new JLabel("Hrs              Mins");
		lblHrsMins.setFont(new Font("Tahoma", Font.PLAIN, 20));
		mainGUI.add(lblHrsMins, "cell 0 15 6 1,alignx right,aligny top");
		
		yearTextField = new JTextField();
		yearTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
					System.out.println("ILLEGAL ACTION");
					e.consume();
				}
			}
		});
		yearTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		mainGUI.add(yearTextField, "cell 5 9 2 1,alignx left,aligny top");
		yearTextField.setColumns(10);
		
		dayTextField = new JTextField();
		dayTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
					System.out.println("ILLEGAL ACTION");
					e.consume();
				}
			}
		});
		dayTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		mainGUI.add(dayTextField, "cell 5 7 2 1,growx,aligny top");
		dayTextField.setColumns(10);
		
		latTextField = new JTextField();
		latTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		latTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.' && e.getKeyChar() != '-') {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
					System.out.println("ILLEGAL ACTION");
					e.consume();
				}
			}
		});
		mainGUI.add(latTextField, "cell 6 21,grow");
		latTextField.setColumns(10);
		
		longTextField = new JTextField();
		longTextField.setFont(new Font("Tahoma", Font.PLAIN, 28));
		longTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '.' &&  e.getKeyChar() != '-') {
					e.consume();
				}
			}
			public void keyPressed(KeyEvent e) {
				if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_V) {
					System.out.println("ILLEGAL ACTION");
					e.consume();
				}
			}
		});
		mainGUI.add(longTextField, "cell 6 25,grow");
		longTextField.setColumns(10);
		
		
		
		
	}
}
