package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.Order;

public class CEOViewLogsOrderFormController implements Initializable {


	@FXML
	private ComboBox<String> cmpRestaurants;
	ArrayList<String> Restaurants = new ArrayList<String>();
	private ObservableList<String> Restaurantslist;

	@FXML
	private ComboBox<String> cmpMonth;
	ArrayList<String> Month = new ArrayList<String>();
	private ObservableList<String> Monthlist;

	@FXML
	private ComboBox<String> cmpCategory;
	ArrayList<String> Category = new ArrayList<String>();
	private ObservableList<String> Categorylist;

	@FXML
	private TableView<?> tblLogs;

	@FXML
	private TableColumn<?, String> orderColumn;

	@FXML
	private TableColumn<?, String> customerColumn;

	@FXML
	private TableColumn<?, String> nameColumn;

	@FXML
	private TableColumn<?, String> mealColumn;

	@FXML
	private TextField txtTotalMealCount;

	@FXML
	private Label lblRestaurantName;

	@FXML
	private Label lblCategoryName;

	@FXML
	private Label lblMessage; // Message to client on screen

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void getSelectedMonthMethod(ActionEvent event) {

	}

	@FXML
	void getSelectedRestaurantMethod(ActionEvent event) {

	}

	@FXML
	void getSelectedRestaurant(MouseEvent event) {

	}

	@FXML
	void getSelectedCategoryMethod(ActionEvent event) {

	}

	@FXML
	public void getBackBtn(MouseEvent event) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CEOForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("CEO page");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
