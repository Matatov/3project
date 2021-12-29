package DB;

import java.io.BufferedInputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.Delivery;
import logic.Order;
import logic.Restaurant;
import logic.User;

public class BMMenagerDBController {
	static ResultSet rs;

	public static ArrayList<User> getCustomerDetailsByAuthorization(String Authorization) { // Authorization=Customer
		ArrayList<User> usersList = new ArrayList<>();

		String selectQuery = "SELECT * FROM test.users WHERE Authorization = '" + Authorization + "';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					User userDetails = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getString(10), rs.getString(12));

					usersList.add(userDetails);
				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usersList;

	}

	public static Boolean changeCustomerStatus(ArrayList<String> items) { // items = customerID, new status
		String customerID = items.get(0);
		String newStatus = items.get(1);
		String sqlQuery = "UPDATE test.users SET status = ? WHERE ID = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, newStatus);
				ps.setString(2, customerID);
				ps.executeUpdate();
				System.out.println("after put things");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static Boolean changeOrderStatus(ArrayList<String> items) { // items = customerID, new status
		String customerID = items.get(0);
		String OrderNumber = items.get(1);
		String newStatus = items.get(2);
		String sqlQuery = "UPDATE test.order SET status = ? WHERE customerID = ? AND OrderNumber = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, newStatus);
				ps.setString(2, customerID);
				ps.setString(3, OrderNumber);
				ps.executeUpdate();
				System.out.println("after put things");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static Boolean addUserToDB(User user) {
		boolean flag = false;
		boolean flag1 = false;
		user.setW4C(user.getiD() + user.getiD());

		String sqlQuery = "INSERT INTO test.w4c values (?,?,?,?);"; /// test.

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);

				pst.setString(1, user.getW4C()); // ******
				pst.setString(2, "");
				pst.setString(3, "");
				pst.setString(4, "");

				pst.executeUpdate();

				pst.close();
				flag = true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();

		}

		String sqlQuery1 = "INSERT INTO test.users values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"; /// test.

		PreparedStatement pst1 = null;
		try {
			if (DBConnector.myConn != null) {
				pst1 = DBConnector.myConn.prepareStatement(sqlQuery1);

				pst1.setString(1, user.getiD());

				pst1.setString(2, user.getW4C());
				pst1.setString(3, user.getUserName());
				pst1.setString(4, user.getPassword());
				pst1.setString(5, user.getFirstName());
				pst1.setString(6, user.getLastName());
				pst1.setString(7, user.getEmail());
				pst1.setString(8, user.getAuthorization());
				pst1.setString(9, user.getAccountType());
				pst1.setString(10, user.getPhone());
				pst1.setString(11, "0");
				pst1.setString(12, "1");
				pst1.setString(13, user.getEmployeeName());
				pst1.setString(14, user.getCompanyCode());
				pst1.setString(15, "");

				pst1.executeUpdate();

				System.out.println("DB adding user");
				// DBConnector.myConn.close();
				pst.close();
				flag1 = true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();

		}
		if (flag && flag1)
			return true;
		return false;
	}

}
