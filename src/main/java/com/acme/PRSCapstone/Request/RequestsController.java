package com.acme.PRSCapstone.Request;


import java.math.BigDecimal;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/requests")
public class RequestsController {

	@Autowired
	private RequestRepository reqRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Request>> GetAll() {
		return new ResponseEntity<Iterable<Request>>(reqRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Request> GetById(@PathVariable int id) {
		var request = reqRepo.findById(id);
		if(request.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Request>(request.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Request> Insert(@RequestBody Request request){
		if(request == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		request.setId(0);
		var newrequest = reqRepo.save(request);
		return new ResponseEntity<Request>(newrequest, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("{id}")
	public ResponseEntity Update(@PathVariable int id, @RequestBody Request request) {
		if(request.getId()!= id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var oldrequest = reqRepo.findById(request.getId());
		if(oldrequest.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		reqRepo.save(request);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity Delete(@PathVariable int id) {
		var request = reqRepo.findById(id);
		if(request.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		reqRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@GetMapping("notmine/{id}")
	public ResponseEntity<Iterable<Request>> findIdNot(@PathVariable int id){
		var req = reqRepo.findByIdNot(id);
		return new ResponseEntity<Iterable<Request>>(req, HttpStatus.OK);
	}
	
	@GetMapping("reviewnotmine/{id}")
	public ResponseEntity<Iterable<Request>> UnderReview(@PathVariable int id) {
		var req = reqRepo.findByStatusAndIdNot("REVIEW", id);
		return new ResponseEntity<Iterable<Request>>(req, HttpStatus.OK);
	}
	
	@PutMapping("approve/{id}")
	public ResponseEntity SetToApprove(@RequestBody Request req, @PathVariable int id) {
		if(req.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		req.setStatus("APPROVED");
		reqRepo.save(req);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("reject/{id}")
	public ResponseEntity SetToReject(@RequestBody Request req, @PathVariable int id) {
		if(req.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		req.setStatus("REJECTED");
		reqRepo.save(req);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("revieworapprove/{id}")
	public ResponseEntity SetToReviewOrApprove(@PathVariable int id, @RequestBody Request req) {
		if(req.getId() != id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(req.getTotal() <= 50) {
			req.setStatus("APPROVED");
		}
		if(req.getTotal() > 50) {
			req.setStatus("REVIEW");
		}
		reqRepo.save(req);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
}
