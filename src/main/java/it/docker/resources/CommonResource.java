package it.docker.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import it.docker.services.CommonService;

public abstract class CommonResource<D, ID, S extends CommonService<?, ID, D, ?, ?>> {
	
	@Autowired
	S service;
	
	@GetMapping("/{id}")
	public D findById(ID id) {
		return service.findById(id);
	}
	
	@GetMapping
	public List<D> findAll() {
		return service.findAll();
	}

}
