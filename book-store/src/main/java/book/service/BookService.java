package book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.dao.BookRepo;
import book.model.Book;

@Service
public class BookService 
{
	@Autowired
	BookRepo repo;
	
	public void addBook(Book book)
	{
		repo.save(book);
	}
	
	public List<Book> getBooks()
	{
		return repo.findAll();
	}
	
	public Book getBook(String name)
	{
		return repo.findByName(name);
	}
	
	public void deleteBook(String name)
	{
		repo.deleteById(name);
	}
}
