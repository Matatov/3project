package logic;

import java.io.Serializable;

public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;

	/** the Restaurant Name */
	private String Name;

	/** the Restaurant's Address */
	private String Category;

	/** the BMMenagerID */
	private String Price;
	
	private String Size;

	
	public Menu(String name, String category, String price, String size) {
		super();
		Name = name;
		Category = category;
		Price = price;
		Size = size;
	}
	
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCategory() {
		return Category;
	}

	public void setCategory(String category) {
		Category = category;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		Price = price;
	}

	public String getSize() {
		return Size;
	}

	public void setSize(String size) {
		Size = size;
	}


	@Override
	public String toString() {
		return String.format("%s %s %s %s \n", Name, Category, Price, Size);
//		return "Menu [Name=" + Name + ", Category=" + Category + ", Price=" + Price + ", Size=" + Size + "]";
	}



	

}
