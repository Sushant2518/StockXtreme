package com.masai.services;
import java.util.List;
import java.util.Map;

import com.masai.entities.Customer;
import com.masai.entities.Stock;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.StockException;
public interface CustomerSrevice {
	public boolean login(String email, String password, Map<String, Customer> customers) throws InvalidDetailsException;

	public void signUp(Customer cus, Map<String, Customer> customers) throws DuplicateDataException;

	public boolean buyProduct(int id, int qty, String email, Map<Integer, Stock> products,
			Map<String, Customer> customers, List<Transaction> transactions)
			throws InvalidDetailsException, StockException;

	public boolean addMoneyToWallet(double amount, String email, Map<String, Customer> customers);

	public double viewWalletBalance(String email, Map<String, Customer> customers);

	public Customer viewCustomerDetails(String email, Map<String, Customer> customers);

	public List<Customer> viewAllCustomers(Map<String, Customer> customers) throws StockException;
}
