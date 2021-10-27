package com.acme.PRSCapstone.Requestline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestlineRepository extends JpaRepository<Requestline, Integer>{

	public List<Requestline> findRequestlineByRequestId(int id);
}
