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

public class CEOCompareLogsFormController implements Initializable {


	// Log 1

	// Combo box can be the same name initialize one time , the methods different
	@FXML
	private ComboBox<String> cmpRestaurants;
	ArrayList<String> Restaurants = new ArrayList<String>();
	private ObservableList<String> Restaurantslist;

	@FXML
	private ComboBox<String> cmpMonth;
	ArrayList<String> Month = new ArrayList<String>();
	private ObservableList<String> Monthlist;

	@FXML
	private TableView<?> tblLogs;

	@FXML
	private TableColumn<?, String> orderColumn;

	@FXML
	private TableColumn<?, String> customerColumn;

	@FXML
	private TableColumn<?, String> nameColumn;

	@FXML
	private TableColumn<?, String> priceColumn;

	@FXML
	private TextField txtTotalPrice;

	@FXML
	private Label lblRestaurantName;

	///// Log 2

	@FXML
	private TableView<?> tblLogs1;

	@FXML
	private TableColumn<?, String> orderColumn1;

	@FXML
	private TableColumn<?, String> customerColumn1;

	@FXML
	private TableColumn<?, String> nameColumn1;

	@FXML
	private TableColumn<?, String> priceColumn1;

	@FXML
	private TextField txtTotalPrice1;

	@FXML
	private Label lblRestaurantName1;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

	@FXML
	void getSelectedMonthMethodLog1(ActionEvent event) {

	}

	@FXML
	void getSelectedRestaurantLog1(MouseEvent event) {

	}

	@FXML
	void getSelectedRestaurantMethodLog1(ActionEvent event) {

	}

	@FXML
	void getSelectedMonthMethodLog2(ActionEvent event) {

	}

	@FXML
	void getSelectedRestaurantLog2(MouseEvent event) {

	}

	@FXML
	void getSelectedRestaurantMethodLog2(ActionEvent event) {

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
