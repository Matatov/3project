package gui;

import java.io.IOException;

import client.BMClientUI;
import client.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class CustomerEditOrderFormController {


	@FXML
	public void getNextBtn(MouseEvent event) throws Exception {

	}


	@FXML 
	public void getBackBtn(MouseEvent event) throws Exception {
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

}
