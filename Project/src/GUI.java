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

public class GUI extends JFrame {

	private JPanel contentPane;
	private final JLabel month = new JLabel("Month");
	private JTextField monthTextField;
	private JTextField dayTextField;
	private JLabel year;
	private JTextField yearTextField;
	private JLabel lblTime;
	private JTextField textField;
	private JLabel label;
	private JTextField textField_1;
	private JLabel lblLatitude;
	private JTextField textField_2;
	private JLabel lblLongitude;
	private JTextField textField_3;
	private JLabel lblMinutes;
	private JTextField textField_4;

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

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 2500, 1500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		month.setFont(new Font("Tahoma", Font.PLAIN, 34));
		month.setBounds(26, 215, 115, 33);
		contentPane.add(month);
		
		monthTextField = new JTextField();
		monthTextField.setFont(new Font("Tahoma", Font.PLAIN, 34));
		monthTextField.setBounds(142, 213, 52, 36);
		contentPane.add(monthTextField);
		monthTextField.setColumns(10);
		
		JLabel day = new JLabel("Day");
		day.setFont(new Font("Tahoma", Font.PLAIN, 34));
		day.setBounds(26, 276, 115, 33);
		contentPane.add(day);
		
		dayTextField = new JTextField();
		dayTextField.setFont(new Font("Tahoma", Font.PLAIN, 34));
		dayTextField.setBounds(142, 276, 52, 39);
		contentPane.add(dayTextField);
		dayTextField.setColumns(10);
		
		year = new JLabel("Year");
		year.setFont(new Font("Tahoma", Font.PLAIN, 34));
		year.setBounds(26, 337, 115, 33);
		contentPane.add(year);
		
		yearTextField = new JTextField();
		yearTextField.setBounds(142, 343, 52, 39);
		contentPane.add(yearTextField);
		yearTextField.setColumns(10);
		
		lblTime = new JLabel("Time:");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblTime.setBounds(26, 399, 115, 33);
		contentPane.add(lblTime);
		
		textField = new JTextField();
		textField.setBounds(26, 441, 49, 39);
		contentPane.add(textField);
		textField.setColumns(10);
		
		label = new JLabel(":");
		label.setBounds(84, 444, 10, 33);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setBounds(101, 441, 49, 39);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		lblLatitude = new JLabel("Latitude");
		lblLatitude.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblLatitude.setBounds(21, 794, 173, 33);
		contentPane.add(lblLatitude);
		
		textField_2 = new JTextField();
		textField_2.setBounds(186, 794, 52, 39);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		lblLongitude = new JLabel("Longitude");
		lblLongitude.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblLongitude.setBounds(22, 855, 154, 47);
		contentPane.add(lblLongitude);
		
		textField_3 = new JTextField();
		textField_3.setBounds(186, 862, 52, 39);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		lblMinutes = new JLabel("Minutes");
		lblMinutes.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lblMinutes.setBounds(22, 933, 150, 33);
		contentPane.add(lblMinutes);
		
		textField_4 = new JTextField();
		textField_4.setBounds(186, 933, 52, 39);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		JCheckBox chckbxStarNames = new JCheckBox("Star Names");
		chckbxStarNames.setFont(new Font("Tahoma", Font.PLAIN, 34));
		chckbxStarNames.setBounds(2000, 343, 429, 41);
		contentPane.add(chckbxStarNames);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Constellations");
		chckbxNewCheckBox.setFont(new Font("Tahoma", Font.PLAIN, 34));
		chckbxNewCheckBox.setBounds(2000, 412, 429, 41);
		contentPane.add(chckbxNewCheckBox);
		
		JCheckBox chckbxPlanets = new JCheckBox("Planets");
		chckbxPlanets.setFont(new Font("Tahoma", Font.PLAIN, 34));
		chckbxPlanets.setBounds(2000, 473, 221, 41);
		contentPane.add(chckbxPlanets);
		
		JCheckBox chckbxMessierDeepSpace = new JCheckBox("Messier Deep Space Objects");
		chckbxMessierDeepSpace.setFont(new Font("Tahoma", Font.PLAIN, 30));
		chckbxMessierDeepSpace.setBounds(2000, 534, 429, 41);
		contentPane.add(chckbxMessierDeepSpace);
		
		Box verticalBox = Box.createVerticalBox();
		verticalBox.setBounds(2018, 155, 1, 1);
		contentPane.add(verticalBox);
		
		JLabel lblNewLabel = new JLabel("Sky Map");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel.setBounds(0, 28, 2468, 67);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Apply");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 34));
		btnNewButton.setBounds(26, 1082, 212, 67);
		contentPane.add(btnNewButton);
		
		JLabel lblEnterLocation = new JLabel("Enter Location:");
		lblEnterLocation.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblEnterLocation.setBounds(21, 726, 271, 33);
		contentPane.add(lblEnterLocation);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Date:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblNewLabel_1.setBounds(26, 155, 212, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblLabels = new JLabel("Labels:");
		lblLabels.setHorizontalAlignment(SwingConstants.CENTER);
		lblLabels.setFont(new Font("Tahoma", Font.BOLD, 34));
		lblLabels.setBounds(2000, 279, 429, 40);
		contentPane.add(lblLabels);
	}
}
