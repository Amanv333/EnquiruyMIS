package app.mis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;

public class Login extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtid;
	private JPasswordField txtpass;
	JButton btnNewButton;
	JRadioButton rdcnslr,rdAdmin;
	private final ButtonGroup rd = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		AddComponents();
	
	}
	
	public void AddComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 489);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 16));
		lblNewLabel.setBounds(282, 21, 90, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("UserID");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_1.setBounds(71, 95, 90, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_2.setBounds(71, 165, 90, 24);
		contentPane.add(lblNewLabel_2);
		
		txtid = new JTextField();
		txtid.setBounds(259, 100, 143, 19);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		rdAdmin = new JRadioButton("Admin");
		rdAdmin.setFont(new Font("Arial", Font.BOLD, 10));
		rdAdmin.setBounds(178, 239, 103, 21);
		contentPane.add(rdAdmin);
		rd.add(rdAdmin);
		
		rdcnslr = new JRadioButton("Councellor");
		rdcnslr.setFont(new Font("Arial", Font.BOLD, 10));
		rdcnslr.setBounds(299, 239, 103, 21);
		contentPane.add(rdcnslr);
		rd.add(rdcnslr);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(259, 170, 143, 19);
		contentPane.add(txtpass);
		
		btnNewButton = new JButton("Login");
		btnNewButton.setForeground(new Color(30, 144, 255));
		btnNewButton.setFont(new Font("Arial Black", Font.BOLD, 11));
		btnNewButton.setBounds(243, 299, 85, 21);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String id = txtid.getText().trim();
		char[] pass = txtpass.getPassword();
		String pwd = String.valueOf(pass).trim();
	
		
		String user = "";
		
		if (rdAdmin.isSelected()) {
			user = rdAdmin.getText();
		}
		if (rdcnslr.isSelected()) {
			user = rdcnslr.getText();
		}
		
		if(id.isEmpty()&&pwd.isEmpty()) {
			JOptionPane.showMessageDialog(this, "please enter details");
		}
		if (id.equalsIgnoreCase("akash")&&pwd.equalsIgnoreCase("Verma")&&user.equalsIgnoreCase("Admin")) {
			Admin a =new Admin();
			a.setVisible(true);
		}
		if (id.equalsIgnoreCase("aman")&&pwd.equalsIgnoreCase("Verma")&&user.equalsIgnoreCase("Councellor")) {
			Counsellor a =new Counsellor();
			a.setVisible(true);
		}
		
		else {
			JOptionPane.showMessageDialog(this, "Please enter valid id or password");
		}
		
	}
}
