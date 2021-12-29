package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.Delivery;
import logic.Order;
import logic.Restaurant;

public class CustomerViewOrderFormController implements Initializable {

	@FXML
	private Label lblTitle;
	@FXML
	private Button btnUpdate;
	@FXML
	private Button btnBack;

	@FXML
	private ComboBox<String> cmpOrderNum;
	private ObservableList<String> OrderNumbersList;
	ArrayList<String> OrderNumbers = new ArrayList<String>();

	@FXML
	private TableView<Order> OrderTbl = new TableView<Order>();
	@FXML
	private TableView<Delivery> DeliveryTbl = new TableView<Delivery>();

	@FXML
	private TableColumn<Order, String> orderNumColumn;
	@FXML
	private TableColumn<Order, String> statusColumn;
	@FXML
	private TableColumn<Order, String> restaurantColumn;
	@FXML
	private TableColumn<Delivery, String> dateColumn;
	@FXML
	private TableColumn<Delivery, String> timeColumn;
	@FXML
	private TableColumn<Delivery, String> deliveryTypeColumn;

	ArrayList<String> getItems = new ArrayList<String>(); // UserID,OrderNum , MealNum

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		HashMap<String, String> OrderNumHashMap = new HashMap<>(); // key= user ID , value = order number
		String UserID = UserController.UserID;
		String OrderNumber;
		lblTitle.setText("     " + UserController.userName + " Orders");

		// Add Order Table and set order number ComboBox
		getItems.add(0, UserController.UserID);

		ArrayList<Order> ordersListByID = OrderController.getOrderDetailsByID(UserController.UserID);
		ArrayList<Order> ordersListByIDWithoutDuplicates = new ArrayList<Order>();

		for (int i = 0; i < ordersListByID.size(); i++) {

			OrderNumber = ordersListByID.get(i).getOrderNumber();

			if (!OrderNumHashMap.containsValue(OrderNumber)) {
				OrderNumHashMap.put(UserID, OrderNumber);
				ordersListByIDWithoutDuplicates.add(ordersListByID.get(i));
				OrderNumbers.add(OrderNumber);
			}
		}
		// Set ComboBox
		OrderNumbersList = FXCollections.observableArrayList(OrderNumbers);
		cmpOrderNum.setItems(OrderNumbersList);

		// Set Table
		ObservableList<Order> data = FXCollections.observableArrayList(ordersListByIDWithoutDuplicates);

		System.out.println("data is " + data);

		orderNumColumn.setCellValueFactory(new PropertyValueFactory<>("OrderNumber"));
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		restaurantColumn.setCellValueFactory(new PropertyValueFactory<>("RestaurantName"));

		OrderTbl.setItems(data);

	

		// Add delivery table

		ArrayList<Delivery> delievryList = OrderController.getDeliveryDetails(UserController.UserID);
		ObservableList<Delivery> data1 = FXCollections.observableArrayList(delievryList);

		System.out.println("data1 is " + data1);

		dateColumn.setCellValueFactory(new PropertyValueFactory<>("Date"));
		timeColumn.setCellValueFactory(new PropertyValueFactory<>("Time"));
		deliveryTypeColumn.setCellValueFactory(new PropertyValueFactory<>("TypeOfDelivery"));

		DeliveryTbl.setItems(data1);

	}

	@FXML
	public void getSelectedOrderNumber() {

	}

	@FXML
	public void getUpdateBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerEditOrderForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Edit orders");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Customer page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
