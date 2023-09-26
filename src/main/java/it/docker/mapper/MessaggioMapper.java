package it.docker.mapper;

import java.util.Optional;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import it.docker.dto.MessaggioDTO;
import it.docker.model.Discussione;
import it.docker.model.Messaggio;
import it.docker.model.Utente;
import it.docker.repository.DiscussioneRepository;
import it.docker.repository.UtenteRepository;

@Mapper(componentModel = "spring")
public abstract class MessaggioMapper implements CommonMapper<Messaggio, MessaggioDTO> {
	
	@Autowired
	UtenteRepository uRepository;
	
	@Autowired
	DiscussioneRepository dRepository;
	
	@Override
	@Mapping(target = "idUtente", source = "utente.id")
	@Mapping(target = "idDiscussione", source = "discussione.id")
	public abstract MessaggioDTO serializza(Messaggio entity);
	
	@Override
	@Mapping(target = "discussione", source = "idDiscussione", qualifiedByName = "discussioneToEntity")
	@Mapping(target = "utente", source = "idUtente", qualifiedByName = "userToEntity")
	public abstract Messaggio deserializza(MessaggioDTO dto);
	
	@Named("discussioneToEntity")
	public Discussione discussioneToEntity(Integer id) {
		Optional<Discussione> d = dRepository.findById(id);
		if (d.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Discussione non trovata");
		
		return d.get();
	}
	
	@Named("userToEntity")
	public Utente userToEntity(Integer id) {
		Optional<Utente> u = uRepository.findById(id);
		if (u.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non trovato");
		
		return u.get();
	}
	
}
