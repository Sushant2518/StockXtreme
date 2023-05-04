package com.masai.entities;
import java.io.Serializable;
import java.time.LocalDate;

public class Transaction {
	private String username;
	private String email;
	private int stockId;
	private String stockName;
	private int qty;
	private double price;
	private double total;
	private LocalDate dt;
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Transaction(String username, String email, int stockId, String stockName, int qty, double price,
			double total, LocalDate dt) {
		super();
		this.username = username;
		this.email = email;
		this.stockId = stockId;
		this.stockName = stockName;
		this.qty = qty;
		this.price = price;
		this.total = total;
		this.dt = dt;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public LocalDate getDt() {
		return dt;
	}

	public void setDt(LocalDate dt) {
		this.dt = dt;
	}

	@Override
	public String toString() {
		return "Transaction [username=" + username + ", email=" + email + ", stockId=" + stockId + ", stockName="
				+ stockName + ", qty=" + qty + ", price=" + price + ", total=" + total + ", dt=" + dt + "]";
	}
	
	
	
}
