package it.docker.mapper;

import org.mapstruct.Mapper;

import it.docker.dto.DiscussioneDTO;
import it.docker.model.Discussione;

@Mapper(componentModel = "spring")
public interface DiscussioneMapper extends CommonMapper<Discussione, DiscussioneDTO> {

}
