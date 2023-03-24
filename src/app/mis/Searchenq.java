package app.mis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.dbtask.DatabaseConnection2;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class Searchenq extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtphone;
	private JTextField txtname;
	private JTextField txtmail;
	private JTextField txtclg;
	private Connection con;
	private JTextField txtid;
	private JTextArea txtaddress,txtrem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Searchenq frame = new Searchenq();
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
	public Searchenq() {
		con = DatabaseConnection2.createConnection();
		AddComponents();
	}
	public void AddComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 840, 685);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 255, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search");
		lblNewLabel.setForeground(new Color(0, 51, 204));
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 17));
		lblNewLabel.setBounds(341, 23, 103, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("phone");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(87, 108, 110, 24);
		contentPane.add(lblNewLabel_1);
		
		txtphone = new JTextField();
		txtphone.setBounds(361, 112, 160, 19);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(this);
		btnNewButton.setForeground(new Color(0, 153, 51));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(635, 111, 103, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(87, 203, 110, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(87, 251, 110, 19);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("College ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(87, 297, 110, 19);
		contentPane.add(lblNewLabel_4);
		
		txtname = new JTextField();
		txtname.setDisabledTextColor(Color.BLACK);
		txtname.setSelectedTextColor(new Color(0, 0, 51));
		txtname.setEnabled(false);
		txtname.setEditable(false);
		txtname.setBounds(287, 207, 172, 19);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtmail = new JTextField();
		txtmail.setDisabledTextColor(Color.BLACK);
		txtmail.setEnabled(false);
		txtmail.setEditable(false);
		txtmail.setBounds(287, 252, 172, 19);
		contentPane.add(txtmail);
		txtmail.setColumns(10);
		
		txtclg = new JTextField();
		txtclg.setDisabledTextColor(Color.BLACK);
		txtclg.setEnabled(false);
		txtclg.setEditable(false);
		txtclg.setBounds(287, 298, 172, 19);
		contentPane.add(txtclg);
		txtclg.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Course ID");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(87, 347, 60, 19);
		contentPane.add(lblNewLabel_5);
		
		txtid = new JTextField();
		txtid.setDisabledTextColor(Color.BLACK);
		txtid.setEnabled(false);
		txtid.setEditable(false);
		txtid.setBounds(287, 348, 172, 19);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Adddress");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(87, 392, 82, 30);
		contentPane.add(lblNewLabel_6);
		
		txtaddress = new JTextArea();
		txtaddress.setDisabledTextColor(Color.BLACK);
		txtaddress.setEnabled(false);
		txtaddress.setEditable(false);
		txtaddress.setLineWrap(true);
		txtaddress.setBounds(287, 396, 172, 73);
		contentPane.add(txtaddress);
		
		JLabel lblNewLabel_7 = new JLabel("Remark");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(87, 497, 60, 24);
		contentPane.add(lblNewLabel_7);
		
		txtrem = new JTextArea();
		txtrem.setDisabledTextColor(Color.BLACK);
		txtrem.setEnabled(false);
		txtrem.setEditable(false);
		txtrem.setBounds(287, 498, 172, 73);
		contentPane.add(txtrem);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = txtphone.getText();
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Phone required","Mandatory",JOptionPane.ERROR_MESSAGE);
		}
		else {
			String strsql = "select * from enquiry_details where phone=?";
			PreparedStatement ps;
			ResultSet rs;
			
			try {
				ps = con.prepareStatement(strsql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				if(rs.next()) {
					String nm = rs.getString("Name");
					String em = rs.getString("Email");
					String clg = rs.getString("CollegeName");
					String cid = rs.getString("CourseID");
					String add = rs.getString("Address");
					String rem = rs.getString("Remarks");

			
					txtname.setText(nm);
					txtclg.setText(clg);
					txtmail.setText(em);
					txtaddress.setText(add);
					txtid.setText(cid);
					txtrem.setText(rem);
					
					
					
				}
				else {
					JOptionPane.showMessageDialog(this, "No Enquiry is exist");
				}
				
			}
			catch(SQLException s) {
				s.printStackTrace();
			}
		}
	}
}
