package book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.model.Sales;

public interface SalesRepo extends JpaRepository<Sales, Integer>
{

}
