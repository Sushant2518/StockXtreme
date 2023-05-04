package com.masai.entities;
import java.io.Serializable;

public class User implements Serializable {
	private String Username ;
	private String password;
	private String email;
	
	
	
	
	public User() {
		super();
	}

	public User(String usreanme, String password, String email) {
		super();
		this.Username = usreanme;
		this.password = password;
		this.email = email;
	}

	public String getUsername() {
		return Username;
	}

	public void setUserName(String usreanme) {
		this.Username = usreanme;
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

	@Override
	public String toString() {
		return "User [usreanme=" + Username + ", password=" + password + ", email=" + email + "]";
	}
	
}
