package book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.dao.SaleRepo;
import book.model.Sale;

@Service
public class SaleService
{
	@Autowired
	private SaleRepo repo;
	
	public void addSale(Sale sale)
	{
		repo.save(sale);
	}
	
	public List<Sale> getSale()
	{
		return repo.findAll();
	}
}
