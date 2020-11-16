package book.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sales 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	@ManyToOne()
	private Book book;

	public Sales()
	{
	
	}
	
	public Sales(Book book) {
		this.book = book;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	
	
}
