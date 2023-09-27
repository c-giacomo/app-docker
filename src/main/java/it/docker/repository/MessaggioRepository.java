package it.docker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.docker.model.Messaggio;

public interface MessaggioRepository extends JpaRepository<Messaggio, Integer> {
	
	public List<Messaggio> findByUtenteId(Integer id); 

}
