package app.mis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.Font;

import app.dbtask.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ViewEnquiry extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private Connection con;
	private String[]columnNames= {"CourseID", "Name", "Phone", "Email", "CollegeName", "Address", "Remarks"};
	private Object[][]data;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewEnquiry frame = new ViewEnquiry();
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
	public ViewEnquiry() {
		con = DatabaseConnection2.createConnection();
		CreateContact();
	}
	//find num of records(will create dd rows)
	//create obj of Dbl dimention Array
	//poppulate dd array from db table contacts
	
	public void populateArray() {
		PreparedStatement pscount , psdata;
		ResultSet rscount,rsdata;
		try {
			String strcount="select count(*) from enquiry_details";
			pscount=con.prepareStatement(strcount);
			rscount=pscount.executeQuery();
			if(rscount.next()) {
				int row_count = rscount.getInt(1);//it will read data from first column
				//System.out.println(row_count);
				data = new Object[row_count][7];//creating obj of dd array
				
				String stssql = "select * from enquiry_details";
				psdata = con.prepareStatement(stssql);
				rsdata = psdata.executeQuery();//it will fetch all the records from contacts table and assign ref to rsdata
				int row =0;
				while(rsdata.next()) {
					String nm = rsdata.getString("Name");
					String cid = rsdata.getString("CourseID");
					String clg = rsdata.getString("CollegeName");
					String ad = rsdata.getString("Address");
					String rem = rsdata.getString("Remarks");
//					long dt = rsdata.getLong("Date");
//					String d = String.valueOf(dt);

					String em = rsdata.getString("Email");
					String ph = rsdata.getString("Phone");
					
					data[row][0]=cid;//filling columns in row column of dd array from database
					data[row][1]=nm;
					data[row][02]=ph;
					data[row][03]=em;
					data[row][04]=clg;
					data[row][05]=ad;
				
					data[row][06]=rem;


					row++;

					

				}
			}
			else {
				JOptionPane.showMessageDialog(this, "No Enquiry to show");
			}
		}
		catch(SQLException s) {
			s.printStackTrace();
		}
	}
	
	private void CreateContact() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 842, 612);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(69, 26, 724, 513);
		contentPane.add(scrollPane);
		
		table = new JTable();
		JTableHeader header =table.getTableHeader();
		header.setForeground(Color.white);
		header.setBackground(Color.BLUE);
		header("Comic Sans Ms",Font.PLAIN,20);
		
		populateArray();
//		for(int i=0;i<2;i++) {
//			for(int j = 0;j<3;j++) {
//				System.out.println(data[i][j]+" ");
//			}
//		}
		table.setModel(new DefaultTableModel(data,columnNames));//it will show record on table
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{"aman"},
//				{null},
//			},
//			new String[] {
//				"Name"
//			}
//		));
		scrollPane.setViewportView(table);
	}

	private void header(String string, int plain, int i) {
		// TODO Auto-generated method stub
		
	}
}
