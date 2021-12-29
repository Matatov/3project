package logic;

import java.io.Serializable;

public class Employer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String employerCode;
	private String employerName;
	
	public Employer(String employerCode, String employerName) {
		this.employerCode = employerCode;
		this.employerName = employerName;
	}
	
	public Employer() {
		
	}
	
	public void setEmployerCode(String employerCode) {
		this.employerCode = employerCode;
	}
	
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}
	
	public String getEmployerCode() {
		return employerCode;
	}
	
	public String getEmployerName() {
		return employerName;
	}
}
