// This file contains material supporting section 3.7 of the textbook:
// "Object Oriented Software Engineering" and is issued under the open-source
// license found at www.lloseng.com 

package client;

import logic.Delivery;
import logic.Message;

import logic.MessageType;
import logic.Order;
import logic.Restaurant;
import logic.User;
import ocsf.*;

import java.io.*;
import java.util.ArrayList;

import javafx.collections.ObservableList;

/**
 * This class overrides some of the methods defined in the abstract superclass
 * in order to give more functionality to the client.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;
 * @author Fran&ccedil;ois B&eacute;langer
 * @version July 2000
 */
public class MainClient extends AbstractClient { // ChatClient in Ex
	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */

	BMClientUI clientUI;
	public static String ServerIP;
	public static boolean awaitResponse = false;

	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public MainClient(String host, int port, BMClientUI clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.clientUI = clientUI;
		// openConnection();
	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	@SuppressWarnings("unchecked")
	public void handleMessageFromServer(Object msg) {
		System.out.println("--> handleMessageFrom Server");
		Message message = (Message) msg;
		awaitResponse = false;
		System.out.println(message.getMessageType());
		switch (message.getMessageType()) {
		case IpAddress:
			UserController.ipAddress = (String) message.getMessageData(); // ServerIP
			ServerIP = UserController.ipAddress;
			break;

		case logIn:
			UserController.logInStatus = (String) message.getMessageData();

			break;

		case logOut:
//			UserController.logOutStatus = (String) message.getMessageData();
//			System.out.println("UserController.logOutStatus " + UserController.logOutStatus);
			break;

		/// ********* get cases *********

		case getUserDetails:

			ArrayList<String> items = (ArrayList<String>) message.getMessageData();
			UserController.UserID = items.get(0);
			UserController.W4C = items.get(1);
			UserController.firstName = items.get(2);
			UserController.lastName = items.get(3);
			UserController.email = items.get(4);
			UserController.Authorization = items.get(5);
			UserController.AccountType = items.get(6);
			UserController.phone = items.get(7);
			UserController.status = items.get(8);
			break;

		case getAllRestaurants:
			OrderController.restaurants = (ArrayList<Restaurant>) message.getMessageData();
			break;

		case getMealsInRestaurant:
			OrderController.mealslist = (ArrayList<String>) message.getMessageData();

			break;

		case getMealPriceItems:
			OrderController.selectedPrice = (String) message.getMessageData();
			break;

		case getOrdersDetails:
//			System.out.println(" Main client getOrderDetails " + (ArrayList<Order>) message.getMessageData());
			OrderController.orders = (ArrayList<Order>) message.getMessageData();
//			System.out.println(" Main client OrderController.orders " + OrderController.orders);
			break;

		case getOrderDetailsByID:
//			System.out.println(" Main client getOrderDetailsByID " + (ArrayList<Order>) message.getMessageData());
			OrderController.ordersByID = (ArrayList<Order>) message.getMessageData();
			break;

		case getAllOrders:
			OrderController.AllOrders = (ArrayList<Order>) message.getMessageData();
			break;

		case getDeliveryDetails:
			System.out.println(" Main client delivery " + (ArrayList<Delivery>) message.getMessageData());
			OrderController.delivery = (ArrayList<Delivery>) message.getMessageData();

			break;

		case getCustomerDetailsByAuthorization:
//			System.out.println(" Main client getUsersDetails " + (ArrayList<User>) message.getMessageData());
			UserController.users = (ArrayList<User>) message.getMessageData();
//			System.out.println(" Main client UserController.users " + UserController.users);
			break;

		case getAllRequstionForBuisnesAccount:
			System.out.println("Main client getRequstedAccounts " + (ArrayList<User>) message.getMessageData());
			UserController.requests = (ArrayList<User>) message.getMessageData();
			break;

		/// ********* add cases *********

		case addOrder:
			OrderController.addOrderFlag = (boolean) message.getMessageData();
			if (OrderController.addOrderFlag)
				System.out.println("addOrder Done");
			break;

		case addDeliveryDetails:
			OrderController.addDeliveryDetailsFlag = (boolean) message.getMessageData();
			if (OrderController.addDeliveryDetailsFlag)
				System.out.println("addDeliveryDetails Done");
			break;

		case addMealToMenu:
			OrderController.addMealToMenuFlag = (boolean) message.getMessageData();
			if (OrderController.addMealToMenuFlag)
				System.out.println("addMealToMenu Done");
			break;

		case addUserToDB:
			UserController.addUserToDBFlag = (boolean) message.getMessageData();
			if (UserController.addUserToDBFlag)
				System.out.println("addUserToDB Done");
			break;

		case registerNewEmployer:
			UserController.registerNewEmployerFlag = (boolean) message.getMessageData();
			if (UserController.registerNewEmployerFlag)
				System.out.println("registerNewEmployer Done");
			break;

		case SetFinalPriceByOrder: // *** NO USE
			if ((boolean) message.getMessageData())
				System.out.println("SetFinalPriceByOrder Done");

			break;

		/// ********* change cases *********

		case changeCustomerStatus:
			if ((boolean) message.getMessageData())
				UserController.changeCustomerStatusFlag = true;
			System.out.println("changeCustomerStatus Done");
			break;

		case changeOrderStatus:
			if ((boolean) message.getMessageData())
				UserController.changeOrderStatusFlag = true;
			System.out.println("changeOrderStatusFlag Done");
			break;

		case confirmBusinessAccount:
			if ((boolean) message.getMessageData())
				UserController.confirmBusinessAccountFlag = true;
			System.out.println("confirmBusinessAccount Done");
			break;

		case UpdateMealsPriceInMenu:
			if ((boolean) message.getMessageData())
				OrderController.UpdateMealsPriceInMenuFlag = true;
			System.out.println("UpdateMealsPriceInMenu Done");
			break;

		case DeleteMealFromMenu:
			if ((boolean) message.getMessageData())
				OrderController.DeleteMealFromMenuFlag = true;
			System.out.println("DeleteMealFromMenu Done");
			break;

		default:
			BMClientUI.display("Could not read message from server");
		}

	}

	public static String getServerIP() {
		return ServerIP;
	}

	// System.out.println(" " + );

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message The message from the UI.
	 */

	public void handleMessageFromClientUI(Object message) // changed to object
	{
		try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			sendToServer(message);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			BMClientUI.display("Could not send message to server: Terminating client." + e);
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
			System.out.println("Error in closing connection");
		}
		System.exit(0);
	}
}
// End of ChatClient class
