package com.masai.entities;
import java.io.Serializable;

public class Customer extends User implements Serializable{
private double walletBalance;
	
	

	public Customer(double walletBalance,String username,String password,String email) {
		super(username, password, email);
		this.walletBalance = walletBalance;
	}



	public double getWalletBalance() {
		return walletBalance;
	}



	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}



	@Override
	public String toString() {
		return "Customer [walletBalance=" + walletBalance + ", getWalletBalance()=" + getWalletBalance()
				+ ", getUsreanme()=" + getUsername() + ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
	
}
