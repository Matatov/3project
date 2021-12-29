package logic;

import java.io.Serializable;
import java.util.HashMap;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private String OrderNumber;
	private String customerID;
	private String Meal;
	private String Category;
	private String Size;
	private String lvlOfCook;
	private String Restrictions;
	private String status;
	private String MealPrice;
	private String primaryKey;
	private int FinalPrice;
	private int mealNum = 0;
	private String quantity;
	private String RestaurantName;
	private String DateOfOrder;

	public Order(String orderNumber, String customerID, String meal, String category, String size, String lvlOfCook,
			String restrictions, String status) {
		super();
		this.OrderNumber = orderNumber;
		this.customerID = customerID;
		this.Meal = meal;
		this.Category = category;
		this.Size = size;
		this.lvlOfCook = lvlOfCook;
		this.Restrictions = restrictions;
		this.status = status;
	}

	public Order(String primaryKey, String orderNumber, String customerID, String meal, String category, String size,
			String lvlOfCook, String restrictions, String status, String mealPrice, String RestaurantName,
			String DateOfOrder) {
		this.OrderNumber = orderNumber;
		this.customerID = customerID;
		this.Meal = meal;
		this.Category = category;
		this.Size = size;
		this.lvlOfCook = lvlOfCook;
		this.Restrictions = restrictions;
		this.status = status;
		this.MealPrice = mealPrice;
		this.primaryKey = primaryKey;
		this.RestaurantName = RestaurantName;
		this.DateOfOrder = DateOfOrder;
	}

	public String getMealNum() {
		return String.valueOf(mealNum);
	}

	public void setMealNum(int mealNum) {
		this.mealNum = mealNum;
	}

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.OrderNumber = orderNumber;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	public String getMeal() {
		return Meal;
	}

	public void setMeal(String meal) {
		this.Meal = meal;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		this.Category = category;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		this.Size = size;
	}

	public String getLvlOfCook() {
		return lvlOfCook;
	}

	public void setLvlOfCook(String lvlOfCook) {
		this.lvlOfCook = lvlOfCook;
	}

	public String getRestrictions() {
		return Restrictions;
	}

	public void setRestrictions(String restrictions) {
		this.Restrictions = restrictions;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMealPrice() {
		return MealPrice;
	}

	public void setMealPrice(String mealPrice) {
		this.MealPrice = mealPrice;
	}

	public String getFinalPrice() {
		return String.valueOf(FinalPrice);
	}

	public void setFinalPrice(int finalPrice) {
		this.FinalPrice = finalPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getRestaurantName() {
		return RestaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		RestaurantName = restaurantName;
	}

	public String getDateOfOrder() {
		return DateOfOrder;
	}

	public void setDateOfOrder(String dateOfOrder) {
		DateOfOrder = dateOfOrder;
	}

	@Override
	public String toString() {
		return "Order [OrderNumber=" + OrderNumber + ", customerID=" + customerID + ", Meal=" + Meal + ", Category="
				+ Category + ", Size=" + Size + ", lvlOfCook=" + lvlOfCook + ", Restrictions=" + Restrictions
				+ ", status=" + status + ", MealPrice=" + MealPrice + ", primaryKey=" + primaryKey + ", FinalPrice="
				+ FinalPrice + ", mealNum=" + mealNum + ", quantity=" + quantity + ", RestaurantName=" + RestaurantName
				+ "]";
	}

}
