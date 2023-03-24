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

public class SearchMis extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtfees;
	private JTextField txtduration;
	private Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchMis frame = new SearchMis();
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
	public SearchMis() {
		con = DatabaseConnection2.createConnection();
		AddComponents();
	}
	public void AddComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 809, 526);
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
		
		JLabel lblNewLabel_1 = new JLabel("Course ID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(87, 108, 110, 24);
		contentPane.add(lblNewLabel_1);
		
		txtid = new JTextField();
		txtid.setBounds(361, 112, 160, 19);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(this);
		btnNewButton.setForeground(new Color(0, 153, 51));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(635, 111, 103, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Course Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(87, 203, 110, 24);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fees");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(87, 251, 110, 19);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Duration");
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
		
		txtfees = new JTextField();
		txtfees.setDisabledTextColor(Color.BLACK);
		txtfees.setEnabled(false);
		txtfees.setEditable(false);
		txtfees.setBounds(287, 252, 172, 19);
		contentPane.add(txtfees);
		txtfees.setColumns(10);
		
		txtduration = new JTextField();
		txtduration.setDisabledTextColor(Color.BLACK);
		txtduration.setEnabled(false);
		txtduration.setEditable(false);
		txtduration.setBounds(287, 298, 172, 19);
		contentPane.add(txtduration);
		txtduration.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = txtid.getText();
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Course ID required","Mandatory",JOptionPane.ERROR_MESSAGE);
		}
		else {
			String strsql = "select * from Courses where Courseid=?";
			PreparedStatement ps;
			ResultSet rs;
			
			try {
				ps = con.prepareStatement(strsql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				if(rs.next()) {
					String nm = rs.getString("CourseName");
					String em = rs.getString("Duration");
					int x = rs.getInt("Fees");
					String s = String.valueOf(x);
					txtname.setText(nm);
					txtduration.setText(em);
					txtfees.setText(s);
					
					
				}
				else {
					JOptionPane.showMessageDialog(this, "No Course ID is exist");
				}
				
			}
			catch(SQLException s) {
				s.printStackTrace();
			}
		}
	}
	
}
