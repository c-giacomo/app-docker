package it.docker.services;

import java.util.List;

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

}
