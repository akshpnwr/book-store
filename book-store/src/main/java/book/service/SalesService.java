package book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.dao.SalesRepo;
import book.model.Sales;

@Service
public class SalesService
{
	@Autowired
	private SalesRepo repo;
	
	public void addSale(Sales sales)
	{
		repo.save(sales);
	}
	
	public List<Sales> getSales()
	{
		return repo.findAll();
	}
}
