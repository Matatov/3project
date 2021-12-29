package logic;

import java.io.Serializable;

public class Restaurant implements Serializable {

	private static final long serialVersionUID = 1L;

	/** the Restaurant Name */
	private String Name;

	/** the Restaurant's Address */
	private String Address;

	/** the BMMenagerID */
	private String BMMenagerID;

	public Restaurant(String name, String address, String bMMenagerID) {
		super();
		Name = name;
		Address = address;
		BMMenagerID = bMMenagerID;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getBMMenagerID() {
		return BMMenagerID;
	}

	public void setBMMenagerID(String bMMenagerID) {
		BMMenagerID = bMMenagerID;
	}

	@Override
	public String toString() {
		return String.format("%s %s %s \n", Name, Address, BMMenagerID);
//		return "Restaurant [Name=" + Name + ", Address=" + Address + ", BMMenagerID=" + BMMenagerID + "]";
	}

}
