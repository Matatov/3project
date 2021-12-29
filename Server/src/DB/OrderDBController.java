package DB;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.Delivery;
import logic.Order;
import logic.Restaurant;

public class OrderDBController {
	static ResultSet rs;

	public static ArrayList<Restaurant> getRestaurantDetails() {
		ArrayList<Restaurant> restaurantsList = new ArrayList<>();
		String sqlQuery = "SELECT RestaurantName,RestaurantAddress,BMMenagerID FROM restaurant";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {
					Restaurant restaurantDetails = new Restaurant(rs.getString(1), rs.getString(2), rs.getString(3));

					restaurantsList.add(restaurantDetails);

					System.out.println("restaurantsList: " + restaurantsList);

				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return restaurantsList;

	}

	public static ArrayList<String> getMealsInRestaurant(ArrayList<String> items) {

		ArrayList<String> list = new ArrayList<String>();
		String RestaurantName = items.get(0);
		String Category = items.get(1);

		String sqlQuery = "SELECT DISTINCT Meal FROM mealinrestaurant WHERE Restaurant = '" + RestaurantName
				+ "' AND Category ='" + Category + "';";
		String MealName = "";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {
					MealName = rs.getString("Meal");
					list.add(MealName);
				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;

	}

	public static String getMealPrice(ArrayList<String> items) { // ****
		String RestaurantName = items.get(0);
		String MealName = items.get(1);
		String MealSize = items.get(2);
		String Key = RestaurantName + "_" + MealName + "_" + MealSize;
		System.out.println("Key " + Key);
		String sqlQuery = "SELECT MealPrice FROM menu WHERE MealCode = '" + Key + "';";
		String MealPrice = "";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(sqlQuery);
				while (rs.next()) {
					MealPrice = rs.getString("MealPrice");
					System.out.println("MealPrice" + MealPrice);
				}
				rs.close();

			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return MealPrice;

	}

	public static ArrayList<Order> getOrderDetails(ArrayList<String> items) { // items = UserID,OrderNum
		ArrayList<Order> orderList = new ArrayList<>();

		String UserID = items.get(0);
		String OrderNum = items.get(1);

		String selectQuery = "SELECT * FROM test.order WHERE customerID = '" + UserID + "'AND OrderNumber ='" + OrderNum
				+ "';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					Order orderDetails = new Order(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getString(10), rs.getString(11), rs.getString(12));

					orderList.add(orderDetails);
					System.out.println("orderDetails: " + orderDetails);

				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderList;

	}

	public static ArrayList<Order> getOrderDetailsByID(String UserID) {
		ArrayList<Order> orderList = new ArrayList<>();

		String selectQuery = "SELECT * FROM test.order WHERE customerID = '" + UserID + "';";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					Order orderDetails = new Order(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getString(10), rs.getString(11), rs.getString(12));

					orderList.add(orderDetails);
//					System.out.println(orderDetails);

				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return orderList;

	}

	public static ArrayList<Order> getAllOrders() {
		ArrayList<Order> ordersList = new ArrayList<>();

		String selectQuery = "SELECT * FROM test.order;";
		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					Order orderDetails = new Order(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9),
							rs.getString(10), rs.getString(11), rs.getString(12));

					ordersList.add(orderDetails);
//					System.out.println(orderDetails);

				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ordersList;

	}

	public static ArrayList<Delivery> getDeliveryDetails(String UserID) {
		ArrayList<Delivery> deliveryList = new ArrayList<>();

		String selectQuery = "SELECT * FROM test.delivery WHERE CustomerID = '" + UserID + "';";

		try {
			if (DBConnector.myConn != null) {
				Statement st = DBConnector.myConn.createStatement();
				rs = st.executeQuery(selectQuery);
				while (rs.next()) {
					Delivery deliveryDetails = new Delivery(rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
							rs.getString(14), rs.getString(15));

					deliveryList.add(deliveryDetails);
					System.out.println(deliveryDetails);

				}
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return deliveryList;

	}

	// ***** ADD SQL *****

	public static boolean addOrder(Order order) {

		String sqlQuery = "INSERT INTO test.order values (?,?,?,?,?,?,?,?,?,?,?,?);"; /// test.

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);

				pst.setString(1, order.getCustomerID() + "_" + order.getOrderNumber() + "_" + order.getMealNum()); // ******
				pst.setString(2, order.getOrderNumber());
				pst.setString(3, order.getCustomerID());
				pst.setString(4, order.getRestaurantName() + "_" + order.getMeal());
				pst.setString(5, order.getCategory());
				pst.setString(6, order.getSize());
				pst.setString(7, order.getLvlOfCook());
				pst.setString(8, order.getRestrictions());
				pst.setString(9, order.getStatus());
				pst.setString(10, order.getMealPrice());
				pst.setString(11, order.getRestaurantName());
				pst.setString(12, " "); // Add when delivery done
				pst.executeUpdate();

				pst.close();
				return true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();

		}

		return false;
	}

	public static boolean addDeliveryDetails(Delivery delivery) {
		boolean flag = false;
		boolean flag1 = false;
		String sqlQuery = "INSERT INTO test.delivery values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);"; /// test.

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);

				pst.setString(1,
						delivery.getCustomerID() + "_" + delivery.getDeliveryNum() + "_" + delivery.getOrderNum());

				pst.setString(2, delivery.getCustomerID());
				pst.setString(3, delivery.getOrderNumber());
				pst.setString(4, delivery.getTypeOfPayment());
				pst.setString(5, delivery.getTypeOfDelivery());
				pst.setString(6, delivery.getTime());
				pst.setString(7, delivery.getDate());
				pst.setString(8, delivery.getCustomerName());
				pst.setString(9, delivery.getPhoneNum());
				pst.setString(10, delivery.getAddress());
				pst.setString(11, delivery.getEmployeeName());
				pst.setString(12, delivery.getEmployeeCode());
				pst.setString(13, delivery.getShipmentTime());
				pst.setString(14, delivery.getPrice());
				pst.setString(15, delivery.getBasePrice());

				pst.executeUpdate();

				pst.close();
				flag = true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();

		}

		String sqlQuery1 = "UPDATE test.order SET DateOfOrder = ? WHERE customerID = ? AND OrderNumber = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery1);
				ps.setString(1, delivery.getDate());
				ps.setString(2, delivery.getCustomerID());
				ps.setString(3, delivery.getOrderNumber());
				ps.executeUpdate();

				flag1 = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (flag && flag1)
			return true;
		return false;
	}

	public static boolean addMealToMenu(ArrayList<String> items) {
		String restaurantName = items.get(0);
		String Category = items.get(1);
		String mealName = items.get(2);
		String Size = items.get(3);
		String PriceBySize = items.get(4);

		boolean flag = false;
		boolean flag1 = false;
		String sqlQuery = "INSERT INTO test.menu values (?,?,?,?,?);"; /// test.

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);

				pst.setString(1, restaurantName + "_" + mealName + "_" + Size); // ******
				pst.setString(2, mealName);
				pst.setString(3, Category);
				pst.setString(4, PriceBySize);
				pst.setString(5, Size);

				pst.executeUpdate();

				// DBConnector.myConn.close();
				pst.close();
				flag = true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();

		}

		String sqlQuery1 = "INSERT INTO test.mealinrestaurant values (?,?,?,?);"; /// test.

		PreparedStatement pst1 = null;
		try {
			if (DBConnector.myConn != null) {
				pst1 = DBConnector.myConn.prepareStatement(sqlQuery1);

				pst1.setString(1, restaurantName + "_" + mealName + "_" + Size); // ******
				pst1.setString(2, restaurantName);
				pst1.setString(3, mealName);
				pst1.setString(4, Category);

				pst1.executeUpdate();

				pst1.close();
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

	public static Boolean UpdateMealsPriceInMenu(ArrayList<String> items) {
		String restaurantName = items.get(0);
		String mealName = items.get(1);
		String size = items.get(2);
		String newPrice = items.get(3);
		String Key = restaurantName + "_" + mealName + "_" + size;
		String sqlQuery = "UPDATE test.menu SET MealPrice = ? WHERE MealCode = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, newPrice);
				ps.setString(2, Key);

				ps.executeUpdate();

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean DeleteMealFromMenu(ArrayList<String> items) {
		String restaurantName = items.get(0);
		String mealName = items.get(1);
		String size = items.get(2);
		boolean flag = false;
		boolean flag1 = false;

		String Key = restaurantName + "_" + mealName + "_" + size;
		String sqlQuery = "DELETE FROM test.menu WHERE MealCode = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps = DBConnector.myConn.prepareStatement(sqlQuery);
				ps.setString(1, Key);

				ps.executeUpdate();
				flag = true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sqlQuery1 = "DELETE FROM test.mealinrestaurant WHERE MenuCode = ?;";
		try {
			if (DBConnector.myConn != null) {
				PreparedStatement ps1 = DBConnector.myConn.prepareStatement(sqlQuery1);
				ps1.setString(1, Key);

				ps1.executeUpdate();
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

	/// NOT USED
	public static Boolean SetFinalPriceByOrder(Order order) {
		String sqlQuery = "INSERT INTO test.finalSummary values (?,?);";

		PreparedStatement pst = null;
		try {
			if (DBConnector.myConn != null) {
				pst = DBConnector.myConn.prepareStatement(sqlQuery);
				pst.setString(1, order.getCustomerID() + "_" + order.getOrderNumber());
				pst.setString(2, String.valueOf(order.getFinalPrice()));

				pst.executeUpdate();

				// DBConnector.myConn.close();
				pst.close();
				return true;
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return false;
	}

}
