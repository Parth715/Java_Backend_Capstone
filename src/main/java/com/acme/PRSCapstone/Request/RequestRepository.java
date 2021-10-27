package com.acme.PRSCapstone.Request;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer>{

	public List<Request> findByIdNot(int id);
	
	public List<Request> findByStatusAndIdNot(String status, int id);
	
	
}
