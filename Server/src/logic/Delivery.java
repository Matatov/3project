package logic;

import java.io.Serializable;

public class Delivery implements Serializable {

	private static final long serialVersionUID = 1L;

	private String DeliveryNum;
	private String CustomerID;
	private String OrderNumber;
	private String TypeOfPayment;
	private String TypeOfDelivery;
	private String Time;
	private String Date;
	private String CustomerName;
	private String PhoneNum;
	private String Address;
	private String EmployeeName;
	private String EmployeeCode;
	private String ShipmentTime;
	private String Price;
	private String BasePrice;
	
	private int orderNum = 0;

	public Delivery(String deliveryNum, String customerID, String orderNumber, String typeOfPayment,
			String typeOfDelivery, String time, String date, String customerName, String phoneNum, String address,
			String employeeName, String employeeCode, String shipmentTime, String price, String basePrice) {
		super();
		DeliveryNum = deliveryNum;
		CustomerID = customerID;
		OrderNumber = orderNumber;
		TypeOfPayment = typeOfPayment;
		TypeOfDelivery = typeOfDelivery;
		Time = time;
		Date = date;
		CustomerName = customerName;
		PhoneNum = phoneNum;
		Address = address;
		EmployeeName = employeeName;
		EmployeeCode = employeeCode;
		ShipmentTime = shipmentTime;
		Price = price;
		BasePrice = basePrice;
	}

	
	public String getOrderNum() {
		return String.valueOf(orderNum);
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	
	public String getDeliveryNum() {
		return DeliveryNum;
	}

	public void setDeliveryNum(String deliveryNum) {
		DeliveryNum = deliveryNum;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getOrderNumber() {
		return OrderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		OrderNumber = orderNumber;
	}

	public String getTypeOfPayment() {
		return TypeOfPayment;
	}

	public void setTypeOfPayment(String typeOfPayment) {
		TypeOfPayment = typeOfPayment;
	}

	public String getTypeOfDelivery() {
		return TypeOfDelivery;
	}

	public void setTypeOfDelivery(String typeOfDelivery) {
		TypeOfDelivery = typeOfDelivery;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public String getPhoneNum() {
		return PhoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		PhoneNum = phoneNum;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public String getEmployeeCode() {
		return EmployeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		EmployeeCode = employeeCode;
	}

	public String getShipmentTime() {
		return ShipmentTime;
	}

	public void setShipmentTime(String shipmentTime) {
		ShipmentTime = shipmentTime;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getBasePrice() {
		return BasePrice;
	}

	public void setBasePrice(String basePrice) {
		BasePrice = basePrice;
	}

	@Override
	public String toString() {
		return "Delivery [DeliveryNum=" + DeliveryNum + ", CustomerID=" + CustomerID + ", OrderNumber=" + OrderNumber
				+ ", TypeOfPayment=" + TypeOfPayment + ", TypeOfDelivery=" + TypeOfDelivery + ", Time=" + Time
				+ ", Date=" + Date + ", CustomerName=" + CustomerName + ", PhoneNum=" + PhoneNum + ", Address="
				+ Address + ", EmployeeName=" + EmployeeName + ", EmployeeCode=" + EmployeeCode + ", ShipmentTime="
				+ ShipmentTime + ", Price=" + Price + ", BasePrice=" + BasePrice + "]";
	}

	
	
}
