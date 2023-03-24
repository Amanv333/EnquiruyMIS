package app.dbtask;

import java.sql.*;



public class DatabaseConnection2 {
	private static Connection con;
	
	public static Connection createConnection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/enq_mis","root","Amanv123@");

		}
		catch(ClassNotFoundException|SQLException c) {
			c.printStackTrace();
		}
		return con;
		
	}
	
	public static void closeConnection() {
		if(con!=null) {
			try {
				con.close();
				System.out.println("connection is closed");
				
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
	Connection cn = createConnection();
	System.out.println(cn);
}
}
