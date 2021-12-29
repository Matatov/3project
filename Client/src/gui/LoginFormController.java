package gui;

import java.io.IOException;

import client.BMClientUI;
import client.ScreenControllers;
import client.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Message;
import logic.MessageType;

public class LoginFormController {
	public static String username;

	@FXML
	private Button btnLogin;

	@FXML
	private Button btnExit;

	@FXML
	private Label lblMessage;

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	public String getUsername() {
		return txtUsername.getText();
	}

	private String getPassword() {
		return txtPassword.getText();
	}

	public void start(Stage primaryStage) throws IOException {

	}

	@FXML
	void getLoginBtn(MouseEvent event) {

		String logInStatus = UserController.logIn(getUsername(), getPassword());
		
		UserController.getUserDetails(getUsername(), getPassword());
		UserController.userName = getUsername();
		UserController.password = getPassword();
//		System.out.println(logInStatus);
		switch (logInStatus) {
		case "You must fill all the fields":
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("You must fill all the fields");
			lblMessage.setTextFill(Color.RED);
			break;
		case "The user does not exist":
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("The user does not exist");
			lblMessage.setTextFill(Color.RED);

			break;
		case "The password is incorrect":
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("The password is incorrect");
			lblMessage.setTextFill(Color.RED);

			break;
		case "The user is already connected":
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("The user is already connected");
			lblMessage.setTextFill(Color.RED);

			BMClientUI.display(logInStatus);
			break;
		case "BMManager": // openManagerForm();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("BMManagerForm.fxml"));
			Parent root;
			try {
				root = loader.load();
				ScreenControllers.ManagerFormController = loader.getController();
				Scene scene = new Scene(root);
				UserController.currentStage.setScene(scene);
				UserController.currentStage.setTitle("BMManager page");
				ScreenControllers.ManagerFormController.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case "Restaurant": // openEmployeeForm();

//			username = getUsername();
//			UserController.userName = getUsername();
			FXMLLoader loader1 = new FXMLLoader(getClass().getResource("RestaurantForm.fxml"));
			Parent root1;
			try {
				root1 = loader1.load();
				ScreenControllers.restaurantFormController = loader1.getController();
				Scene scene = new Scene(root1);
				UserController.currentStage.setTitle("Restaurant page");
				UserController.currentStage.setScene(scene);
				ScreenControllers.restaurantFormController.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
			break;

		case "Customer": // openCustomerForm();

			FXMLLoader loader2 = new FXMLLoader(getClass().getResource("CustomerForm.fxml"));
			Parent root2;
			try {
				root2 = loader2.load();
				ScreenControllers.customerFormController = loader2.getController();
				Scene scene = new Scene(root2);
				UserController.currentStage.setTitle("Customer page");
				UserController.currentStage.setScene(scene);
				ScreenControllers.customerFormController.start();

			} catch (IOException e) {
				e.printStackTrace();
			}

			break;

		case "HR": // openCustomerForm();

			FXMLLoader loader3 = new FXMLLoader(getClass().getResource("HRForm.fxml"));
			Parent root3;
			try {
				root3 = loader3.load();
				ScreenControllers.hrFormController = loader3.getController();
				Scene scene = new Scene(root3);
				UserController.currentStage.setTitle("HR page");
				UserController.currentStage.setScene(scene);
				ScreenControllers.hrFormController.start();

			} catch (IOException e) {
				e.printStackTrace();
			}

			break;

		case "CEO": // openCustomerForm();
			FXMLLoader loader4 = new FXMLLoader(getClass().getResource("CEOForm.fxml"));
			Parent root4;
			try {
				root4 = loader4.load();
				ScreenControllers.ceoFormController = loader4.getController();
				Scene scene = new Scene(root4);
				UserController.currentStage.setTitle("CEO page");
				UserController.currentStage.setScene(scene);
				ScreenControllers.ceoFormController.start();
//			
			} catch (IOException e) {
				e.printStackTrace();
			}

			break;

		default:
			BMClientUI.display("cant read message from server");
		}

	}

	public void getExitBtn(MouseEvent event) throws Exception {
		System.out.println("Come back soon:)");

		FXMLLoader loader = new FXMLLoader(getClass().getResource("IpForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			ScreenControllers.ipFormController = loader.getController();
			Scene scene = new Scene(root);
			UserController.currentStage.setScene(scene);

			ScreenControllers.ipFormController.start(UserController.currentStage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
