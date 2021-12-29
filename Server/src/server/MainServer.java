package server;

// This file contains material supporting section 3.7 of the textbook:

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;

import DB.BMMenagerDBController;
import DB.DBConnector;
import DB.UserDBController;
import logic.Client;
import logic.Delivery;
import logic.Employer;
import logic.Message;
import logic.MessageType;
import logic.Order;
import logic.Restaurant;
import logic.User;
import ocsf.AbstractServer;
import ocsf.ConnectionToClient;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Paul Holden
 * @version July 2000
 */
public class MainServer extends AbstractServer {
	// Class variables *************************************************

	/**
	 * The default port to listen on.
	 */
	public static String ServerIP;
	public static String clientIp;
	public static String hostName;
	public static String clientConnected = "Not Connected";

	public static DBConnector dbConnector;
	Message msgFromServer = null;

	ArrayList<String> mealsInRestaurantlist = new ArrayList<String>();

	public static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();

	public static ArrayList<Order> orders = new ArrayList<Order>();
	public static ArrayList<Order> ordersByID = new ArrayList<Order>();
	public static ArrayList<Order> AllOrders = new ArrayList<Order>();

	public static ArrayList<Delivery> delivery = new ArrayList<Delivery>();

	public static ArrayList<User> users = new ArrayList<User>();
	public static ArrayList<User> requstedUsers = new ArrayList<User>(); // Nikita

	public static Client[] clientItems = new Client[100];
	private int clientNum = 0;
	// Constructors ****************************************************

	/**
	 * Constructs an instance of the echo server.
	 *
	 * @param port The port number to connect on.
	 */
	public MainServer(int port) {
		super(port);
		dbConnector = new DBConnector(); /// ***
		Socket socket = new Socket();
		try {
			socket.connect(new InetSocketAddress("google.com", 80));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		ServerIP = socket.getLocalAddress().toString();
		ServerIP = ServerIP.substring(1);
	}

	// Instance methods ************************************************

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg    The message received from the client.
	 * @param client The connection from which the message originated.
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {
		System.out.println("--> handleMessageFrom Client");
		Message message = (Message) msg;
		System.out.println(message.getMessageType());
		switch (message.getMessageType()) {
		case IpAddress:
			clientConnected(client);
			// NEED to add if remove client or not connect status
			clientNum++;
			msgFromServer = new Message(MessageType.IpAddress, ServerIP); /// Send ServerIP not clientIp ServerIP
			break;

		case logIn:
			String logInStatus = DB.UserDBController.tryToConnect((User) message.getMessageData());
			msgFromServer = new Message(MessageType.logIn, logInStatus);
			break;

		case logOut: // ****
			UserDBController.removeUserFromLoginArr((String) message.getMessageData());
			break;

		/// ********* get cases *********

		case getUserDetails:
			ArrayList<String> items = DB.UserDBController.getUserDetails((User) message.getMessageData());
			msgFromServer = new Message(MessageType.getUserDetails, items);
			break;

		case getAllRestaurants:
			restaurants = DB.OrderDBController.getRestaurantDetails();
			msgFromServer = new Message(MessageType.getAllRestaurants, restaurants);
			break;

		case getMealsInRestaurant:
			mealsInRestaurantlist = DB.OrderDBController
					.getMealsInRestaurant((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.getMealsInRestaurant, mealsInRestaurantlist);
			break;

		case getMealPriceItems:
			String mealPrice = DB.OrderDBController.getMealPrice((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.getMealPriceItems, mealPrice);
			break;

		case getOrdersDetails:
//			System.out.println("Main Server getOrderDetails: " + (ArrayList<String>) message.getMessageData());
			orders = DB.OrderDBController.getOrderDetails((ArrayList<String>) message.getMessageData());
//			System.out.println("Main Server orders " + orders);
			msgFromServer = new Message(MessageType.getOrdersDetails, orders);
			break;

		case getOrderDetailsByID:
			ordersByID = DB.OrderDBController.getOrderDetailsByID((String) message.getMessageData());
			msgFromServer = new Message(MessageType.getOrderDetailsByID, ordersByID);
			break;

		case getAllOrders:
			AllOrders = DB.OrderDBController.getAllOrders();
			msgFromServer = new Message(MessageType.getAllOrders, AllOrders);
			break;

		case getDeliveryDetails:
			System.out.println("getDeliveryDetails " + (String) message.getMessageData());
			delivery = DB.OrderDBController.getDeliveryDetails((String) message.getMessageData());
			System.out.println("Main Server delivery " + delivery);
			msgFromServer = new Message(MessageType.getDeliveryDetails, delivery);
			break;

		case getCustomerDetailsByAuthorization:
//			System.out.println("getUsersDetails " + (String) message.getMessageData());
			users = DB.BMMenagerDBController.getCustomerDetailsByAuthorization((String) message.getMessageData());
//			System.out.println("Main Server users " + users);
			msgFromServer = new Message(MessageType.getCustomerDetailsByAuthorization, users);
			break;
			
		case getAllRequstionForBuisnesAccount:
			requstedUsers = DB.UserDBController.getRequestsForBuisnessAccount((String) message.getMessageData());
			msgFromServer = new Message(MessageType.getAllRequstionForBuisnesAccount, requstedUsers);
			break;

		/// ********* add cases *********

		case addOrder:
			System.out.println("addOrder:  " + (Order) message.getMessageData());
			Boolean addOrderFlag = DB.OrderDBController.addOrder((Order) message.getMessageData());
			System.out.println("addOrder flag is " + addOrderFlag);
			msgFromServer = new Message(MessageType.addOrder, addOrderFlag);
			break;

		case addDeliveryDetails:
			System.out.println("addDeliveryDetails:  " + (Delivery) message.getMessageData());
			Boolean addDeliveryDetailsFlag = DB.OrderDBController.addDeliveryDetails((Delivery) message.getMessageData());
			System.out.println("addDeliveryDetails flag is " + addDeliveryDetailsFlag);
			msgFromServer = new Message(MessageType.addDeliveryDetails, addDeliveryDetailsFlag);
			break;
			
		case addMealToMenu:
			System.out.println("addMealToMenu:  " + (ArrayList<String>) message.getMessageData());
			Boolean addMealToMenuFlag = DB.OrderDBController.addMealToMenu((ArrayList<String>) message.getMessageData());
			System.out.println("addMealToMenu flag is " + addMealToMenuFlag);
			msgFromServer = new Message(MessageType.addMealToMenu, addMealToMenuFlag);
			break;
			
			
		case addUserToDB:
			System.out.println("addUserToDB:  " + (User) message.getMessageData());
			Boolean addUserToDB = DB.BMMenagerDBController.addUserToDB((User) message.getMessageData());
			System.out.println("addUserToDB flag is " + addUserToDB);
			msgFromServer = new Message(MessageType.addUserToDB, addUserToDB);
			break;

		case SetFinalPriceByOrder: /// **** NO USE
			System.out.println("SetFinalPriceByOrder: " + (Order) message.getMessageData());
			Boolean flag2 = DB.OrderDBController.SetFinalPriceByOrder((Order) message.getMessageData());
			System.out.println("SetFinalPriceByOrder flag is " + flag2);
			msgFromServer = new Message(MessageType.SetFinalPriceByOrder, flag2);
			break;
			
		case registerNewEmployer:
			/*
			 * Nikita: s
			 * 
			 * 
			 */
			//Employer newEmpl = (Employer) message.getMessageData();
			String[] args = (String[]) message.getMessageData();
			Boolean registerNewEmployerFlag = DB.UserDBController.insertNewImployer(args[0], args[1], args[2]);
			
			
			//Boolean addUserToDB = DB.BMMenagerDBController.addUserToDB((User) message.getMessageData());
			msgFromServer = new Message(MessageType.registerNewEmployer, registerNewEmployerFlag);
			break;
			

		/// ********* change cases *********

		case changeCustomerStatus:

			Boolean changeCustomerStatusFlag = DB.BMMenagerDBController.changeCustomerStatus((ArrayList<String>) message.getMessageData());
			System.out.println("Main Server flag3 " + changeCustomerStatusFlag);
			msgFromServer = new Message(MessageType.changeCustomerStatus, changeCustomerStatusFlag);
			break;
			
		case changeOrderStatus:
			Boolean changeOrderStatusFlag = DB.BMMenagerDBController.changeOrderStatus((ArrayList<String>) message.getMessageData());
			System.out.println("Main Server flag4 " + changeOrderStatusFlag);
			msgFromServer = new Message(MessageType.changeOrderStatus, changeOrderStatusFlag);
			break;
			

		case confirmBusinessAccount:
			Boolean confirmBusinessAccountFlag = DB.UserDBController.confirmBuisnesAccount((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.confirmBusinessAccount, confirmBusinessAccountFlag);
			break;
			
		case UpdateMealsPriceInMenu:
			Boolean UpdateMealsPriceInMenuFlag = DB.OrderDBController.UpdateMealsPriceInMenu((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.UpdateMealsPriceInMenu, UpdateMealsPriceInMenuFlag);
			break;
			
		case DeleteMealFromMenu:
			Boolean DeleteMealFromMenuFlag = DB.OrderDBController.DeleteMealFromMenu((ArrayList<String>) message.getMessageData());
			msgFromServer = new Message(MessageType.DeleteMealFromMenu, DeleteMealFromMenuFlag);
			break;

		default:
			msgFromServer = new Message(MessageType.Error, null);
		}

		try {
			client.sendToClient(msgFromServer);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void clientConnected(ConnectionToClient client) { // Check localhost name ****
//		super.clientConnected(client);
		clientConnected = "connected";
		clientIp = client.getInetAddress().getHostAddress();
//		System.out.println(clientIp + " clientIp");
		hostName = client.getInetAddress().getHostName();
//		System.out.println(hostName + " hostName");
		System.out.println(client + " connected!");

		clientItems[clientNum] = new Client(hostName, clientIp, clientConnected); // create new

	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		super.serverStarted(); // **
		System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		System.out.println("Server has stopped listening for connections.");
	}

}
//End of EchoServer class
