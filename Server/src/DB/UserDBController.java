package DB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.User;

public class UserDBController {

	/**
	 * the array saves the users that currently connected
	 */
	public static ArrayList<String> usersConnected = new ArrayList<String>();

	// need to add func for delete users that had been logout!! ****

	static ResultSet rs;

	/**
	 * get details from the data base, and check if the user logged in successfully
	 * 
	 * @param user
	 * @return the condition of the user after he tried to login
	 */
	public static String tryToConnect(User user) {
		String username = user.getUserName();
		String password = user.getPassword();

		String sqlQuery = "SELECT UserName,Password,Authorization FROM users";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);

				boolean checkFieldsNotEmpty = checkFieldsNotEmpty(username, password);
				if (!checkFieldsNotEmpty) {
					rs.close();
					return "You must fill all the fields";
				}

				boolean checkIfUserExist = checkIfUserExist(username);
				if (!checkIfUserExist) {
					rs.close();
					return "The user is not exist";
				}

				boolean checkValidInfo = checkValidInfo(username, password);
				if (!checkValidInfo) {
					rs.close();
					return "The password is incorrect";
				}

//				boolean userNotConnected = userNotConnected(username);
//				if(!userNotConnected) { rs.close(); return "The user is already connected";}

				addUserToUsersConnectedArrayList(username);

				String checkAuthorization = checkAuthorization(username);
				return checkAuthorization;

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "ERROR-LOGIN";
	}

	/**
	 * check if all the fields are filled
	 * 
	 * @param username is the name of the user
	 * @param password of the user
	 * @return true in case that all the fields are not empty otherwise false
	 */
	private static boolean checkFieldsNotEmpty(String username, String password) {
		if (username.isEmpty() || password.isEmpty())
			return false;
		return true;
	}

	/**
	 * add the user to the arrayList usersconnected
	 * 
	 * @param username name of the user account
	 */
	private static void addUserToUsersConnectedArrayList(String username) {
		usersConnected.add(username);
	}

	/**
	 * check the existence of the user
	 * 
	 * @param username is the name of the user
	 * @return true if exist in data base otherwise false
	 */
	public static boolean checkIfUserExist(String username) {
		try {
			rs.beforeFirst();
			while (rs.next()) {
				// search for the user in the query
				if (username.equals(rs.getString(1)))
					return true;
			}
			// rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * check if username and password match to the username and password in the
	 * database
	 * 
	 * @param userInfo - gets the username and password
	 * @return
	 */
	public static boolean checkValidInfo(String username, String password) {
		try {
			rs.beforeFirst();
			while (rs.next()) {
				// search for the user in the rs
				if (username.equals(rs.getString(1)) && password.equals(rs.getString(2)))
					return true;
			}
			// rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * check if the user is already connected
	 * 
	 * @param username name of the user account
	 * @return true if user is not connected otherwise false
	 */
	private static boolean userNotConnected(String username) { // olay ipol biglal marachNULL

		for (String name : usersConnected) {
			if (name.equals(username))
				return false;
		}
		return true;
	}

	/**
	 * gets the Authorization of the account according to user name
	 * 
	 * @param username - name of the user account
	 * @return
	 */
	public static String checkAuthorization(String username) {
		try {
			rs.beforeFirst();
			while (rs.next()) {
				// search for the user in the query
				if (username.equals(rs.getString(1)))
					return rs.getString(3); // return Authorization
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "ERROR-Authorization";
	}

	public static String getAccountType(String username) {
		String sqlQuesry = "SELECT * FROM users WHERE UserName = \"" + username + "\";";
		Statement st;
		try {
			st = DBConnector.myConn.createStatement();
			ResultSet rs = st.executeQuery(sqlQuesry);
			rs.next();
			return rs.getString(8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static String getName(String username) {
		String sqlQuesry = "SELECT * FROM users WHERE UserName = \"" + username + "\";";
		Statement st;
		try {
			st = DBConnector.myConn.createStatement();
			ResultSet rs = st.executeQuery(sqlQuesry);
			rs.next();
			return rs.getString(4) + " " + rs.getString(5);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static String getUserID(User user) { // CHANGE TO GET ALL DETAILS
		String username = user.getUserName();
		String password = user.getPassword();
		String ID = "";

		String sqlQuery = "SELECT ID FROM users WHERE UserName = '" + username + "' AND Password ='" + password + "';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {
					ID = rs.getString("ID");

				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ID;
	}

	public static ArrayList<String> getUserDetails(User user) {
		String username = user.getUserName();
		String password = user.getPassword();
		ArrayList<String> items = new ArrayList<>();
		;

		String sqlQuery = "SELECT * FROM users WHERE UserName = '" + username + "' AND Password ='" + password + "';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {
					items.add(rs.getString("ID"));
					items.add(rs.getString("W4C_card"));
					items.add(rs.getString("FirstName"));
					items.add(rs.getString("LastName"));
					items.add(rs.getString("Email"));
					items.add(rs.getString("Authorization"));
					items.add(rs.getString("AccountType"));
					items.add(rs.getString("Phone"));
					items.add(rs.getString("status"));

				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	/*
	 * Nikita DB query to get all accounts with AccountType = Requsted
	 */
	public static ArrayList<User> getRequestsForBuisnessAccount(String hrID) {
		ArrayList<User> items = new ArrayList<>();

		String sqlQuery = "SELECT CompanyCode FROM users WHERE ID = '" + hrID + "';";

		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				rs.next();
				String hrCompanyCode = rs.getString("CompanyCode");
				if (hrCompanyCode != null) {
					sqlQuery = "SELECT * FROM users WHERE AccountType = 'Requested' AND CompanyCode = '" + hrCompanyCode
							+ "';";
					rs = st.executeQuery(sqlQuery);
					while (rs.next()) {
						User user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
								rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
								rs.getString(10), rs.getString(12), rs.getString(13), rs.getString(14),
								rs.getString(15));
						items.add(user);
					}
					rs.close();
				}

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return items;
	}

	/*
	 * Nikita DB query to update AccountType from requested to Business
	 */
	public static boolean confirmBuisnesAccount(ArrayList<String> items) { // HR

		String ID = items.get(0);
		String Status = items.get(1);
		String newStatus = null;
		String AccountType = null;
		String sqlQuery = "UPDATE test.users SET AccountType = ?, status = ? WHERE ID = ?;";

		switch (Status) {
		case "Approve":
			System.out.println("Status is: " + Status);
			AccountType = "Business";
			newStatus = "Active";
			break;
		case "Decline":
			System.out.println("Status is: " + Status);
			AccountType = "Decline";
			newStatus = "Frozeen";
			break;
		}
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, AccountType);
				ps.setString(2, newStatus);
				ps.setString(3, ID);
				ps.executeUpdate();

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public static boolean insertNewImployer(String employerCode, String employerName, String hrLogIn) {
		String sqlQuery = "SELECT * FROM employer WHERE CompanyCode = '" + employerCode + "';";

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);

				if (!rs.next()) {
					sqlQuery = "INSERT INTO employer (CompanyCode, EmployerName) VALUES ('" + employerCode + "', '"
							+ employerName + "');";
					pst = DBConnector.myConn.prepareStatement(sqlQuery);
					pst.executeUpdate();
					pst.close();

					sqlQuery = "UPDATE users SET EmployeeName = '" + employerName + "', CompanyCode = '" + employerCode
							+ "' WHERE ID = '" + hrLogIn + "' ;";
					pst = DBConnector.myConn.prepareStatement(sqlQuery);
					pst.executeUpdate();
					pst.close();

					return true;
				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * remove user from current users connected
	 * 
	 * @param username username of the user
	 */
	public static void removeUserFromLoginArr(String username) {
		usersConnected.remove(username);
	}

}
