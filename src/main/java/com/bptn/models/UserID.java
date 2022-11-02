package com.bptn.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"UserID\"")
public class UserID {

	@Column(name = "\"name\"")
	private String name;

	@Column(name = "\"emailID\"")
	private String emailID;

	@Column(name = "\"phoneNumber\"")
	private String phoneNumber;

	@Column(name = "\"userPassword\"")
	private String userPassword;

	@Id
	@Column(name = "\"username\"")
	private String username;

	public UserID() {
		super();
	}

	public UserID(String name, String emailID, String phoneNumber, String userPassword, String username) {
		super();
		this.name = name;
		this.emailID = emailID;
		this.phoneNumber = phoneNumber;
		this.userPassword = userPassword;
		this.username = username;
	}

//	getter for name
	public String getName() {
		return this.name;
	}

//	setter for name
	public void setName(String name) {
		this.name = name;
	}

//	getter for emailId
	public String getEmailID() {
		return this.emailID;
	}

//	setter for emailID
	public void setemailID(String emailID) {
		this.emailID = emailID;
	}

//	getter for phoneNumber
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

//	setter for phoneNumber
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// getter for userPassword
	public String getUserPassword() {
		return this.userPassword;
	}

//	setter for phoneNumber
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	// getter for username
		public String getUsername() {
			return this.username;
		}

//		setter for username
		public void setUsername(String username) {
			this.username = username;
		}
	
	
	
	
	

}
