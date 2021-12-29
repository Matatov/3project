package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Order;
import logic.User;

public class HRConfirmAccountsFormController implements Initializable {

	ArrayList<String> userItems = new ArrayList<String>(); // ID, status to be change

	private String selectedUserID;
	private String selectedStatus;

	@FXML
	private Label lblMessage;

	@FXML
	private TableView<User> tbl = new TableView<User>();
	@FXML
	private TableColumn<User, String> idCol;
	@FXML
	private TableColumn<User, String> firstNameCol;
	@FXML
	private TableColumn<User, String> lastNameCol;

	@FXML
	private TableColumn<User, String> phoneCol;
	@FXML
	private TableColumn<User, String> emailCol;
	@FXML
	private TableColumn<User, String> employeeNameCol;
	@FXML
	private TableColumn<User, String> companyCodeCol;
	@FXML
	private TableColumn<User, String> auturizationCol;

	@FXML
	private ComboBox<String> cmpStatus;
	private ObservableList<String> StatusList;
	ArrayList<String> Status = new ArrayList<String>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayTable();

		// set combobox
		Status.add("Approve");
		Status.add("Decline");
		StatusList = FXCollections.observableArrayList(Status);
		cmpStatus.setItems(StatusList);
		cmpStatus.setDisable(true);
	}
	
	
	public void displayTable() {
		ArrayList<User> usersList = UserController.getRequstionsForBuisnesAccount();
		ObservableList<User> data = FXCollections.observableArrayList(usersList);

		idCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getiD()));
		firstNameCol.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		lastNameCol.setCellValueFactory(new PropertyValueFactory<>("LastName"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		employeeNameCol.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
		companyCodeCol.setCellValueFactory(new PropertyValueFactory<>("companyCode"));
		auturizationCol.setCellValueFactory(new PropertyValueFactory<>("Authorization"));
		tbl.getItems().setAll(data);
	}
	

	@FXML
	public void getSelectedUserRequest(MouseEvent event) {
		cmpStatus.setDisable(false);
		User user = tbl.getSelectionModel().getSelectedItem();
		if (user != null) {
			selectedUserID = user.getiD();
			userItems.add(0, selectedUserID);
		}

	}

	@FXML
	public void getSelectedStatus() {
		selectedStatus = cmpStatus.getValue();
		userItems.add(1, selectedStatus);

	}

	/*
	 * 
	 * Nikita Click on Action: selected user = UserId from table is sent to
	 * userController, from there it is sent to Sever and to DBConrtroller
	 */
	@FXML
	public void getConfirmBtn(MouseEvent event) throws Exception {
		if (UserController.confirmBuisnesAccount(userItems)) {
			if (selectedStatus == "Approve") {
				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText(selectedUserID + " has been approved");
			} else {
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText(selectedUserID + " has been declien");
			}

		} else {
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("Error status not changed");
		}
		
		cmpStatus.setValue(" ");

		displayTable();

	}

	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("HRForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("HR page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
