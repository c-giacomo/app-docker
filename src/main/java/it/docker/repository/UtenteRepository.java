package it.docker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.docker.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {
	
	public List<Utente> findByMessaggioDiscussioneId(Integer id);

}
