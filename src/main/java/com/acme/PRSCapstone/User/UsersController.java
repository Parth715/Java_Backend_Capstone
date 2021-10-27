package com.acme.PRSCapstone.User;

import javax.swing.Spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UsersController {
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<User>> GetAll(){
		return new ResponseEntity<Iterable<User>>(userRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<User> GetById(@PathVariable int id){
		var user = userRepo.findById(id);
		if(user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user.get(), HttpStatus.OK);
	} 
	@PostMapping
	public ResponseEntity<User> Insert(@RequestBody User user){
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		user.setId(0);
		var newuser = userRepo.save(user);
		return new ResponseEntity<User>(newuser, HttpStatus.NO_CONTENT);	
	}
	@PutMapping("{id}")
	public ResponseEntity Update(@PathVariable int id, @RequestBody User user) {
		if(user.getId()!=id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var olduser = userRepo.findById(user.getId());
		if(olduser.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		userRepo.save(user);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity Delete(@PathVariable int id) {
		var user = userRepo.findById(id);
		if(user.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		userRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	@GetMapping("{username}/{password}")
	public ResponseEntity<User> Login(@PathVariable String username,@PathVariable String password){
		var user = userRepo.findUserByUsernameAndPassword(username, password);
		if(user == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}

}
