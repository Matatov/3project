package gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Delivery;
import logic.Order;
import logic.User;

public class RestaurantApproveOrderFormController implements Initializable {

	@FXML
	private Button btnChange;
	@FXML
	private Button btnBack;

	@FXML
	private Label lblMessage;
	@FXML
	private Label lblCurrentStatus;

	@FXML
	private ComboBox<String> cmpIdOrderNum;
	private ObservableList<String> IdOrderNumList;
	ArrayList<String> IdOrderNum = new ArrayList<String>();

	@FXML
	private ComboBox<String> cmpStatus;
	private ObservableList<String> StatusList;
	ArrayList<String> Status = new ArrayList<String>();

	@FXML
	private TableView<Order> OrderToApprove = new TableView<Order>();
	@FXML
	private TableColumn<Order, String> mealsColumn;
	@FXML
	private TableColumn<Order, String> levelOfCookColumn;
	@FXML
	private TableColumn<Order, String> retictionsColumn;
	@FXML
	private TableColumn<Order, String> priceColumn;
	@FXML
	private TableColumn<Order, String> statusColumn;

	ArrayList<String> getOrderDetailsItems = new ArrayList<String>(); // Order Number, status to be change

	private String selectedStatus;
	private String selectedNewStatus;
	String ID;
	String OrderNum; // 034556

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setComboBox();

	}

	private void setComboBox() {
		ArrayList<User> usersList = UserController.getCustomerDetailsByAuthorization("Customer");
		HashMap<String, String> OrderNumHashMap = new HashMap<>();
		String UserID;
		String OrderNumber;

		// Display by IdNumber_OrderNumber and status is Hold
		for (int i = 0; i < usersList.size(); i++) {
			UserID = usersList.get(i).getiD();
			ArrayList<Order> ordersListByID = OrderController.getOrderDetailsByID(UserID);
			for (int j = 0; j < ordersListByID.size(); j++) {
				OrderNumber = ordersListByID.get(j).getOrderNumber();
				if (ordersListByID.get(j).getStatus().equals("Hold")) {
					if (!OrderNumHashMap.containsValue(OrderNumber)) {
						OrderNumHashMap.put(UserID, OrderNumber);
						IdOrderNum.add(UserID + "_" + OrderNumber);
					}
				}
			}
		}

		IdOrderNumList = FXCollections.observableArrayList(IdOrderNum);
		cmpIdOrderNum.setItems(IdOrderNumList);

		// Add Status comboBox
		Status.add("Hold");
		Status.add("Approve");
		Status.add("Decline");
		StatusList = FXCollections.observableArrayList(Status);
		cmpStatus.setItems(StatusList);
	}

	@FXML
	public void getSelectedComboBox() {
		String ID_OrderNum = cmpIdOrderNum.getValue();
		String[] parts = ID_OrderNum.split("_");
		ID = parts[0]; // 004
		OrderNum = parts[1]; // 034556
		cmpIdOrderNum.getValue();
		getOrderDetailsItems.add(0, ID);
		getOrderDetailsItems.add(1, OrderNum);

//		ArrayList<Order> ordersList = OrderController.getOrderDetails(getOrderDetailsItems);
//		System.out.println("ordersList is: " + ordersList);

		displayTable();
		cmpStatus.setDisable(false);

//		for (int i = 0; i < ordersList.size(); i++) { // set quantity for each meal
//		String Key = ordersList.get(i).getMeal();
//		Integer value = OrderController.mealsQuantity.get(Key);
//
//		ordersList.get(i).setQuantity(String.valueOf(value));
//	}
	}

	public void displayTable() {

		ArrayList<Order> ordersList = OrderController.getOrderDetails(getOrderDetailsItems);
		ObservableList<Order> data = FXCollections.observableArrayList(ordersList);

		mealsColumn.setCellValueFactory(new PropertyValueFactory<>("Meal"));
		levelOfCookColumn.setCellValueFactory(new PropertyValueFactory<>("lvlOfCook"));
		retictionsColumn.setCellValueFactory(new PropertyValueFactory<>("Restrictions"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("MealPrice"));

		selectedStatus = ordersList.get(0).getStatus();
		this.lblCurrentStatus.setText(selectedStatus);

		OrderToApprove.setItems(data);

	}

	@FXML
	public void getSelectedStatus() {
		selectedNewStatus = cmpStatus.getValue();
		getOrderDetailsItems.add(2, selectedNewStatus); // save new status

	}

	@FXML
	public void getChangeBtn(MouseEvent event) throws Exception {
		if (selectedNewStatus == null) {
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("You must select order status");
		} else if (selectedStatus.equals(selectedNewStatus)) {
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("The current status is " + selectedNewStatus);
		} else {

			boolean changed = UserController.changeOrderStatus(getOrderDetailsItems);
			System.out.println("changed " + changed);

			cmpStatus.setDisable(true);
			if (changed) {
				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Status has changed to " + selectedNewStatus);
			} else {
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Status not changed");
			}
			cmpStatus.setValue(" ");

			displayTable();

			// displayTable(); // display table again
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
