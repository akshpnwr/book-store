package book.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import book.model.Sales;
import book.model.User;
import book.service.BookService;
import book.service.SalesService;
import book.service.UserService;

@Controller
public class BookController
{
	@Autowired
	UserService userService;
	
	@Autowired
	BookService bookService;
	
	@Autowired
	SalesService salesService;
	
	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@RequestMapping("/home")
	public String home()
	{
		return "home";
	}
	
	@RequestMapping("/register")
	public String register()
	{
		return "register";
	}
	
	@GetMapping("/cart")
	public String newFile()
	{
		return "cart";
	}
	
	@PostMapping("/registerUser")
	public String registerUser(@RequestParam("firstName") String firstName,@RequestParam("lastName") String lastName,
			@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("mobile") String mobile)
	{
		userService.addUser(new User(firstName,lastName,username,password,mobile));
		return "login";
	}
	
	@RequestMapping("/cart/{name}")
	public ModelAndView cart(@PathVariable("name") String name)
	{
		
		
		ModelAndView mv = new ModelAndView("cart");
		
		mv.addObject("book",bookService.getBook(name));
		
		return mv;
	}
	
	
	
	@RequestMapping("/buy/{name}")
	public String buyBook(@PathVariable("name") String name)
	{
		
		
		salesService.addSale(new Sales(bookService.getBook(name)));
		return "test";
	}

}
