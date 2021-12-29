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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.Message;
import logic.MessageType;

public class RestaurantFormController implements Initializable {

	@FXML
	private Button btnApproveOrders;
	@FXML
	private Button btnCreateMenu;
	@FXML
	private Button btnUpdateMenu;


	@FXML
	private Label lblWelcome;
	@FXML
	private Label lblTitle;
	
	@FXML
	private Label lblStatus;
	
	@FXML
	private ImageView imgApproveOrders;
	@FXML
	private ImageView imgCreateMenu;
	@FXML
	private ImageView imgUpdateMenu;
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lblTitle.setText("    " +UserController.firstName + " page");
		lblWelcome.setText("Welcome, " + UserController.firstName);
		lblStatus.setFont(new Font("Arial", 13));
		lblStatus.setText(UserController.status);

		if (UserController.status.equals("Active"))
			lblStatus.setTextFill(Color.GREEN);
		else if (UserController.status.equals("Frozeen"))
			lblStatus.setTextFill(Color.RED);
	}

	public void start() {

	}
	
	/// Duplicates code  *****
	@FXML
	public void getApproveOrdersBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantApproveOrderForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Approve orders");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void getCreateMenuBtn(ActionEvent event) throws Exception {
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
	
	
	public void getUpdateMenuBtn(ActionEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantUpdateMenuForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Update Menu");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	

	@FXML
	public void getApproveOrdersBtnMouse(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantApproveOrderForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Approve orders");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void getCreateMenuBtnMouse(MouseEvent event) throws Exception {
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
	
	
	public void getUpdateMenuBtnMouse(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestaurantUpdateMenuForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Update Menu");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void getLogoutBtn(MouseEvent event) throws Exception {
//		Message msg = new Message(MessageType.logOut,UserController.username);
//		BMClientUI.accept(msg);
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

}
