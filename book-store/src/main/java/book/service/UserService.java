package book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import book.dao.UserRepo;
import book.model.User;

@Service
public class UserService
{
	@Autowired
	UserRepo repo;
	
	public void addUser(User user)
	{
		repo.save(user);
	}
	
	public List<User> getUsers()
	{
		return repo.findAll();
	}
	
}
