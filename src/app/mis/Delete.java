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

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class Delete extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtid;
	private Connection con;
	private PreparedStatement ps;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Delete frame = new Delete();
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
	public Delete() {
		con = DatabaseConnection2.createConnection();
		AddComponents();
	}
	public void AddComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 816, 755);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Course ID");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(58, 221, 200, 49);
		contentPane.add(lblNewLabel);
		
		txtid = new JTextField();
		txtid.setBounds(386, 239, 242, 31);
		contentPane.add(txtid);
		txtid.setColumns(10);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setBackground(new Color(255, 99, 71));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setBounds(264, 425, 162, 41);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String id = txtid.getText().trim();
		
		if (id.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter Course id", "Required Field", JOptionPane.ERROR_MESSAGE);
		}
		else {
			int status = JOptionPane.showConfirmDialog(this, "Are you sure want to delete "+id+" course id");
			
			if (status==0) {
				String strdel = ("delete from courses where Courseid =?");
				
				try {
				ps = con.prepareStatement(strdel);
				ps.setString(1, id);
				int s = ps.executeUpdate();
				if (s>0) {
					JOptionPane.showMessageDialog(this, "Course Deleted Succesfully");
				}
				else {
					JOptionPane.showMessageDialog(this, "No such Id is found ", "Id checking", JOptionPane.ERROR_MESSAGE);
				}
				
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				finally {
					if(ps!=null) {
						try 
						{
							ps.close();
						}
						catch(SQLException se ) {
							se.printStackTrace();
						}
						
					}
				}
				
			}
		}

		
		
	}
}
