package it.docker.mapper;

import java.util.Arrays;
import java.util.Optional;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import it.docker.dto.DiscussioneDTO;
import it.docker.model.Discussione;
import it.docker.model.Utente;
import it.docker.repository.UtenteRepository;

@Mapper(componentModel = "spring")
public abstract class DiscussioneMapper implements CommonMapper<Discussione, DiscussioneDTO> {
	
	@Autowired
	UtenteRepository utenteRepository;
	
	@Override
	@Mapping(target = "user", expression = "java(entity.getUtente().stream().findFirst().get().getId())")
	public abstract DiscussioneDTO serializza(Discussione entity);
	
	@AfterMapping
	public void toEntity(@MappingTarget Discussione entity, DiscussioneDTO dto) {
		Optional<Utente> user = utenteRepository.findById(dto.getUser());
		if (user.isEmpty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non presente");
		
		if (entity.getUtente() == null)
			entity.setUtente(Arrays.asList(user.get()));
		else
			entity.getUtente().add(user.get());
	}

}
