package VO;

import java.io.Serializable;

public class SellerVO implements Serializable{
	private long seller_id;
	private String companyname;
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String email;
	private String phNo;
	private String dob;
	private String address;
	private String zip;
	private String routingNumber;
	private String accountNumber;
	private String paypal;
	private int status;
	
	public SellerVO() {}

	public SellerVO(long seller_id){
		this.seller_id = seller_id;
	}
	
	public SellerVO(String companyname, String firstname, String lastname, String username, String password, 
			String email, String phNo, String dob, String address, String zip, String routingNumber, String accountNumber, String paypal, int status){
		this.companyname = companyname;
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phNo = phNo;
		this.dob = dob;
		this.address = address;
		this.zip = zip;
		this.routingNumber = routingNumber;
		this.accountNumber = accountNumber;
		this.paypal = paypal;
		this.status = status;
	}
	
	public long getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(long seller_id) {
		this.seller_id = seller_id;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhNo() {
		return phNo;
	}
	public void setPhNo(String phNo) {
		this.phNo = phNo;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getRoutingNumber() {
		return routingNumber;
	}
	public void setRoutingNumber(String routingNumber) {
		this.routingNumber = routingNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getPaypal() {
		return paypal;
	}
	public void setPaypal(String paypal) {
		this.paypal = paypal;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
};
