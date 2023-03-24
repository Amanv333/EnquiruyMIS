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
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;

public class AddCourse extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtCourseID;
	private JTextField txtCourseName;
	private JTextField txtFees;
	private JTextField txtDuration;
	private Connection con;
	JButton btn_add;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddCourse frame = new AddCourse();
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
	public AddCourse() {
		con = DatabaseConnection2.createConnection();
		AddComponents();

	}
	public void AddComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 932, 498);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Courses");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(387, 36, 152, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("CourseID");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(190, 111, 130, 27);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Course Name");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(190, 159, 99, 33);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fees");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(190, 202, 99, 27);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Duration");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(190, 251, 99, 21);
		contentPane.add(lblNewLabel_4);
		
		btn_add = new JButton("Add");
		btn_add.addActionListener(this);
		btn_add.setBackground(Color.CYAN);
		btn_add.setBounds(350, 323, 85, 21);
		contentPane.add(btn_add);
		
		txtCourseID = new JTextField();
		txtCourseID.setBounds(363, 116, 163, 19);
		contentPane.add(txtCourseID);
		txtCourseID.setColumns(10);
		
		txtCourseName = new JTextField();
		txtCourseName.setBounds(363, 167, 163, 19);
		contentPane.add(txtCourseName);
		txtCourseName.setColumns(10);
		
		txtFees = new JTextField();
		txtFees.setBounds(363, 207, 163, 19);
		contentPane.add(txtFees);
		txtFees.setColumns(10);
		
		txtDuration = new JTextField();
		txtDuration.setBounds(363, 251, 163, 21);
		contentPane.add(txtDuration);
		txtDuration.setColumns(10);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		String id = txtCourseID.getText().trim();
		String nm = txtCourseName.getText().trim();
		String fee = txtFees.getText();
		int x = Integer.parseInt(fee);
		String Dur = txtDuration.getText().trim();
		
		
		if(id.length()>0&&nm.length()>0&& Dur.length()>0) {
			String strinsert = "insert into courses(Courseid, CourseName, Fees, Duration)values(?,?,?,?)";
			PreparedStatement ps =null;
			try {
				ps = con.prepareStatement(strinsert);
				ps.setString(1, id);
				ps.setString(2, nm);
				ps.setInt(3, x);
				ps.setString(4, Dur);
				int status = ps.executeUpdate();
				if(status>0) {
					JOptionPane.showMessageDialog(this, "course added");
					txtCourseID.setText("");
					txtCourseName.setText("");
					txtFees.setText("");
					txtDuration.setText("");
				}

			}
			catch(SQLException s) {
				s.printStackTrace();
			}
			finally {
				if(ps!=null) {
					try {
						ps.close();
					}
					catch(SQLException s) {
						s.printStackTrace();
					}
				
				}
			}
		}	


		
		
	}
}
