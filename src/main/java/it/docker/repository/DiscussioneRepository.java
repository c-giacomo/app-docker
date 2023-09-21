package it.docker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.docker.model.Discussione;

public interface DiscussioneRepository extends JpaRepository<Discussione, Integer> {

}
