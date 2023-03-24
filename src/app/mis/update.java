package app.mis;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
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

public class update extends JFrame implements ActionListener {

	private JPanel contentPane1;
	private JTextField txtemail;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;


	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					update frame = new update();
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
	
	JComboBox<String>cmbphone;
	JTextArea txtaddress;
	JButton btnupdate;
	private JTextField txtName;
	
	public void fillCombo()
	{
		String strsql="select Courseid from courses";
		
		try {
			ps=con.prepareStatement(strsql);
		rs=	ps.executeQuery();
			while(rs.next())
			{
				
				String id=rs.getString("Courseid");
				cmbphone.addItem(id);
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
	public update() {
		con = DatabaseConnection2.createConnection();
		AddComponents();
		
	}
	
	
	
	
	 
	public void AddComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 991, 721);
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane1);
		contentPane1.setLayout(null);
		
		cmbphone = new JComboBox();
		
		cmbphone.addActionListener(this);
		
		cmbphone.setModel(new DefaultComboBoxModel(new String[] {"Select Course ID "}));
		fillCombo();
		
		cmbphone.setBounds(347, 104, 237, 22);
		contentPane1.add(cmbphone);
		
		txtemail = new JTextField();
		txtemail.setBounds(381, 231, 191, 66);
		contentPane1.add(txtemail);
		txtemail.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Fees");
		lblNewLabel.setBounds(126, 238, 105, 52);
		contentPane1.add(lblNewLabel);
		
		txtaddress = new JTextArea();
		txtaddress.setBounds(381, 381, 267, 124);
		contentPane1.add(txtaddress);
		
		JLabel lblNewLabel_1 = new JLabel("Duration");
		lblNewLabel_1.setBounds(117, 413, 127, 93);
		contentPane1.add(lblNewLabel_1);
		
		 btnupdate = new JButton("Update");
		btnupdate.addActionListener(this);
		btnupdate.setBounds(404, 565, 168, 42);
		contentPane1.add(btnupdate);
		
		JLabel lblNewLabel_2 = new JLabel("Course Name");
		lblNewLabel_2.setBounds(128, 179, 62, 22);
		contentPane1.add(lblNewLabel_2);
		
		 txtName = new JTextField();
		 txtName.setDisabledTextColor(Color.BLACK);
		 txtName.setSelectedTextColor(Color.BLACK);
		 txtName.setSelectionColor(Color.BLACK);
		txtName.setEnabled(false);
		txtName.setEditable(false);
		txtName.setBounds(381, 181, 140, 19);
		contentPane1.add(txtName);
		txtName.setColumns(10);
	}
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id=null,name=null;
		if(e.getSource()==cmbphone)
		{
			//System.out.println("combo is selected");
			id=(String)cmbphone.getSelectedItem();
		String strsql="select * from courses where Courseid=?";
		try {
			
			ps=con.prepareStatement(strsql);
			ps.setString(1, id);
			rs=ps.executeQuery();
			if(rs.next())
			{
				int user_email=rs.getInt("Fees");
				String user_address=rs.getString("Duration");
				String s = String.valueOf(user_email);
				txtemail.setText(s);
				txtaddress.setText(user_address);
				name=rs.getString("CourseName");
				txtName.setText(name);
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
		
		
		if(e.getSource()==btnupdate)
		{	
			
			//System.out.println("button is clicked");
			
			id=(String)cmbphone.getSelectedItem();
			
			String newmail=txtemail.getText();
			String newaddress=txtaddress.getText();
			
	String strupdate="update courses set Fees=?,Duration=? where Courseid=?";
	try {
		ps=con.prepareStatement(strupdate);
		ps.setString(1,newmail);
		ps.setString(2,newaddress);
		ps.setString(3,id);
		System.out.println(ps+name);
	int row=ps.executeUpdate();
	
	if(row>0)
	{
		JOptionPane.showMessageDialog(this, "Courses details has been updated successfully");
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
			
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
	}
}}	
}


