package it.docker.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import it.docker.mapper.CommonMapper;
import jakarta.persistence.EntityNotFoundException;

public class CommonService<T, ID, D, R extends JpaRepository<T, ID>, M extends CommonMapper<T, D>> {
	
	@Autowired
	R repository;
	@Autowired
	M mapper;
	
	public D findById(ID id) {
		Optional<T> entity = repository.findById(id);
		if (!entity.isPresent()) 
			throw new EntityNotFoundException(String.format("Entità non trovata %d", id));
		return mapper.serializza(entity.get());
	}
	
	public List<D> findAll() {
		List<T> entity = repository.findAll();
		return entity.stream().map(mapper::serializza).collect(Collectors.toList());
	}

}
