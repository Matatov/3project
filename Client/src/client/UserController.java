package client;

import java.io.BufferedInputStream;

import java.sql.Blob;
import java.util.ArrayList;

import logic.Employer;
import logic.Message;
import logic.MessageType;
import logic.Order;
import logic.User;
import javafx.stage.Stage;

public class UserController {

	public static ArrayList<User> users;
	public static ArrayList<User> requests;
	
	public static ArrayList<String> IDAndAutorization;
	

	public static String UserID;
	public static String W4C;
	public static String userName;
	public static String password;
	public static String firstName;
	public static String lastName;
	public static String email;
	public static String Authorization;
	public static String AccountType;
	public static String phone;
	public static String status;
	public static String employeeName;
	public static String companyCode;

	public static String ipAddress;
	public static String logOutStatus;

	public static boolean changeCustomerStatusFlag;
	public static boolean confirmBusinessAccountFlag;
	public static boolean changeOrderStatusFlag;
	
	public static boolean addUserToDBFlag;
	public static boolean registerNewEmployerFlag;

	/**
	 * true if user exist otherwise false
	 */
	public static boolean userExist;
	/**
	 * true if user connected otherwise false
	 */
	public static boolean flagUserAlreadyConnected;

	/**
	 * true if username and password match otherwise false
	 */
	public static boolean validInfo;

	/**
	 * Permission - the kind of the user {BMManager,Employee,Customer}
	 */
	public static String type;

	/**
	 * the condition of the user after he tried to login
	 */
	public static String logInStatus;

	/**
	 * current stage of the user
	 */
	public static Stage currentStage;

	/**
	 * send to the server the details about the login
	 * 
	 * @param username of the user
	 * @param password of the user
	 * @return the condition of the user - success-teacher,principle,student,or why
	 *         login failed
	 */

	public static String logIn(String username, String password) {
		User currentLogIn = new User(username, password);
		Message msg = new Message(MessageType.logIn, currentLogIn);
		BMClientUI.accept(msg);
		return logInStatus;
	}

	/**
	 * send to the server the IP Address
	 *
	 */

	public static String IpAddress(String ipAddress) {
		Message msg = new Message(MessageType.IpAddress, ipAddress);
		BMClientUI.accept(msg);
		return ipAddress;
	}

	public static void getUserDetails(String username, String password) {
		User currentLogIn = new User(username, password);
		Message msg = new Message(MessageType.getUserDetails, currentLogIn);
		BMClientUI.accept(msg);

	}

	public static ArrayList<User> getCustomerDetailsByAuthorization(String CustomerString) {
		Message msg = new Message(MessageType.getCustomerDetailsByAuthorization, CustomerString);
		BMClientUI.accept(msg);
		return users;
	}

	public static boolean changeCustomerStatus(ArrayList<String> getUserItems) {
		Message msg = new Message(MessageType.changeCustomerStatus, getUserItems);
		BMClientUI.accept(msg);
		return changeCustomerStatusFlag;
	}

	/*
	 * Nikita this method is called from HRConfirmController. MessageType =
	 * getAllRequestionForBusinesAccounts will sent to Server
	 */
	public static ArrayList<User> getRequstionsForBuisnesAccount() {
		
		Message msg = new Message(MessageType.getAllRequstionForBuisnesAccount, UserID);
		BMClientUI.accept(msg);
		return requests;
	}

	/*
	 * Nikita messageData = UserID is sent to DB to update from Requested to Busines
	 * coount type
	 */
	public static boolean confirmBuisnesAccount(ArrayList<String> userItems) {
		Message msg = new Message(MessageType.confirmBusinessAccount, userItems);
		BMClientUI.accept(msg);
		return confirmBusinessAccountFlag;
	}

	public static boolean changeOrderStatus(ArrayList<String> getOrderDetailsItems) {
		Message msg = new Message(MessageType.changeOrderStatus, getOrderDetailsItems);
		BMClientUI.accept(msg);
		return changeOrderStatusFlag;
	}

	public static boolean addUserToDB(User user) {
		Message msg = new Message(MessageType.addUserToDB, user);
		BMClientUI.accept(msg);
		return addUserToDBFlag;
	}

	public static boolean registerNewEmployer(String companyCode, String employerName) {
		//Employer newEmpl = new Employer(employerCode, employerName);
		String[] args = {companyCode, employerName, UserID};
		Message msg = new Message(MessageType.registerNewEmployer, args);
		BMClientUI.accept(msg);
		return registerNewEmployerFlag;
	}

}
