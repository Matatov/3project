package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.UserController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import logic.Order;
import logic.User;

public class HRRegisterEmployeeFormController implements Initializable {

	@FXML
	private TextField txtCode;

	@FXML
	private TextField txtName;

	@FXML
	private Label lblMessage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	public void getSignUpBtn(MouseEvent event) throws Exception {

		if ((txtCode.getText().isEmpty()) && (txtName.getText().isEmpty())) {
			lblMessage.setTextFill(Color.RED);
			lblMessage.setFont(new Font("Arial", 20));
			lblMessage.setText("You must fill all fields");

		} else {
			
			if (UserController.registerNewEmployer(txtCode.getText(), txtName.getText())) {
				
				lblMessage.setTextFill(Color.GREEN);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Employer registration succseed");
			} else {
				lblMessage.setTextFill(Color.RED);
				lblMessage.setFont(new Font("Arial", 20));
				lblMessage.setText("Employer registration failed");
			}
		}
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
