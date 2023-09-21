package it.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.docker.model.Utente;

public interface UtenteRepository extends JpaRepository<Utente, Integer> {

}
