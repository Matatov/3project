package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.OrderController;
import client.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Order;

public class RestaurantUpdateMenuFormController implements Initializable {

	ArrayList<String> meals = new ArrayList<String>();
	ArrayList<String> getMealsInRestaurantItems = new ArrayList<String>(); // Restaurant name, Category
	ArrayList<String> getMealPriceItems = new ArrayList<String>(); // Meal name, Size

	ArrayList<String> MenuItems = new ArrayList<String>(); // Restaurant name , MealName, size

	private String mealPrice;
	String selectedSize;
	String selectedMeal;

	@FXML
	private ImageView imgUpdate;
	@FXML
	private ImageView imgDelete;
	@FXML
	private ImageView imgAdd;
	@FXML
	private ImageView imgBack;

	@FXML
	private Label lblMessage;

	@FXML
	private ListView<String> MealList;

	@FXML
	private TextField txtPriceBig;
	@FXML
	private TextField txtPriceMedium;
	@FXML
	private TextField txtPriceSmall;

	@FXML
	private ComboBox<String> cmpCategory;
	ArrayList<String> Category = new ArrayList<String>();
	private ObservableList<String> Categorylist;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setComboBox();
		getMealsInRestaurantItems.add(0, UserController.firstName);
		getMealPriceItems.add(0, UserController.firstName);
		MenuItems.add(0, UserController.firstName);
	}

	private void setComboBox() {
		Category.add("Starter");
		Category.add("Main Course");
		Category.add("Salad");
		Category.add("Drinks");
		Category.add("Dessert");
		Categorylist = FXCollections.observableArrayList(Category);
		cmpCategory.setItems(Categorylist);
	}

	@FXML
	public void getSelectedCategory() {
		getMealsInRestaurantItems.add(1, cmpCategory.getValue());
		setList();

		this.txtPriceBig.setText(" ");
		this.txtPriceMedium.setText(" ");
		this.txtPriceSmall.setText(" ");

	}

	public void setList() {
		meals = OrderController.getMealsInRestaurant(getMealsInRestaurantItems);
		ObservableList<String> ObListMeals = FXCollections.observableArrayList(meals);
		MealList.setItems(ObListMeals); // show meals from specific category
	}

	@FXML
	public void getSelectedMeal(MouseEvent event) {
		selectedMeal = MealList.getSelectionModel().getSelectedItem();
		MenuItems.add(1, selectedMeal);

		selectedSize = "Big";
		setPrice(selectedSize);
		this.txtPriceBig.setText(mealPrice); // show meal price

		selectedSize = "Medium";
		setPrice(selectedSize);
		this.txtPriceMedium.setText(mealPrice); // show meal price

		selectedSize = "Small";
		setPrice(selectedSize);
		this.txtPriceSmall.setText(mealPrice); // show meal price

	}

	public void setPrice(String selectedSize) {
		getMealPriceItems.add(1, selectedMeal);
		getMealPriceItems.add(2, selectedSize);

		mealPrice = OrderController.getMealsInRestaurantPrice(getMealPriceItems); // get meal price from DB

	}

	@FXML
	public void getUpdateBtn(MouseEvent event) throws Exception {
		if (selectedMeal == null) {
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("You must choose meal first");
		} else {
			MenuItems.add(2, "Big");
			MenuItems.add(3, txtPriceBig.getText());
			boolean flag1 = OrderController.UpdateMealsPriceInMenu(MenuItems);

			MenuItems.add(2, "Medium");
			MenuItems.add(3, txtPriceMedium.getText());
			boolean flag2 = OrderController.UpdateMealsPriceInMenu(MenuItems);

			MenuItems.add(2, "Small");
			MenuItems.add(3, txtPriceSmall.getText());
			boolean flag3 = OrderController.UpdateMealsPriceInMenu(MenuItems);

			if (flag1 && flag2 && flag3) {
				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText(selectedMeal + " Updated succesfully");
				setList();
			}
		}

	}

	@FXML
	public void getDeleteBtn(MouseEvent event) throws Exception {
		if (selectedMeal == null) {
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("You must choose meal first");
		} else {
			MenuItems.add(2, "Big");

			boolean flag1 = OrderController.DeleteMealFromMenu(MenuItems);

			MenuItems.add(2, "Medium");

			boolean flag2 = OrderController.DeleteMealFromMenu(MenuItems);

			MenuItems.add(2, "Small");

			boolean flag3 = OrderController.DeleteMealFromMenu(MenuItems);

			if (flag1 && flag2 && flag3) {
				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText(selectedMeal + " Deleted succesfully");
				setList();
			}
		}
	}

	@FXML
	public void getAddBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantCreateMenuForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Add meals");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Restaurant page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
