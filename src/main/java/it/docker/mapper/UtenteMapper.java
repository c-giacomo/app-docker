package it.docker.mapper;

import org.mapstruct.Mapper;

import it.docker.dto.UtenteDTO;
import it.docker.model.Utente;

@Mapper(componentModel = "spring")
public interface UtenteMapper extends CommonMapper<Utente, UtenteDTO> {

}
