package book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.model.Sale;


public interface SaleRepo extends JpaRepository<Sale, Integer>
{

}
