package com.acme.PRSCapstone.Requestline;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.acme.PRSCapstone.Product.Product;
import com.acme.PRSCapstone.Request.RequestRepository;
@CrossOrigin
@RestController
@RequestMapping("/api/requestlines")
public class RequestlinesController {
	@Autowired
	private RequestlineRepository rlRepo;
	@Autowired
	private RequestRepository reqRepo;
	
	@GetMapping
	public ResponseEntity<Iterable<Requestline>> GetAll() {
		return new ResponseEntity<Iterable<Requestline>>(rlRepo.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Requestline> GetById(@PathVariable int id) {
		var rl = rlRepo.findById(id);
		if(rl.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Requestline>(rl.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Requestline> Insert(@RequestBody Requestline rl) throws Exception {
		if(rl == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		rl.setId(0);
		var newrl = rlRepo.save(rl);
		Recalculate(rl.getRequest().getId());
		return new ResponseEntity<Requestline>(newrl, HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("{id}")
	public ResponseEntity Update(@PathVariable int id, @RequestBody Requestline rl) throws Exception {
		if(rl.getId()!=id) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var oldrl = rlRepo.findById(id);
		if(oldrl.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		rlRepo.save(rl);
		Recalculate(rl.getRequest().getId());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity Delete(@PathVariable int id) throws Exception {
		var rl = rlRepo.findById(id);
		if(rl.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		rlRepo.deleteById(id);
		Recalculate(rl.get().getRequest().getId());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	private void Recalculate(int requestId) throws Exception {
		var optreq = reqRepo.findById(requestId);
		if(optreq.isEmpty()) {
			throw new Exception("Request Id is invalid");
		}
		var rl = rlRepo.findRequestlineByRequestId(requestId);
		var req = optreq.get();
		var requestline = rlRepo.findRequestlineByRequestId(requestId);
		var newtotal = 0;
		for(var reqline : requestline){
			newtotal += reqline.getProduct().getPrice() * reqline.getQuantity();
		}
		req.setTotal(newtotal);
		reqRepo.save(req);
	}

}
