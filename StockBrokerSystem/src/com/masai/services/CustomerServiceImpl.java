package com.masai.services;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.masai.entities.Stock;
import com.masai.entities.Customer;
import com.masai.entities.Transaction;

import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.StockException;

public class CustomerServiceImpl implements CustomerSrevice{
	@Override
	public boolean login(String email, String password, Map<String, Customer> customers)
			throws InvalidDetailsException {
		if (customers.containsKey(email)) {

			if (customers.get(email).getPassword().equals(password)) {
				return true;
			} else {
				throw new InvalidDetailsException("Invalid Credentials");
			}

		} else {
			throw new InvalidDetailsException("you have not sign up yet, please signup");
		}
	}

	@Override
	public void signUp(Customer cus, Map<String, Customer> customers) throws DuplicateDataException {
		// TODO Auto-generated method stub
		if (customers.containsKey(cus.getEmail())) {
			throw new DuplicateDataException("Customer already exists , please login");
		} else {

			customers.put(cus.getEmail(), cus);

		}

	}

	@Override
	public boolean buyProduct(int id, int qty, String email, Map<Integer, Stock> products,
			Map<String, Customer> customers, List<Transaction> transactions)
			throws InvalidDetailsException, StockException {
		// TODO Auto-generated method stub
		if (products.size() == 0)
			throw new StockException("Stock list is empty");

		if (products.containsKey(id)) {

			Stock st = products.get(id);

			if (st.getQty() >= qty) {

				Customer cus = customers.get(email);

				double buyingPrice = qty * st.getPrice();

				if (cus.getWalletBalance() >= buyingPrice) {
					cus.setWalletBalance(cus.getWalletBalance() - buyingPrice);

					st.setQty(st.getQty() - qty);

					products.put(id, st);

					Transaction tr = new Transaction(cus.getUsername(), email, id,st.getName(), qty, st.getPrice(),
							st.getPrice() * qty, LocalDate.now());

					transactions.add(tr);

				} else {
					throw new InvalidDetailsException("wallet balance is not sufficient");
				}

			} else {
				throw new InvalidDetailsException("stock quantity is not suffiecient");
			}

		} else {
			throw new InvalidDetailsException("stock not available with id: " + id);
		}

		return false;
	}

	@Override
	public boolean addMoneyToWallet(double amount, String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub
		Customer cus = customers.get(email);

		cus.setWalletBalance(cus.getWalletBalance() + amount);

		customers.put(email, cus);

		return true;
	}

	@Override
	public double viewWalletBalance(String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub
		Customer cus = customers.get(email);

		return cus.getWalletBalance();
	}

	@Override
	public Customer viewCustomerDetails(String email, Map<String, Customer> customers) {
		// TODO Auto-generated method stub
		if (customers.containsKey(email)) {

			return customers.get(email);

		}

		return null;
	}

	@Override
	public List<Customer> viewAllCustomers(Map<String, Customer> customers) throws StockException {
		// TODO Auto-generated method stub
		List<Customer> list = null;

		if (customers != null && customers.size() > 0) {
			Collection<Customer> coll = customers.values();
			list = new ArrayList<>(coll);
		} else {
			throw new StockException("Customer list is empty");
		}

		return list;
	}
}
