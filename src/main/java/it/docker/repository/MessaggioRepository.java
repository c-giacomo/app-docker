package it.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.docker.model.Messaggio;

public interface MessaggioRepository extends JpaRepository<Messaggio, Integer> {

}
