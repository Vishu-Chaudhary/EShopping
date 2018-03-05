package com.sapient.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class UserDetails {
	@Id
	@NotEmpty(message="User Name cannot be  empty")
	@Size(min=3, max=20, message="user name must be 3-20 characters")
	@Pattern(regexp="[a-zA-Z ]+", message="User Name Must Contains Only Alphabets")
	private String userName;
	
	@NotEmpty(message="Password cannot be  empty")
	@Size(min=4, message="Password must be minimum 4 characters")
	private String pwd;
	
	private String role="user";
	
	public UserDetails(String userName, String pwd) {
		super();
		this.userName = userName;
		this.pwd = pwd;
	}
	
	public UserDetails() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRole() {
		return role;
	}
	
	
}
