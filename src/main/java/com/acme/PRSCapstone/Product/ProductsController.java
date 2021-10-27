package com.acme.PRSCapstone.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/products")
public class ProductsController {
	@Autowired
	private ProductRepository proRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Product>> GetAll(){
		return new ResponseEntity<Iterable<Product>>(proRepo.findAll(), HttpStatus.OK);
	}
	@GetMapping("{id}")
	public ResponseEntity<Product> GetById(@PathVariable int id){
		var product = proRepo.findById(id);
		if(product.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Product> Insert(@RequestBody Product product) {
		if(product == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		product.setId(0);
		var newproduct = proRepo.save(product);
		return new ResponseEntity<Product>(newproduct, HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity Update(@PathVariable int id, @RequestBody Product product) {
		if(product.getId()!= id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var oldpro = proRepo.findById(id);
		if(oldpro.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		proRepo.save(product);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity Delete(@PathVariable int id) {
		var product = proRepo.findById(id);
		if(product.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		proRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
