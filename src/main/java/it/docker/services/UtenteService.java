package it.docker.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.docker.dto.UtenteDTO;
import it.docker.mapper.UtenteMapper;
import it.docker.model.Utente;
import it.docker.repository.UtenteRepository;

@Service
public class UtenteService extends CommonService<Utente, Integer, UtenteDTO, UtenteRepository, UtenteMapper> {
	
	@Transactional
	public UtenteDTO inserisci(UtenteDTO utenteDto) {
		Utente utente = mapper.deserializza(utenteDto);
		utente = repository.save(utente);
		return mapper.serializza(utente);
	}

	@Transactional(readOnly = true)
	public List<UtenteDTO> findPartecipanti(Integer id) {
		return repository.findByMessaggioDiscussioneId(id).stream().map(mapper::serializza).toList();
	}

	@Transactional(readOnly = true)
	public Set<UtenteDTO> findCreatori() {
		return repository.findAll().stream().filter(u -> !u.getDiscussione().isEmpty()).map(mapper::serializza).collect(Collectors.toSet()); 
	}

}
