package gui;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import logic.Delivery;
import logic.Order;
import logic.Restaurant;

public class CustomerDeliveryFormController implements Initializable {

	@FXML
	private Button BtnNext;
	@FXML
	private Button BtnBack;

	@FXML
	private Label lblMessage;

	@FXML
	private TextField txtName;
	@FXML
	private DatePicker txtDate;
	@FXML
	private TextField txtPhone;
	@FXML
	private TextField txtTime;
	@FXML
	private TextField txtAddress;

	@FXML
	private TextField txtEmployeeName;
	@FXML
	private TextField txtEmployeeCode;

	@FXML
	private ComboBox<String> cmpType;
	private ObservableList<String> Typelist;
	ArrayList<String> Type = new ArrayList<String>();

	@FXML
	private ComboBox<String> cmpDeliveryType;
	private ObservableList<String> DeliveryTypelist;
	ArrayList<String> DeliveryType = new ArrayList<String>();

	@FXML
	private ComboBox<String> cmpPayment;
	private ObservableList<String> Paymentlist;
	ArrayList<String> PaymentMethod = new ArrayList<String>();

	private String AccountType;
	private String DeliveryTypeOptions;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Add Type comboBox
		Type.add("Take-Away");
		Type.add("Delivery");
		Typelist = FXCollections.observableArrayList(Type);
		cmpType.setItems(Typelist);

		// Add Payment comboBox
		PaymentMethod.add("Private account");
		PaymentMethod.add("Buisness account");
		Paymentlist = FXCollections.observableArrayList(PaymentMethod);
		cmpPayment.setItems(Paymentlist);

		cmpPayment.setDisable(true);
		cmpDeliveryType.setDisable(true);
		txtName.setDisable(true);
		txtDate.setDisable(true);
		txtPhone.setDisable(true);
		txtTime.setDisable(true);
		txtAddress.setDisable(true);
		txtEmployeeName.setDisable(true);
		txtEmployeeCode.setDisable(true);

//		this.title.setTextAlignment(TextAlignment.CENTER);

//		cmpType.setDisable(true);
	}

	@FXML
	public void getSelectedType() {
		DeliveryTypeOptions = cmpType.getValue();
		OrderController.selectedType = DeliveryTypeOptions; // ****
//		System.out.println("type " + DeliveryTypeOptions);
		if (DeliveryTypeOptions == "Take-Away") {
			cmpPayment.setDisable(true);
			cmpDeliveryType.setDisable(true);
			txtName.setDisable(true);
			txtDate.setDisable(false);
			txtPhone.setDisable(true);
			txtTime.setDisable(false);
			txtAddress.setDisable(true);

		} else if (DeliveryTypeOptions == "Delivery") {
			cmpPayment.setDisable(false);
			cmpDeliveryType.setDisable(false);
			txtName.setDisable(false);
			txtDate.setDisable(false);
			txtPhone.setDisable(false);
			txtTime.setDisable(false);
			txtAddress.setDisable(false);
		} else { // ***
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("You must choose type");
			lblMessage.setTextFill(Color.RED);
		}

	}

	@FXML
	public void getSelectedPaymentMethod() {
		OrderController.selectedTypeOfPayment = cmpPayment.getValue();
		AccountType = OrderController.selectedTypeOfPayment;
		setComboBox();

	}

	private void setComboBox() {

		if (AccountType == "Private account") {
			txtEmployeeName.setDisable(true);
			txtEmployeeCode.setDisable(true);
			DeliveryType.clear();
			DeliveryType.add("Regular");
			DeliveryType.add("Robot");
		} else if (AccountType == "Buisness account") {
			txtEmployeeName.setDisable(false);
			txtEmployeeCode.setDisable(false);
			DeliveryType.clear();
			DeliveryType.add("Regular");
			DeliveryType.add("Shared");
			DeliveryType.add("Robot");
		}

		DeliveryTypelist = FXCollections.observableArrayList(DeliveryType);
		cmpDeliveryType.setItems(DeliveryTypelist);
	}

	@FXML
	void getNextBtn(MouseEvent event) {
		Delivery delivery;
		boolean next = false;

		if (DeliveryTypeOptions == "Take-Away") {
			if (txtTime.getText().isEmpty() || txtDate.getValue().toString() == null) { // txtDate.getValue().toString()
				next = false;
			} else
				next = true;

		} else if (DeliveryTypeOptions == "Delivery") {
			OrderController.selecteDeliveryType = cmpDeliveryType.getValue();
//			System.out.println("1: " + OrderController.selectedTypeOfPayment + " " + OrderController.selectedTime + " "
//					+ OrderController.selectedDate + " " + OrderController.selectedCustomerName + " "
//					+ OrderController.selectedCustomerPhone + " " + OrderController.selectedAddress + " "
//					+ OrderController.selectedEmployeeName + " " + OrderController.selectedEmployeeCode);

			switch (AccountType) {
			case "Private account":

				if (cmpPayment.getValue().isEmpty() || txtTime.getText().isEmpty()
						|| txtDate.getValue().toString().isEmpty() || txtName.getText().isEmpty()
						|| txtPhone.getText().isEmpty() || txtAddress.getText().isEmpty()) {

					next = false;
				} else {

					next = true;
				}
				break;

			case "Buisness account":
				if (cmpPayment.getValue().isEmpty() || txtTime.getText().isEmpty()
						|| txtDate.getValue().toString().isEmpty() || txtName.getText().isEmpty()
						|| txtPhone.getText().isEmpty() || txtAddress.getText().isEmpty()
						|| txtEmployeeName.getText().isEmpty() || txtEmployeeCode.getText().isEmpty()) {
					next = false;
				} else {
					next = true;
				}

				break;

			default:
				next = false;
				break;
			}
		}

		System.out.println(" **** next is " + next);

		if (next) {

			if (DeliveryTypeOptions == "Delivery") {
//				System.out.println("this is delivery");
				delivery = new Delivery(String.valueOf(OrderController.deliveryNum), UserController.UserID,
						String.valueOf(OrderController.orderNumber), AccountType, DeliveryTypeOptions,
						txtTime.getText(), txtDate.getValue().toString(), txtName.getText(), txtPhone.getText(),
						txtAddress.getText(), txtEmployeeName.getText(), txtEmployeeCode.getText(), "1", "1", "1");
				delivery.setOrderNum(OrderController.orderNumber);

			} else {
//				System.out.println("this is Take away");
				delivery = new Delivery(String.valueOf(OrderController.deliveryNum), UserController.UserID,
						String.valueOf(OrderController.orderNumber), AccountType, DeliveryTypeOptions,
						txtTime.getText(), txtDate.getValue().toString());
				delivery.setOrderNum(OrderController.orderNumber);

			}

			OrderController.deliveryNum++;
			OrderController.deliveryList.add(0, delivery);
			System.out.println("OrderController.deliveryList" + OrderController.deliveryList);
//			if (OrderController.addDeliveryDetails(delivery)) {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerFinalOrderForm.fxml"));
			Parent root;
			try {
				root = loader.load();
				Scene scene = new Scene(root);
				UserController.currentStage.setTitle("Final order page");
				UserController.currentStage.setScene(scene);
			} catch (IOException e) {
				e.printStackTrace();
			}
//			} else {
//				lblMessage.setFont(new Font("Arial", 18));
//				lblMessage.setText("Error adding delivery details");
//				lblMessage.setTextFill(Color.RED);
//			}
		} else {
			lblMessage.setFont(new Font("Arial", 18));
			lblMessage.setText("You must fill all the fields");
			lblMessage.setTextFill(Color.RED);
		}
	}

	void geNextPage() {

	}

	@FXML
	void getBackBtn(MouseEvent event) {
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
	}

}
