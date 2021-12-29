package gui;

import java.io.IOException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.BMClientUI;
import client.OrderController;
import client.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import logic.Menu;
import logic.Order;
import logic.Restaurant;

public class CustomerMenuFormController implements Initializable {

	ArrayList<String> meals = new ArrayList<String>();

	ArrayList<String> getMealsInRestaurantItems = new ArrayList<String>(); // Restaurant name, Category
	ArrayList<String> getMealPriceItems = new ArrayList<String>(); // Meal name, Size

	private Order order;
	private String mealPrice;
	boolean addFlag = false;

	@FXML
	private Button BtnNext;
	@FXML
	private Button BtnBack;

	@FXML
	private Label txtPrice;
	@FXML
	private Label txtTotalPrice;
	@FXML
	private Text title;
	@FXML
	private Label lblMessage;
	@FXML
	private ListView<String> MealList;

	@FXML
	private TextField txtRestricions;
	@FXML
	private ImageView imgAdd;

	// Category
	@FXML
	private ComboBox<String> cmpCategory;
	ArrayList<String> Category = new ArrayList<String>();
	private ObservableList<String> Categorylist;

	// Size
	@FXML
	private ComboBox<String> cmpSize1;
	ArrayList<String> Size = new ArrayList<String>();
	private ObservableList<String> Sizelist;

	// LvlOfCook
	@FXML
	private ComboBox<String> cmpLvlOfCook;
	ArrayList<String> LvlOfCook = new ArrayList<String>();
	private ObservableList<String> LvlOfCooklist;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.title.setText("         " + OrderController.selectedRestaurant + " Menu");
		OrderController.ordersList.clear();
		OrderController.deliveryList.clear();
		getMealPriceItems.add(0, OrderController.selectedRestaurant);
//		this.title.setTextAlignment(TextAlignment.CENTER);
		setComboBox();

		int intOrderNumber[] = { 0 };
		int max = 0;
		// To know how many orders the user have already
		ArrayList<Order> ordersListByID = OrderController.getOrderDetailsByID(UserController.UserID);
		if (ordersListByID.isEmpty()) {
			OrderController.orderNumber = 1; // reset order numbers for customer who haven't order yet
		} else { // find the last order number

			intOrderNumber = new int[ordersListByID.size()];
			for (int i = 0; i < ordersListByID.size(); i++) {
				intOrderNumber[i] = (Integer.valueOf(ordersListByID.get(i).getOrderNumber()));

			}
			System.out.println("OrderController.orderNumber " + OrderController.orderNumber);
			max = intOrderNumber[0];
			for (int i = 0; i < ordersListByID.size(); i++) {
				System.out.println("max " + max);
				if (intOrderNumber[i] > max) {
					max = intOrderNumber[i];
				}
			}
			System.out.println("max after for " + max);
			OrderController.orderNumber = max + 1;
		}

		cmpSize1.setDisable(true);
		cmpLvlOfCook.setDisable(true);
		txtRestricions.setDisable(true);

		txtPrice.setFont(new Font("Arial", 20));
		txtPrice.setStyle("-fx-font-weight: bold");

		txtTotalPrice.setFont(new Font("Arial", 20));
		txtTotalPrice.setStyle("-fx-font-weight: bold");

	}

	private void setComboBox() {
		// Add category comboBox
		Category.add("Starter");
		Category.add("Main Course");
		Category.add("Salad");
		Category.add("Drinks");
		Category.add("Dessert");
		Categorylist = FXCollections.observableArrayList(Category);
		cmpCategory.setItems(Categorylist);

		// Add Size comboBox
		Size.add("Big");
		Size.add("Medium");
		Size.add("Small");
		Sizelist = FXCollections.observableArrayList(Size);
		cmpSize1.setItems(Sizelist);

		// Add Level of cook comboBox
		LvlOfCook.add("Well-Done");
		LvlOfCook.add("Medium-Well");
		LvlOfCook.add("Medium");
		LvlOfCooklist = FXCollections.observableArrayList(LvlOfCook);
		cmpLvlOfCook.setItems(LvlOfCooklist);

		// Add Restrictions

	}

	@FXML
	public void getSelectedCategory() {
		this.lblMessage.setText(" ");
		this.txtPrice.setText(" ");
		this.txtRestricions.setText(" ");
		this.cmpSize1.valueProperty().set("");
		this.cmpLvlOfCook.valueProperty().set("");

		OrderController.selectedCategory = cmpCategory.getValue();

		getMealsInRestaurantItems.add(0, OrderController.selectedRestaurant);
		getMealsInRestaurantItems.add(1, OrderController.selectedCategory);

		// get meals from DB and display in list
		meals = OrderController.getMealsInRestaurant(getMealsInRestaurantItems);
		ObservableList<String> ObListMeals = FXCollections.observableArrayList(meals);
		MealList.setItems(ObListMeals); // show meals from specific category
		if (OrderController.selectedCategory != "Main Course") {
			cmpLvlOfCook.setDisable(true);
		} else
			cmpLvlOfCook.setDisable(false);
	}

	@FXML
	public void getSelectedMeal(MouseEvent event) {
		OrderController.selectedMeal = MealList.getSelectionModel().getSelectedItem();
		if (OrderController.selectedMeal != null) {
			cmpSize1.setDisable(false);

			txtRestricions.setDisable(false);
		}

		cmpSize1.setValue("Big");
		OrderController.selectedSize = "Big";
		setPrice();
	}

	@FXML
	public void getSelectedSize() {
		OrderController.selectedSize = cmpSize1.getValue();
		setPrice();

	}

	public void setPrice() {
		getMealPriceItems.add(1, OrderController.selectedMeal);
		getMealPriceItems.add(2, OrderController.selectedSize);

		mealPrice = OrderController.getMealsInRestaurantPrice(getMealPriceItems); // get meal price from DB

		this.txtPrice.setText(mealPrice); // show meal price
	}

	@FXML
	public void getSelectedLvlOfCook() {
		OrderController.selectedLvlOfCook = cmpLvlOfCook.getValue();
	}

	// click on add photo
	@FXML
	void clickedAddOrderMouse(MouseEvent event) {

		String customerID, meal, category, size, lvlOfCook, restrictions, status;
		String Key = OrderController.selectedMeal + "_" + OrderController.selectedSize;
		addFlag = true;
		OrderController.mealNum++;

		customerID = UserController.UserID;
		category = OrderController.selectedCategory;
		size = OrderController.selectedSize;
		meal = OrderController.selectedMeal + "_" + size;
		lvlOfCook = OrderController.selectedLvlOfCook;
		restrictions = txtRestricions.getText();
		status = "Hold"; // until restaurant confirmation

		OrderController.totalPrice += Integer.valueOf(mealPrice); // add meal price to total
		this.txtTotalPrice.setText(String.valueOf(OrderController.totalPrice)); // show order total price

		// HashMap
		if (OrderController.mealsQuantity.containsKey(Key)) { // if the meal exist
			int quantityValue = OrderController.mealsQuantity.get(Key);
			quantityValue++;
			OrderController.mealsQuantity.put(Key, quantityValue);
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("Added one more " + Key);
			lblMessage.setTextFill(Color.GREEN);

		} else {
			OrderController.mealsQuantity.put(Key, 1); // if the meal doesn't exist add 1
			order = new Order(String.valueOf(OrderController.orderNumber), customerID, meal, category, size, lvlOfCook,
					restrictions, status);
			order.setMealNum(OrderController.mealNum);
			order.setMealPrice(mealPrice);
			order.setRestaurantName(OrderController.selectedRestaurant);
			System.out.println("order" + order);
			OrderController.ordersList.add(order); // save meals in arraylist ,, add to DB when click PAY
			System.out.println("OrderController.ordersList" + OrderController.ordersList);
			
//			OrderController.addOrder(order); // Add order to DB
			
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText(Key + " Added succesfuly");
			lblMessage.setTextFill(Color.GREEN);
		}
		// reset fields

//		this.cmpCategory.setValue(" ");

		this.txtRestricions.setText(" ");
//		this.cmpSize1.valueProperty().set("");
		this.cmpLvlOfCook.valueProperty().set("");

		System.out.println("Hash Map: " + Key + ": " + OrderController.mealsQuantity.get(Key));

	}

	@FXML
	void getNextBtn(MouseEvent event) {

		if (OrderController.selectedCategory == null || OrderController.selectedMeal == null) {
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("You must fill all the fields");
			lblMessage.setTextFill(Color.RED);

		} else if (addFlag == false) { // at least one meal
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("You must add meal first");
			lblMessage.setTextFill(Color.RED);
		}
//		else if (OrderController.addOrderFlag == true) {
			OrderController.totalPrice = Integer.valueOf(txtTotalPrice.getText());
			order.setFinalPrice(OrderController.totalPrice);
			System.out.println(" OrderController.totalPrice " + OrderController.totalPrice);

//			OrderController.SetFinalPriceByOrder(order); // NEED TO CHANGE TO FINAL ORDER WINDOW***

			FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDeliveryForm.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				UserController.currentStage.setTitle("Delivery details");
				UserController.currentStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
//	else {
//			lblMessage.setFont(new Font("Arial", 18));
//			lblMessage.setText("Error adding order");
//			lblMessage.setTextFill(Color.RED);
//		}

//	}

	@FXML
	void getBackBtn(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerChooseRestaurantForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Choose restaurant");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
