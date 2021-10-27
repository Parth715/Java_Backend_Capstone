package com.acme.PRSCapstone.Vendor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/vendors")
public class VendorsController {
	@Autowired
	private VendorRepository venRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Vendor>> GetAll() {
		return new ResponseEntity<Iterable<Vendor>>(venRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Vendor> GetById(@PathVariable int id) {
		var vendor = venRepo.findById(id);
		if(vendor.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vendor>(vendor.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Vendor> Insert(@RequestBody Vendor vendor) {
		if(vendor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		vendor.setId(0);
		var newvendor = venRepo.save(vendor);
		return new ResponseEntity<Vendor>(newvendor, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("{id}")
	public ResponseEntity Update(@PathVariable int id, @RequestBody Vendor vendor) {
		if(vendor.getId()!=id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var oldvendor = venRepo.findById(vendor.getId());
		if(oldvendor.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		venRepo.save(vendor);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity Delete(@PathVariable int id) {
		var ven = venRepo.findById(id);
		if(ven.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		venRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
