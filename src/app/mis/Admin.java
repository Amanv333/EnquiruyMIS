package app.mis;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class Admin extends JFrame implements ActionListener
{

	private JPanel contentPane;
	JMenuItem mnt_add,mnt_Bycourse,mnt_update,mntDelete,mnt_view,mnt_num,mntvcourse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin frame = new Admin();
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
	public Admin() {
		AddComponent();
	}
	public void AddComponent() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1128, 542);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Courses");
		menuBar.add(mnNewMenu);
		
		 mnt_add = new JMenuItem("Add");
		mnNewMenu.add(mnt_add);
		mnt_add.addActionListener(this);
		
		
		 mnt_update = new JMenuItem("Update");
		mnNewMenu.add(mnt_update);
		mnt_update.addActionListener(this);
		
		
		mntDelete = new JMenuItem("Delete");
		mnNewMenu.add(mntDelete);
		mntDelete.addActionListener(this);
		

		
		
		JMenu mnNewMenu_1 = new JMenu("Search");
		menuBar.add(mnNewMenu_1);
		
		mnt_Bycourse = new JMenuItem("ByCourseID");
		mnt_Bycourse.addActionListener(this);
		mnNewMenu_1.add(mnt_Bycourse);
		
		mntvcourse = new JMenuItem("View all Courses");
		mntvcourse.addActionListener(this);
		mnNewMenu_1.add(mntvcourse);
		
		JMenu mnNewMenu_2 = new JMenu("Enquiries");
		menuBar.add(mnNewMenu_2);
		mnt_num = new JMenuItem("PhoneNumber");
		mnt_num.addActionListener(this);
		mnNewMenu_2.add(mnt_num);
		
		mnt_view = new JMenuItem("View All Enquires");
		mnt_view.addActionListener(this);
		mnNewMenu_2.add(mnt_view);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin");
		lblNewLabel.setForeground(new Color(0, 0, 139));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
		lblNewLabel.setBounds(507, 47, 137, 40);
		contentPane.add(lblNewLabel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String caption = e.getActionCommand();
		
		if(caption.equalsIgnoreCase("add")) {
			AddCourse ac = new AddCourse();
			ac.setVisible(true);
		}
		if(caption.equalsIgnoreCase("bycourseid")) {
			SearchMis sm = new SearchMis();
			sm.setVisible(true);
		}
		if(caption.equalsIgnoreCase("update")) {
			update u = new update();
			u.setVisible(true);
		}
		if(caption.equalsIgnoreCase("Delete")) {
			Delete d = new Delete();
			d.setVisible(true);
		}
		if(caption.equalsIgnoreCase("phonenumber")) {
			Searchenq d = new Searchenq();
			d.setVisible(true);
		}
		if(caption.equalsIgnoreCase("view all enquires")) {
			ViewEnquiry d = new ViewEnquiry();
			d.setVisible(true);
		}
		if(caption.equalsIgnoreCase("view all courses")) {
			ViewCourses d = new ViewCourses();
			d.setVisible(true);
		}
	}
}
