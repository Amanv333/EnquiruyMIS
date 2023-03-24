package app.mis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import app.dbtask.DatabaseConnection2;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Counsellor extends JFrame implements ActionListener {

	private JPanel contentPane;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private JComboBox cmbcouseid;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JTextField txtname;
	private JTextField txtphone;
	private JTextField txtmail;
	private JTextField txtcollege;
	private JButton btnSubmit;
	private JTextArea txtRemark,txtAddress;
	private JMenuItem mntdel,mntSearchnum,mntVenq;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Counsellor frame = new Counsellor();
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
	
	public void fillCombo()
	{
		String strsql="select Courseid from courses";
		
		try {
			ps=con.prepareStatement(strsql);
		rs=	ps.executeQuery();
			while(rs.next())
			{
				
				String id=rs.getString("Courseid");
				cmbcouseid.addItem(id);
			}
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		
		finally {
			
			try {
				if(ps!=null)
					ps.close();
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}
	public Counsellor() {
		con = DatabaseConnection2.createConnection();
		AddComponent();

	}
	public void AddComponent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 991, 736);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Enquiry");
		menuBar.add(mnNewMenu);
		
		mntdel = new JMenuItem("del phone");
		mntdel.addActionListener(this);
		mnNewMenu.add(mntdel);
		
		mntSearchnum = new JMenuItem("Search Enquiry");
		mnNewMenu.add(mntSearchnum);
		mntSearchnum.addActionListener(this);
		
		mntVenq = new JMenuItem("View Enquiries");
		mntVenq.addActionListener(this);
		mnNewMenu.add(mntVenq);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Counsellor");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.BOLD, 24));
		lblNewLabel.setBounds(401, 26, 270, 84);
		contentPane.add(lblNewLabel);
		
		cmbcouseid = new JComboBox();
		cmbcouseid.setBounds(367, 120, 185, 21);
		cmbcouseid.addActionListener(this);
		cmbcouseid.setModel(new DefaultComboBoxModel(new String[] {"Select Course ID "}));
		fillCombo();
		
		contentPane.add(cmbcouseid);
		
		lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_1.setBounds(130, 167, 117, 27);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Phone");
		lblNewLabel_2.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_2.setBounds(130, 228, 117, 27);
		contentPane.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_3.setBounds(130, 289, 117, 27);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("College Name");
		lblNewLabel_4.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_4.setForeground(new Color(0, 0, 0));
		lblNewLabel_4.setBounds(130, 337, 117, 37);
		contentPane.add(lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel("Address");
		lblNewLabel_5.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_5.setBounds(130, 411, 117, 21);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6 = new JLabel("Remarks");
		lblNewLabel_6.setFont(new Font("Arial Black", Font.BOLD, 14));
		lblNewLabel_6.setBounds(130, 524, 122, 21);
		contentPane.add(lblNewLabel_6);
		
		txtAddress = new JTextArea();
		txtAddress.setLineWrap(true);
		txtAddress.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtAddress.setBounds(492, 411, 211, 78);
		contentPane.add(txtAddress);
		
		txtRemark = new JTextArea();
		txtRemark.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtRemark.setLineWrap(true);
		txtRemark.setBounds(492, 524, 211, 78);
		contentPane.add(txtRemark);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setForeground(Color.BLUE);
		btnSubmit.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnSubmit.setBounds(367, 635, 105, 37);
		btnSubmit.addActionListener(this);
		contentPane.add(btnSubmit);
		
		txtname = new JTextField();
		txtname.setBounds(492, 173, 211, 21);
		contentPane.add(txtname);
		txtname.setColumns(10);
		
		txtphone = new JTextField();
		txtphone.setText("");
		txtphone.setBounds(492, 234, 211, 19);
		contentPane.add(txtphone);
		txtphone.setColumns(10);
		
		txtmail = new JTextField();
		txtmail.setText("");
		txtmail.setBounds(492, 295, 211, 19);
		contentPane.add(txtmail);
		txtmail.setColumns(10);
		
		txtcollege = new JTextField();
		txtcollege.setBounds(492, 348, 211, 19);
		contentPane.add(txtcollege);
		txtcollege.setColumns(10);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
String caption = e.getActionCommand();
		
		if(caption.equalsIgnoreCase("del phone")) {
			Deletecnslr ac = new Deletecnslr();
			ac.setVisible(true);
		}
		if(caption.equalsIgnoreCase("search enquiry")) {
			Searchenq sm = new Searchenq();
			sm.setVisible(true);
		}
		if(caption.equalsIgnoreCase("view Enquiries")) {
			ViewEnquiry sm = new ViewEnquiry();
			sm.setVisible(true);
		}
		String nm= txtname.getText();
		String em = txtmail.getText();
		String ph = txtphone.getText();
		String ad = txtAddress.getText();
		String re = txtRemark.getText();
		String clg = txtcollege.getText();
		String id;
		java.util.Date dt = new java.util.Date();
		long d = dt.getTime();
		java.sql.Date sd = new java.sql.Date(d);
	
			id = (String) cmbcouseid.getSelectedItem();
			
		if (nm.isEmpty()||ph.isEmpty()||em.isEmpty()) {
			JOptionPane.showMessageDialog(this, "please enter details");
		}
		
		if(nm.length()>0&&ph.length()>0&&em.length()>0) {
			
		
		if(e.getSource()==btnSubmit) {
			String strsql = "Insert into enquiry_details(CourseID, Name, Phone, Email, CollegeName, Address, Date, Remarks) values(?,?,?,?,?,?,?,?)";
			try {
				ps = con.prepareStatement(strsql);
				ps.setString(1, id);
				ps.setString(2, nm);
				ps.setString(3, ph);
				ps.setString(4, em);
				ps.setString(5, clg);
				ps.setString(6, ad);
				ps.setDate(7, sd);
				ps.setString(8, re);
				
				int status = ps.executeUpdate();
				if (status>0) {
					JOptionPane.showMessageDialog(this, "Enquiry Added");
					txtAddress.setText("");
					txtcollege.setText("");
					txtmail.setText("");
					txtname.setText("");
					txtphone.setText("");
					txtRemark.setText("");
					

					
					
				}
				


			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			finally {
				if (ps!=null) {
					
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}}
			
		}
	}
}
