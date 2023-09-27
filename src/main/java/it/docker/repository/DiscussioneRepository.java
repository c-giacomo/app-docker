package it.docker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.docker.model.Discussione;

public interface DiscussioneRepository extends JpaRepository<Discussione, Integer> {
	
	public List<Discussione> findByUtenteId(Integer idUtente);

}
