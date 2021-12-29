package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import client.OrderController;
import client.UserController;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Delivery;
import logic.Order;
import logic.User;

public class BMManagerManageDataFormController implements Initializable {

	@FXML
	private Label lblMessage;

	@FXML
	private Button btnBack;
	@FXML
	private Button btnChange;

	@FXML
	private ComboBox<String> cmpStatus;
	private ObservableList<String> statusList;
	ArrayList<String> statusArray = new ArrayList<String>();

	@FXML
	private TableView<User> UsersTbl = new TableView<User>();

	@FXML
	private TableColumn<User, String> customerIDColumn;
	@FXML
	private TableColumn<User, String> W4CColumn;
	@FXML
	private TableColumn<User, String> userNameColumn;
	@FXML
	private TableColumn<User, String> passwordColumn;
	@FXML
	private TableColumn<User, String> accountTypeColumn;
	@FXML
	private TableColumn<User, String> currentStatusColumn;

	ArrayList<String> getUserItems = new ArrayList<String>(); // CustomerID, status to be change
	ArrayList<User> usersList;
	private String selectedCustomer;
	private String selectedStatus;
	private String selectedNewStatus;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setComboBox();
		cmpStatus.setDisable(true);

		displayTable();

	}

	public ObservableList<User> getUsers() {
		usersList = UserController.getCustomerDetailsByAuthorization("Customer");

		ObservableList<User> data = FXCollections.observableArrayList(usersList);
		return data;
	}

	public void displayTable() {
		customerIDColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getiD()));
		W4CColumn.setCellValueFactory(new PropertyValueFactory<>("W4C"));
		userNameColumn.setCellValueFactory(new PropertyValueFactory<>("userName"));
		passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));
		accountTypeColumn.setCellValueFactory(new PropertyValueFactory<>("AccountType"));
		currentStatusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

		ObservableList<User> data = FXCollections.observableArrayList(getUsers());
		UsersTbl.setItems(data);
	}

	@FXML
	public void getSelectedCustomer() {
		cmpStatus.setDisable(false);
		getUserItems.add(0, UsersTbl.getSelectionModel().getSelectedItem().getiD()); // save customer id
		selectedCustomer = UsersTbl.getSelectionModel().getSelectedItem().getUserName();
		selectedStatus = UsersTbl.getSelectionModel().getSelectedItem().getStatus();
		lblMessage.setTextFill(Color.BLACK);
		lblMessage.setFont(new Font("Arial", 20));

		lblMessage.setText("You select " + selectedCustomer + " and his status is " + selectedStatus);

	}

	@FXML
	public void getSelectedStatus() {
		selectedNewStatus = cmpStatus.getValue();
		getUserItems.add(1, selectedNewStatus); // save new status

	}

	private void setComboBox() {
		// Add category comboBox
		statusArray.add("Active");
		statusArray.add("Frozeen");
		statusList = FXCollections.observableArrayList(statusArray);
		cmpStatus.setItems(statusList);
	}

	@FXML
	public void getChangeBtn(MouseEvent event) throws Exception {
	if(selectedCustomer== null) {
		lblMessage.setTextFill(Color.RED);
		lblMessage.setFont(new Font("Arial", 20));
		lblMessage.setText("You must select customer first");
	}
		else if (selectedStatus.equals(selectedNewStatus)) {
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("The current status is " + selectedNewStatus);
		} else {

			boolean changed = UserController.changeCustomerStatus(getUserItems);
			
			this.UsersTbl.setEditable(true);
			
			cmpStatus.setDisable(true);
			if (changed) {
				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Status of " + selectedCustomer + " changed to " + selectedNewStatus);
			} else {
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Status not changed");
			}
			cmpStatus.setValue(" ");
			displayTable(); // display table again
		}
//		

	}

	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BMManagerForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("BM Manager page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
