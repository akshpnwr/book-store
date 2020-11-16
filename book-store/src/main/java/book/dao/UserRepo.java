package book.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import book.model.User;


public interface UserRepo extends JpaRepository<User, Integer>
{
	public Optional<User> findByUsername(String username);
}
