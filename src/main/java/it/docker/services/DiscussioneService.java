package it.docker.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.docker.dto.DiscussioneDTO;
import it.docker.mapper.DiscussioneMapper;
import it.docker.model.Discussione;
import it.docker.repository.DiscussioneRepository;

@Service
public class DiscussioneService extends CommonService<Discussione, Integer, DiscussioneDTO, DiscussioneRepository, DiscussioneMapper> {
	
	@Transactional
	public DiscussioneDTO inserisci(DiscussioneDTO discussioneDto) {
		Discussione discussione = mapper.deserializza(discussioneDto);
		discussione = repository.save(discussione);
		return mapper.serializza(discussione);
	}

	public List<DiscussioneDTO> findByUtente(Integer idUser) {
		return repository.findByUtenteId(idUser).stream().map(mapper::serializza).toList();
	}

}
