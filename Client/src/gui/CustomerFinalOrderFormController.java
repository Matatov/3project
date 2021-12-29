package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import logic.Menu;
import logic.Order;
import logic.Restaurant;

public class CustomerFinalOrderFormController implements Initializable {
	ArrayList<Order> finalOrdersList = new ArrayList<Order>();

	@FXML
	private Button BtnPay;

	@FXML
	private Button BtnBack;

	@FXML
	private Label lblSubtotal;
	@FXML
	private Label lblShipping;
	@FXML
	private Label lblDiscount;
	@FXML
	private Label lblTotalPrice;

	@FXML
	private TableView<Order> SummaryTable = new TableView<Order>();
	@FXML
	private TableColumn<Order, String> mealColumn;
	@FXML
	private TableColumn<Order, String> quantityColumn;
	@FXML
	private TableColumn<Order, String> priceColumn;

	ArrayList<String> getOrderDetailsItems = new ArrayList<String>(); // UserID,OrderNum , MealNum

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		this.lblSubtotal.setFont(new Font("Arial", 13));
		this.lblSubtotal.setText(String.valueOf(OrderController.totalPrice));

		LocalTime time = LocalTime.now();
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
	
		
		System.out.println("time " + time);
		OrderController.currentDate = LocalDate.now(); // time of order // LocalDate.now();

		System.out.println("OrderController.currentDate " + OrderController.currentDate);

		if (OrderController.selectedType == "Take-Away") {
			this.lblShipping.setFont(new Font("Arial", 13));
			this.lblShipping.setText("0");

		} else {
			switch (OrderController.selecteDeliveryType) {

			case "Regular":
				this.lblShipping.setFont(new Font("Arial", 13));
				this.lblShipping.setText("25");
				OrderController.totalPrice += 25;
				break;

			case "Shared":
				this.lblShipping.setFont(new Font("Arial", 13));
				this.lblShipping.setText("25");
				OrderController.totalPrice += 25;
				break;
			//
			case "Robot":
				this.lblShipping.setFont(new Font("Arial", 13));
				this.lblShipping.setText("0");
				OrderController.totalPrice += 0;
				break;

			}
			
			
			
			

//			OrderController.currentDate = LocalDate.now(); // time of order
//			
//			System.out.println("OrderController.currentDate"+OrderController.currentDate);

//			Date date1=(Date) new SimpleDateFormat("dd/MM/yyyy").parse(OrderController.deliveryList.get(0).getTime());  
//			OrderController.PickTime = LocalDate.parse(OrderController.deliveryList.get(0).getTime());

//			long diffInMinutes = java.time.Duration.between(OrderController.currentDate, OrderController.PickTime).toMinutes();

		}

		// NEED TO CALCULATE TOTAL PRICE BY DISCOUNTS

		this.lblTotalPrice.setFont(new Font("Arial", 20));
		this.lblTotalPrice.setText(String.valueOf(OrderController.totalPrice));
		getOrderDetailsItems.add(0, UserController.UserID);
		getOrderDetailsItems.add(1, String.valueOf(OrderController.orderNumber));
		System.out.println("Customer Final Order Form Controller");

		System.out.println("Order Controller orders before: " + OrderController.orders);

//		ArrayList<Order> finalOrdersList = OrderController.getOrderDetails(getOrderDetailsItems);
		finalOrdersList = OrderController.ordersList; // **
		System.out.println("Order Controller orders after: " + OrderController.orders);
//
//		System.out.println("initialize ordersList is "+ ordersList1);
//		
		for (int i = 0; i < finalOrdersList.size(); i++) { // set quantity for each meal
			String Key = finalOrdersList.get(i).getMeal();
			Integer value = OrderController.mealsQuantity.get(Key);

			finalOrdersList.get(i).setQuantity(String.valueOf(value));
		}
//
		ObservableList<Order> data = FXCollections.observableArrayList(finalOrdersList);
//
		mealColumn.setCellValueFactory(new PropertyValueFactory<>("Meal"));
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<>("MealPrice"));

		SummaryTable.setItems(data);
	}

	@FXML
	void getPayBtn(MouseEvent event) { // FIX: JUST WHEN CLICK PAY SET STATUS HOLD

		for (int i = 0; i < finalOrdersList.size(); i++) {
			boolean flag = OrderController.addOrder(finalOrdersList.get(i));
			System.out.println("pay flag " + flag);
		}

		boolean flag1 = OrderController.addDeliveryDetails(OrderController.deliveryList.get(0));
		System.out.println("pay flag1 " + flag1);

		OrderController.mealNum = 0; // reset meals number for new order
		OrderController.totalPrice = 0; // reset for next order
		OrderController.mealsQuantity.clear(); // reset HashMap
		OrderController.ordersList.clear();
		OrderController.deliveryList.clear();
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

	@FXML
	void getBackBtn(MouseEvent event) { // FIX; IF CLICK BACK CORRECT THE DATA ON DB
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CustomerDeliveryForm.fxml"));
		Parent root;
		try {
			root = loader.load();
			Scene scene = new Scene(root);
			UserController.currentStage.setTitle("Delivery details");
			UserController.currentStage.setScene(scene);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
//	public class Time {
//
//	    int seconds;
//	    int minutes;
//	    int hours;
//
//	    public Time(int hours, int minutes, int seconds) {
//	        this.hours = hours;
//	        this.minutes = minutes;
//	        this.seconds = seconds;
//	    }
//
//	    public void main(String[] args) {
//
//	      // create objects of Time class
//	        Time start = new Time(8, 12, 15);
//	        Time stop = new Time(12, 34, 55);
//	        Time diff;
//
//	        // call difference method
//	        diff = difference(start, stop);
//
//	        System.out.printf("TIME DIFFERENCE: %d:%d:%d - ", start.hours, start.minutes, start.seconds);
//	        System.out.printf("%d:%d:%d ", stop.hours, stop.minutes, stop.seconds);
//	        System.out.printf("= %d:%d:%d\n", diff.hours, diff.minutes, diff.seconds);
//	    }
//
//	    public Time difference(Time start, Time stop)
//	    {
//	        Time diff = new Time(0, 0, 0);
//
//	        // if start second is greater
//	        // convert minute of stop into seconds
//	        // and add seconds to stop second
//	        if(start.seconds > stop.seconds){
//	            --stop.minutes;
//	            stop.seconds += 60;
//	        }
//
//	        diff.seconds = stop.seconds - start.seconds;
//
//	        // if start minute is greater
//	        // convert stop hour into minutes
//	        // and add minutes to stop minutes
//	        if(start.minutes > stop.minutes){
//	            --stop.hours;
//	            stop.minutes += 60;
//	        }
//
//	        diff.minutes = stop.minutes - start.minutes;
//	        diff.hours = stop.hours - start.hours;
//
//	        // return the difference time
//	        return(diff);
//	    }
//	}
	
	
	
	
}
