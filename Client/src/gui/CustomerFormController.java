package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.BMClientUI;
import client.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import logic.Message;
import logic.MessageType;

public class CustomerFormController implements Initializable {

	@FXML
	private Button btnLogout;

	@FXML
	private Button CreateNewOrder;

	@FXML
	private Button ViewExsistingOrder;

	@FXML
	private Label lblWelcome;

	@FXML
	private Label lblStatus;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblWelcome.setText("Welcome, " + UserController.firstName + " " + UserController.lastName);
		lblStatus.setFont(new Font("Arial", 13));
		lblStatus.setText(UserController.status);

		if (UserController.status.equals("Active"))
			lblStatus.setTextFill(Color.GREEN);
		else if (UserController.status.equals("Frozeen"))
			lblStatus.setTextFill(Color.RED);

	}

	@FXML
	void clickedCreateNewOrder(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CustomerChooseRestaurantForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Choose Restaurant");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void clickedViewExsistingOrder(ActionEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerViewOrderForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Orders");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// click on photos
	@FXML
	void clickedCreateNewOrderMouse(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerChooseRestaurantForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Choose Restaurant");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	void clickedViewExsistingOrderMouse(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerViewOrderForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Orders");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	

	@FXML
	public void getLogoutBtn(MouseEvent event) throws Exception {
		Message msg = new Message(MessageType.logOut, UserController.userName);
		BMClientUI.accept(msg);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Login");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
		// TODO Auto-generated method stub

	}

}
