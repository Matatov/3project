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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Order;
import logic.User;

public class BMManagerRegisterFormController implements Initializable {
	String selectedAccountType;

	@FXML
	private Button btnSignUp;
	@FXML
	private Button btnBack;

	@FXML
	private ComboBox<String> cmpAccountType;
	ArrayList<String> AccountType = new ArrayList<String>();
	private ObservableList<String> AccountTypelist;

	@FXML
	private TextField txtID;
	@FXML
	private TextField txtFirstName;
	@FXML
	private TextField txtLastName;
	@FXML
	private TextField txtPhoneNumber;
	@FXML
	private TextField txtEmail;
	@FXML
	private TextField txtCreditCard;
	@FXML
	private TextField txtEmployeeName;
	@FXML
	private TextField txtEmployeeCode;

	@FXML
	private Label lblMessage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Add Account type combo box
		AccountType.add("Private account");
		AccountType.add("Buisness account");
		AccountTypelist = FXCollections.observableArrayList(AccountType);
		cmpAccountType.setItems(AccountTypelist);

		txtCreditCard.setDisable(true);
		txtEmployeeName.setDisable(true);
		txtEmployeeCode.setDisable(true);

	}

	@FXML
	public void getSelectedAccountType() {
		selectedAccountType = cmpAccountType.getValue();
		switch (selectedAccountType) {
		case "Private account":
			txtCreditCard.setDisable(false);
			txtEmployeeName.setDisable(true);
			txtEmployeeCode.setDisable(true);

			break;
		case "Buisness account":
			txtCreditCard.setDisable(true);
			txtEmployeeName.setDisable(false);
			txtEmployeeCode.setDisable(false);
			break;
		}

	}

	// NEED TO CHECK IF RESTAURANT OR CUSTOMER

	@FXML
	public void getSignUpBtn(MouseEvent event) throws Exception {
		User user = null;
		String UserID = txtID.getText();
		String firstName = txtFirstName.getText();
		String lastName = txtLastName.getText();
		String phone = txtPhoneNumber.getText();
		String email = txtEmail.getText();

		if (UserID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty() || email.isEmpty()) {
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("You must fill all fields");
		} else {
			switch (selectedAccountType) {
			case "Private account":
				String creditCard = txtCreditCard.getText();
				if (creditCard.isEmpty()) {
					if (creditCard.isEmpty()) {
						lblMessage.setTextFill(Color.RED);
						lblMessage.setFont(new Font("Arial", 20));
						lblMessage.setText("You must fill specific details");
					} else {
						user = new User(UserID, firstName, lastName, phone, email, "Customer", "Private", creditCard);
					}
				}
				break;
			case "Buisness account":
				String employeeName = txtEmployeeName.getText();
				String comapanyCode = txtEmployeeCode.getText();
				if (employeeName.isEmpty() || comapanyCode.isEmpty()) {
					lblMessage.setTextFill(Color.RED);
					lblMessage.setFont(new Font("Arial", 20));
					lblMessage.setText("You must fill specific details");
				} else {
					user = new User(UserID, firstName, lastName, phone, email, "Customer", "Requested", employeeName,
							comapanyCode);
				}
				break;
			}

			if (UserController.addUserToDB(user)) {
				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 18));
				lblMessage.setText("Adding user to DB");

			} else {
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 18));
				lblMessage.setText("Error adding user to DB");

			}

		}
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
