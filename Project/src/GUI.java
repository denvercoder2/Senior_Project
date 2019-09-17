import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;

public class GUI extends JFrame {

	private JPanel contentPane;
	private final JLabel month = new JLabel("Month");
	private JTextField monthTextField;
	private JTextField dayTextField;
	private JLabel year;
	private JTextField yearTextField;

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
		month.setBounds(26, 123, 115, 33);
		contentPane.add(month);
		
		monthTextField = new JTextField();
		monthTextField.setFont(new Font("Tahoma", Font.PLAIN, 34));
		monthTextField.setBounds(142, 121, 52, 36);
		contentPane.add(monthTextField);
		monthTextField.setColumns(10);
		
		JLabel day = new JLabel("Day");
		day.setFont(new Font("Tahoma", Font.PLAIN, 34));
		day.setBounds(26, 184, 115, 33);
		contentPane.add(day);
		
		dayTextField = new JTextField();
		dayTextField.setFont(new Font("Tahoma", Font.PLAIN, 34));
		dayTextField.setBounds(142, 181, 52, 39);
		contentPane.add(dayTextField);
		dayTextField.setColumns(10);
		
		year = new JLabel("Year");
		year.setFont(new Font("Tahoma", Font.PLAIN, 34));
		year.setBounds(26, 245, 115, 33);
		contentPane.add(year);
		
		yearTextField = new JTextField();
		yearTextField.setBounds(142, 245, 52, 39);
		contentPane.add(yearTextField);
		yearTextField.setColumns(10);
	}
}
