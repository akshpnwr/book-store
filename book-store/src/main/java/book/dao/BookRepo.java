package book.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import book.model.Book;

public interface BookRepo extends JpaRepository<Book,String>
{
	public Book findByName(String name);
	

}
