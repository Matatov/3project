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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import logic.Restaurant;

public class CustomerChooseRestaurantFormController implements Initializable {

	@FXML
	private Button BtnNext;

	@FXML
	private Button BtnBack;

	@FXML
	private Label lblMessage;

	@FXML
	private TableView<Restaurant> tbl = new TableView<Restaurant>();
	@FXML
	private TableColumn<Restaurant, String> nameColumn;
	@FXML
	private TableColumn<Restaurant, String> addressColumn;
	@FXML
	private TableColumn<Restaurant, String> BMMenagerIDColumn;

	private String selectedRestaurant;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ArrayList<Restaurant> restaurants = OrderController.getAllRestaurants(null);
		ObservableList<Restaurant> data = FXCollections.observableArrayList(restaurants);

//		for (int i = 0; i < restaurants.size(); i++) {
////			System.out.println("res [" + i + "]" + res[i]);
//			if (restaurants[i] != null) {
//				data.add(res[i]);
//			}
//		}
		System.out.println("data is " + data);

		nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		addressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));

		tbl.setItems(data);

	}

	@FXML
	public void getSelectedRestaurant(MouseEvent event) {
		selectedRestaurant = tbl.getSelectionModel().getSelectedItem().getName();
		lblMessage.setTextFill(Color.BLACK);
		lblMessage.setFont(new Font("Arial", 18));

		lblMessage.setText("You select " + selectedRestaurant + " restaurant");

	}

	@FXML
	void getNextBtn(MouseEvent event) {

		if (selectedRestaurant != null) {

			OrderController.selectedRestaurant = selectedRestaurant; // to save

			FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerMenuForm.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				UserController.currentStage.setTitle("Menu");
				UserController.currentStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			lblMessage.setText("You must choose restaurant");
			lblMessage.setTextFill(Color.RED);
		}
	}

	@FXML
	void getBackBtn(MouseEvent event) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Customer page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
