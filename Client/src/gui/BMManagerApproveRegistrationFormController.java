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
import javafx.stage.Stage;
import logic.Order;

public class BMManagerApproveRegistrationFormController implements Initializable {


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	public void getNextBtn(MouseEvent event) throws Exception {
//		FXMLLoader loader = new FXMLLoader(getClass().getResource(".fxml"));
//		Parent root;
//		try {
//			root = loader.load();
//			Scene scene = new Scene(root);
//			UserController.currentStage.setTitle("");
//			UserController.currentStage.setScene(scene);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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
