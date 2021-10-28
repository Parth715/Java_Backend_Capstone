package com.acme.PRSCapstone.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer>{
	public User findUserByUsernameAndPassword(String username, String password);
	
	@Query("select u from User u where u.username = ?1 and u.password = ?2")
	User findByLogin(String username, String password);
	
	@Query("select u from User u where u.lastname = ?1")
	List<User> findByLastname(String lastname);
}
