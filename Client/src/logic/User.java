package logic;

import java.io.Serializable;

/**
 * represents a User in the system
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String iD;
	private String W4C;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String Authorization;
	private String AccountType;
	private String phone;
	private String status;
	private String employeeName;
	private String companyCode;
	private String creditCard;

	public User(String iD, String W4C, String userName, String password, String firstName, String lastName,
			String email, String Authorization, String AccountType, String phone, String status, String employeeName,
			String companyCode, String creditCard) {
		this.iD=iD;
		this.W4C=W4C;
		this.userName=userName;
		this.password=password;
		this.firstName=firstName;
		this.lastName=lastName;
		this.email=email;
		this.Authorization=Authorization;
		this.AccountType=AccountType;
		this.phone=phone;
		this.status=status;
		this.employeeName=employeeName;
		this.companyCode=companyCode;
		this.creditCard=creditCard;
	}

	public User(String iD, String w4c, String userName, String password, String firstName, String lastName,
			String email, String authorization, String accountType, String phone, String status) {
		super();
		this.iD = iD;
		W4C = w4c;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		Authorization = authorization;
		AccountType = accountType;
		this.phone = phone;
		this.status = status;
	}

	/**
	 * constructor
	 * 
	 * @param username account
	 * @param password for the username account
	 * @param userID
	 */
	public User(String username, String password) {
		this.userName = username;
		this.password = password;

	}

	// private account
	public User(String userID, String firstName, String lastName, String phone, String email, String autorization,
			String accountType, String creditCard) {
		iD = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		Authorization = autorization;
		AccountType = accountType;
		this.creditCard = creditCard;

	}

	// buisness account
	public User(String userID, String firstName, String lastName, String phone, String email, String autorization,
			String accountType, String employeeName, String comapanyCode) {
		iD = userID;
		userName = firstName;
		password = firstName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		Authorization = autorization;
		AccountType = accountType;
		this.employeeName = employeeName;
		this.companyCode = comapanyCode;

	}

	public String getiD() {
		return iD;
	}

	public void setiD(String iD) {
		this.iD = iD;
	}

	public String getW4C() {
		return W4C;
	}

	public void setW4C(String w4c) {
		W4C = w4c;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthorization() {
		return Authorization;
	}

	public void setAuthorization(String authorization) {
		Authorization = authorization;
	}

	public String getAccountType() {
		return AccountType;
	}

	public void setAccountType(String accountType) {
		AccountType = accountType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	@Override
	public String toString() {
		return "User [iD=" + iD + ", W4C=" + W4C + ", userName=" + userName + ", password=" + password + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", Authorization=" + Authorization
				+ ", AccountType=" + AccountType + ", phone=" + phone + ", status=" + status + ", employeeName="
				+ employeeName + ", companyCode=" + companyCode + ", creditCard=" + creditCard + "]";
	}

}
