package client;

import java.io.BufferedInputStream;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import logic.Delivery;
import logic.Menu;
import logic.Message;
import logic.MessageType;
import logic.Order;
import logic.Restaurant;
import logic.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class OrderController {

	public static ArrayList<Order> ordersList = new ArrayList<Order>(); // meals that ordered
	public static ArrayList<Delivery> deliveryList = new ArrayList<Delivery>(); // delivery details for specific meal

	public static ArrayList<Restaurant> restaurants;

	public static ArrayList<Order> orders;
	public static ArrayList<Order> ordersByID;
	public static ArrayList<Order> AllOrders;

	public static ArrayList<Delivery> delivery;

	public static ArrayList<String> mealslist;
	public static HashMap<String, Integer> mealsQuantity = new HashMap<>();

	public static ArrayList<String> orderSummaryList;

	// Choose restaurant
	public static String selectedRestaurant;

	// Menu
	public static String selectedCategory;
	public static String selectedMeal;
	public static String selectedSize;
	public static String selectedLvlOfCook;
	public static String selectedRestrictions;
	public static String selectedPrice;
	public static String selectedStatus;
	public static int totalPrice = 0;
	public static int mealNum = 0;

	// Delivery
	public static String selectedType; // TAKE-AWAY OR DELIVERY
	public static String selectedTypeOfPayment; // PRIVATE OR BUISNESS
	public static String selecteDeliveryType; // REGULAR OR SHARED OR ROBOT
	public static String selectedTime;
	public static String selectedDate;
	public static String selectedCustomerName;
	public static String selectedCustomerPhone;
	public static String selectedAddress;
	public static String selectedEmployeeName;
	public static String selectedEmployeeCode;
//  haser 3

	public static int orderNumber = 1; // start from 1

	public static int deliveryNum = 1; // start from 1

	public static LocalDate currentDate;
	public static String PickTime;

	public static boolean addOrderFlag;
	public static boolean addDeliveryDetailsFlag;
	public static boolean addMealToMenuFlag;
	public static boolean UpdateMealsPriceInMenuFlag;
	public static boolean DeleteMealFromMenuFlag;

	public static ArrayList<Restaurant> getAllRestaurants(String messageData) {
		Message msg = new Message(MessageType.getAllRestaurants, restaurants);
		BMClientUI.accept(msg);
		return restaurants;
	}

	public static ArrayList<String> getMealsInRestaurant(ArrayList<String> getMealsInRestaurantItems) {

		Message msg = new Message(MessageType.getMealsInRestaurant, getMealsInRestaurantItems);
		BMClientUI.accept(msg);
		return mealslist;
	}

	public static String getMealsInRestaurantPrice(ArrayList<String> getMealPriceItems) {
		Message msg = new Message(MessageType.getMealPriceItems, getMealPriceItems);
		BMClientUI.accept(msg);
		return selectedPrice;
	}

	public static boolean addOrder(Order order) {
		Message msg = new Message(MessageType.addOrder, order);
		BMClientUI.accept(msg);
		return addOrderFlag;

	}

	public static boolean addDeliveryDetails(Delivery delivery) {
		Message msg = new Message(MessageType.addDeliveryDetails, delivery);
		BMClientUI.accept(msg);
		return addDeliveryDetailsFlag;
	}

	public static void SetFinalPriceByOrder(Order order) {
		Message msg = new Message(MessageType.SetFinalPriceByOrder, order);
		BMClientUI.accept(msg);

	}

	public static ArrayList<Order> getOrderDetails(ArrayList<String> Items) {
		Message msg = new Message(MessageType.getOrdersDetails, Items);
		BMClientUI.accept(msg);
		return orders;
	}

	public static ArrayList<Order> getOrderDetailsByID(String userID) { // ***
		Message msg = new Message(MessageType.getOrderDetailsByID, userID);
		BMClientUI.accept(msg);
		return ordersByID;
	}

	public static ArrayList<Order> getAllOrders() {
		Message msg = new Message(MessageType.getAllOrders, null);
		BMClientUI.accept(msg);
		return AllOrders;
	}

	public static ArrayList<Delivery> getDeliveryDetails(String userID) {
		Message msg = new Message(MessageType.getDeliveryDetails, userID);
		BMClientUI.accept(msg);
		return delivery;
	}

	public static boolean addMealToMenu(ArrayList<String> items) {
		Message msg = new Message(MessageType.addMealToMenu, items);
		BMClientUI.accept(msg);
		return addMealToMenuFlag;

	}

	public static boolean UpdateMealsPriceInMenu(ArrayList<String> items) {
		Message msg = new Message(MessageType.UpdateMealsPriceInMenu, items);
		BMClientUI.accept(msg);
		return UpdateMealsPriceInMenuFlag;

	}

	public static boolean DeleteMealFromMenu(ArrayList<String> items) {
		Message msg = new Message(MessageType.DeleteMealFromMenu, items);
		BMClientUI.accept(msg);
		return DeleteMealFromMenuFlag;
	}

}
