package DB;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import gui.ServerFormController;
import logic.Order;
import server.MainServer;

public class DBConnector {
	private static Order[] order = new Order[6];

//	private static String url;
//	private static String username;
//	private static String password;

//	private static String url = "jdbc:mysql://localhost:3306/test?serverTimezone=IST";
//	private static String username = "root";
//	private static String password = "211515838";

//	conn = DriverManager.getConnection("jdbc:mysql://localhost/bitemesystem?serverTimezone=IST", "root",
//	"@Elad15643");
//conn = DriverManager.getConnection("jdbc:mysql://localhost/bitemesystem?serverTimezone=IST", "root",
//	"@1234");
//conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/Assignment2?serverTimezone=IST", "root",
//	"211515838");

	public static Connection myConn;

	public DBConnector() {

	}

	public static Boolean connectToDB(String url, String username, String password) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			System.out.println("Driver definition succeed");
		} catch (Exception ex) {

			System.out.println("Driver definition failed");
			return false;
		}
		try {
			myConn = DriverManager.getConnection(url, username, password);
			System.out.println("SQL connection succeed");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			return false;
		}
		return true;

	}

	public void closeConnection() {
		try {
			if (!myConn.isClosed())
				myConn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}

}