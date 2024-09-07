/*
  Loading the driver:
  	Driver is a translator between Java code and oracle code.
  	Driver is a class.
  	class Class
  	{
  		static void forName(String driver) throws CLassNotFoundException
  	}
  	class DriverManager
	{
		static Connection getConnection(String url, String user, String pwd) throws SQLException
	}
 */

import java.sql.*;
import java.util.Scanner;
public class DBConnect3 {

	public static void main(String[] args) {
		try
		{
			String driver = "oracle.jdbc.driver.OracleDriver" ;
			Class.forName(driver);
			System.out.println("Driver Loaded");
			
			String url = "jdbc:oracle:thin:@localhost:1521:xe" ;
			String user = "SYSTEM" ;
			String pwd = "pritam" ;
			
			Connection con = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection is ready") ;
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Account Num : ");
			int num = sc.nextInt();
			System.out.println("Enter Amount to deposit : ");
			int amt = sc.nextInt();
			
			String query = "update account set balance = balance + ? where num = ?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, amt);
			ps.setInt(2, num);
			int count = ps.executeUpdate();
			if (count>0)
				System.out.println("Aount Deposited");
			else
				System.out.println("Error : Invalid acc num");
			con.close();
			System.out.println("Connection is closed");
		}
		catch(Exception e)
		{
			System.out.println("Error : No such driver to load");
		}

	}

}
