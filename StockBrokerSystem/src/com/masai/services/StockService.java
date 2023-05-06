package com.masai.services;
import com.masai.exceptions.StockException;
import java.util.Map;
import com.masai.entities.Stock;
public interface StockService {
	public String addStock(Stock st,Map<Integer,Stock> stocks);
	
	public void viewAllStocks(Map<Integer,Stock> stocks) throws StockException;
	
	public void deleteStock(int id, Map<Integer, Stock> stocks) throws StockException;

	public String updateStock(int id, Stock st, Map<Integer, Stock> stocks) throws StockException;
}
