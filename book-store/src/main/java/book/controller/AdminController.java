package book.controller;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import book.model.Book;
import book.model.Sales;
import book.service.BookService;
import book.service.SalesService;
import book.service.UserService;


@Controller
public class AdminController
{
	@Autowired
	BookService bookService;
	
	@Autowired
	UserService userService;
	
	@Autowired 
	SalesService salesService;
	
	public static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/uploads";
	
	@GetMapping("/add")
	public String addBook()
	{
		return "addBook";
	}
	
	@GetMapping("/delete")
	public ModelAndView delete()
	{
		ModelAndView mv=new ModelAndView("delete");
		
		mv.addObject("books",bookService.getBooks());

		return mv;
	}
	
	@RequestMapping("/delete/{name}")
	public String deleteBook(@PathVariable("name") String name)
	{
		bookService.deleteBook(name);
		
		return "home";
	}
	
	@GetMapping("/sales")
	public ModelAndView getSales()
	{
		List<Sales> sales=salesService.getSales();
		int totalSale=0;
		for(Sales sale: sales)
		{
			totalSale=totalSale+sale.getBook().getPrice();
		}		
		
		ModelAndView mv=new ModelAndView("sales");
		mv.addObject("sales",salesService.getSales());
		mv.addObject("totalSale",totalSale);
		return mv;
	}
	
	@GetMapping("/users")
	public ModelAndView getUsers()
	{
		ModelAndView mv=new ModelAndView("users");
		
		mv.addObject("users",userService.getUsers());
		
		return mv;
	}
	
	@PostMapping("/upload")
	public String upload(Model model,@RequestParam("name") String name,@RequestParam("price") int price,@RequestParam("author") String author,@RequestParam("file") MultipartFile file)
	{
		StringBuilder fileName = new StringBuilder();
		  Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
		  fileName.append(file.getOriginalFilename());
		  try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		  
		  
		  bookService.addBook(new Book(name,price,author,fileName.toString()));
		  
		  
		  model.addAttribute("msg", "Successfully uploaded files "+fileName.toString());
		  return "uploadStatus";
	}
	
	@RequestMapping("/books")
	public ModelAndView getBook()
	{
		ModelAndView mv=new ModelAndView("books");
		
		mv.addObject("books",bookService.getBooks());

		return mv;
	}
	
}
