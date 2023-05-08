package com.masai;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.Customer;
import com.masai.entities.Stock;
import com.masai.entities.Transaction;
import com.masai.exceptions.DuplicateDataException;
import com.masai.exceptions.InvalidDetailsException;
import com.masai.exceptions.StockException;
import com.masai.exceptions.TransactionException;
import com.masai.services.CustomerServiceImpl;
import com.masai.services.CustomerSrevice;
import com.masai.services.StockService;
import com.masai.services.StockServiceImpl;
import com.masai.services.TransactionService;
import com.masai.services.TransactionServiceImpl;
import com.masai.utility.Admin;
import com.masai.utility.FileExists;
import com.masai.utility.IdGenration;

public class Main {
	private static void adminFunctionality(Scanner sc, Map<Integer, Stock> stock, Map<String, Customer> customers,
			List<Transaction> transactions) throws InvalidDetailsException, StockException, TransactionException {
		

		adminLogin(sc);

		StockService stockService = new StockServiceImpl();
		CustomerSrevice cusService = new CustomerServiceImpl();
		TransactionService trnsactionService = new TransactionServiceImpl();
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 add the stock");
				System.out.println("Press 2 view all the stock");
				System.out.println("Press 3 delete the stock");
				System.out.println("Press 4 update the stock");
				System.out.println("Press 5 view all customers");
				System.out.println("Press 6 to view all transactions");
				System.out.println("Press 7 to log out");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					String added = adminAddStock(sc, stock, stockService);
					System.out.println(added);
					break;
				case 2:

					adminViewAllStocks(stock, stockService);
					break;
				case 3:

					adminDeleteProduct(sc, stock, stockService);
					break;
				case 4:

					String upt = adminUpdateStock(sc, stock, stockService);
					System.out.println(upt);
					break;
				case 5:
					adminViewAllCustomers(customers, cusService);

					break;
				case 6:
					adminViewAllTransactions(transactions, trnsactionService);
					break;
				case 7:
					System.out.println("admin has successfully logout");
					break;
				default:
					throw new IllegalArgumentException("Unexpected value: " + choice);
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void adminLogin(Scanner sc) throws InvalidDetailsException {

		System.out.print("Enter Your User Name : ");
		String userName = sc.next();
		System.out.print("Enter Your Password : ");
		String password = sc.next();

		if (userName.equals(Admin.username) && password.equals(Admin.password)) {
			System.out.println(">>>>>>>>>> 𝙎𝙪𝙘𝙘𝙚𝙨𝙨𝙛𝙪𝙡𝙡𝙮 𝙇𝙤𝙜𝙞𝙣 <<<<<<<<<");
			System.out.println("***** 𝓦𝓮𝓵𝓬𝓸𝓶𝓮 𝓽𝓸 𝓼𝓽𝓸𝓬𝓴 𝓫𝓻𝓸𝓴𝓮𝓻 𝓐𝓭𝓶𝓲𝓷 *****");
		} else {
			throw new InvalidDetailsException(">>>>> 𝓘𝓷𝓿𝓪𝓵𝓲𝓭 𝓐𝓭𝓶𝓲𝓷 𝓒𝓻𝓮𝓭𝓮𝓷𝓽𝓲𝓪𝓵𝓼 <<<<<");
		}

	}

	public static String adminAddStock(Scanner sc, Map<Integer, Stock> stocks, StockService stockservice) {

		String str = null;

		System.out.println("please enter the Stock details");
		System.out.print("Enter the stock name : ");
		String name = sc.next();
		System.out.print("Enter the stock qty : ");
		int qty = sc.nextInt();
		System.out.print("Enter the stock price : ");
		double price = sc.nextDouble();
		System.out.print("Enter the stock category :");
		String cate = sc.next();

		Stock st = new Stock(IdGenration.genrateId(), name, qty, price, cate);
		str = stockservice.addStock(st, stocks);
		return str;
	}

	public static void adminViewAllStocks(Map<Integer, Stock> stocks, StockService stockservice) throws StockException {
		stockservice.viewAllStocks(stocks);
	}

	public static void adminDeleteProduct(Scanner sc, Map<Integer, Stock> stock, StockService stockservice)
			throws StockException {

		System.out.print("please enter the id of product to be deleted : ");
		int id = sc.nextInt();
		stockservice.deleteStock(id, stock);
	}

	public static String adminUpdateStock(Scanner sc, Map<Integer, Stock> products, StockService stockservice)
			throws StockException {
		String result = null;
		System.out.print("please enter the id of the stock which is to be updated : ");
		int id = sc.nextInt();
		System.out.println("Enter the stock details");

		System.out.print("Enter the stock name : ");
		String name = sc.next();

		System.out.print("Enter the stock qty : ");
		int qty = sc.nextInt();

		System.out.print("Enter the stock price : ");
		double price = sc.nextDouble();

		System.out.print("Enter the stock category : ");
		String cate = sc.next();

		Stock update = new Stock(id, name, qty, price, cate);

		result = stockservice.updateStock(id, update, products);
		return result;
	}

	public static void adminViewAllCustomers(Map<String, Customer> customers, CustomerSrevice cusService)
			throws StockException {
		List<Customer> list = cusService.viewAllCustomers(customers);

		for (Customer c : list) {
			System.out.println(c);
		}
	}

	public static void adminViewAllTransactions(List<Transaction> transactions, TransactionService trnsactionService)
			throws TransactionException {
		List<Transaction> allTransactions = trnsactionService.viewAllTransactions(transactions);

		for (Transaction tr : allTransactions) {
			System.out.println(tr);
		}

	}
	
	// Customer 
	
	public static void customerFunctionality(Scanner sc, Map<String, Customer> customers,
			Map<Integer, Stock> products, List<Transaction> transactions)
			throws InvalidDetailsException, TransactionException {

		CustomerSrevice cusService = new CustomerServiceImpl();
		StockService stockService = new StockServiceImpl();
		TransactionService trnsactionService = new TransactionServiceImpl();

		// Customer login
		System.out.println("please enter the following details to login");
		System.out.print("please enter the email : ");
		String email = sc.next();
		System.out.print("Enter the password : ");
		String pass = sc.next();
		customerLogin(email,pass, customers, cusService);

		try {
			int choice = 0;
			do {
				System.out.println("Select the option of your choice");
				System.out.println("Press 1 to view all stock");
				System.out.println("Press 2 to buy a stock");
				System.out.println("Press 3 to add money to a wallet");
				System.out.println("Press 4 view wallet balance");
				System.out.println("Press 5 view my details");
				System.out.println("Press 6 view my transactions");
				System.out.println("Press 7 to logout");
				choice = sc.nextInt();

				switch (choice) {
				case 1:
					customerViewAllProducts(products, stockService);
					break;
				case 2:
					String result = customerBuyProduct(sc, email, products, customers, transactions, cusService);
					System.out.println(result);
					break;
				case 3:
					String moneyAdded = customerAddMoneyToWallet(sc, email, customers, cusService);
					System.out.println(moneyAdded);
					break;
				case 4:
					double walletBalance = customerViewWalletBalance(email, customers, cusService);
					System.out.println("Wallet balance is: " + walletBalance);
					break;
				case 5:
					customerViewMyDetails(email, customers, cusService);
					break;
				case 6:
					customerViewCustomerTransactions(email, transactions, trnsactionService);
					break;
				case 7:
					System.out.println("***** 𝔂𝓸𝓾 𝓱𝓪𝓿𝓮 𝓼𝓾𝓬𝓬𝓮𝓼𝓼𝓼𝓯𝓾𝓵𝓵𝔂 𝓵𝓸𝓰𝓸𝓾𝓽 *****");
					break;
				default:
					System.out.println(">>>>> invalid choice <<<<<");
					break;
				}

			} while (choice <= 6);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	public static void customerSignup(Scanner sc, Map<String, Customer> customers) throws DuplicateDataException {
		System.out.println("please enter the following details to Signup");
		System.out.print("please enter the user name : ");
		String name = sc.next();
		System.out.print("Enter the password : ");
		String pass = sc.next();
		System.out.print("Enter the email id : ");
		String email = sc.next();
		System.out.print("Enter the balance to be added into the wallet : ");
		double balance = sc.nextDouble();
		Customer cus = new Customer(balance, name, pass, email);

		CustomerSrevice cusService = new CustomerServiceImpl();
		cusService.signUp(cus, customers);
		System.out.println("***** 𝓬𝓾𝓼𝓽𝓸𝓶𝓮𝓻 𝓱𝓪𝓼 𝓢𝓾𝓬𝓬𝓮𝓯𝓾𝓵𝓵𝔂 𝓼𝓲𝓰𝓷 𝓾𝓹 *****");

	}

	public static void customerLogin(String email,String pass, Map<String, Customer> customers, CustomerSrevice cusService)
			throws InvalidDetailsException {
		cusService.login(email, pass,customers);
		System.out.println("***** 𝓒𝓾𝓼𝓽𝓸𝓶𝓮𝓻 𝓱𝓪𝓼 𝓼𝓾𝓬𝓬𝓮𝓼𝓼𝓯𝓾𝓵𝓵𝔂 𝓵𝓸𝓰𝓲𝓷 *****");

	}

	public static void customerViewAllProducts(Map<Integer, Stock> stock, StockService stockService)
			throws StockException {
		stockService.viewAllStocks(stock);
	}

	public static String customerBuyProduct(Scanner sc, String email, Map<Integer, Stock> stock,
			Map<String, Customer> customers, List<Transaction> transactions, CustomerSrevice cusService)
			throws InvalidDetailsException, StockException {
		System.out.print("Enter the product id : ");
		int id = sc.nextInt();
		System.out.print("enter the quantity you want to buy : ");
		int qty = sc.nextInt();
		cusService.buyProduct(id, qty, email, stock, customers, transactions);

		return "***** 𝓨𝓸𝓾 𝓱𝓪𝓿𝓮 𝓼𝓾𝓬𝓬𝓮𝓼𝓼𝓯𝓾𝓵𝓵𝔂 𝓫𝓸𝓾𝓰𝓱𝓽 𝓽𝓱𝓮 𝓼𝓽𝓸𝓬𝓴 *****";

	}

	public static String customerAddMoneyToWallet(Scanner sc, String email, Map<String, Customer> customers,
			CustomerSrevice cusService) {
		System.out.print("please enter the amount : ");
		double money = sc.nextDouble();
		boolean added = cusService.addMoneyToWallet(money, email, customers);

		return "***** 𝓐𝓶𝓸𝓾𝓷𝓽: " + money + " 𝓼𝓾𝓬𝓬𝓮𝓼𝓼𝓯𝓾𝓵𝓵𝔂 𝓪𝓭𝓭𝓮𝓭 𝓽𝓸 𝔂𝓸𝓾𝓻 𝔀𝓪𝓵𝓵𝓮𝓽 *****";
	}

	public static double customerViewWalletBalance(String email, Map<String, Customer> customers,
			CustomerSrevice cusService) {
		double walletBalance = cusService.viewWalletBalance(email, customers);
		return walletBalance;
	}

	public static void customerViewMyDetails(String email, Map<String, Customer> customers,
			CustomerSrevice cusService) {
		Customer cus = cusService.viewCustomerDetails(email, customers);
		System.out.println("name : " + cus.getUsername());
		System.out.println("email : " + cus.getEmail());
		System.out.println("wallet balance : " + cus.getWalletBalance());
	}

	public static void customerViewCustomerTransactions(String email, List<Transaction> transactions,
			TransactionService trnsactionService) throws TransactionException {
		List<Transaction> myTransactions = trnsactionService.viewCustomerTransactions(email, transactions);

		for (Transaction tr : myTransactions) {
			System.out.println(tr);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Integer, Stock> products = FileExists.stockFile();
		Map<String, Customer> customers = FileExists.customerFile();
		List<Transaction> transactions = FileExists.transactionFile();


		Scanner sc = new Scanner(System.in);

		System.out.println("\n***** 𝓦𝓮𝓵𝓬𝓸𝓶𝓮 , 𝓲𝓷 𝓢𝓽𝓸𝓬𝓴 𝓑𝓻𝓸𝓴𝓮𝓻 𝓜𝓪𝓷𝓪𝓰𝓶𝓮𝓷𝓽 𝓢𝔂𝓼𝓽𝓮𝓶 *****\n");

		try {

			int choice = 0;
			do {
				System.out.println("***** Please enter your choice *****\n\n" + " 1 => Admin login \n 2 => Customer login \n"
				+ " 3 => for Customer signup \n 0 => for exit");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					adminFunctionality(sc, products, customers, transactions);
					break;
				case 2:
					customerFunctionality(sc, customers, products, transactions);
					break;

				case 3:
					customerSignup(sc, customers);
					break;

				case 0:
					System.out.println("***** 𝓼𝓾𝓬𝓬𝓮𝓼𝓼𝓯𝓾𝓵𝓵𝔂 𝓮𝔁𝓲𝓼𝓽𝓮𝓭 𝓯𝓻𝓸𝓶 𝓽𝓱𝓮 𝓼𝔂𝓼𝓽𝓮𝓶 *****");

					break;

				default:
					throw new IllegalArgumentException(">>>>> Invalid Selection <<<<<");
				}

			}

			while (choice != 0);

		} catch (Exception e) {

			System.out.println(e.getMessage());
		} finally {
			// serialization (finally always executed)
			try {
				ObjectOutputStream poos = new ObjectOutputStream(new FileOutputStream("Product.ser"));
				poos.writeObject(products);
				ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("Customer.ser"));
				coos.writeObject(customers);

				ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream("Transactions.ser"));
				toos.writeObject(transactions);
			
			} catch (Exception e) {
				
				System.out.println(e.getMessage());
			}
		}
	}

}
