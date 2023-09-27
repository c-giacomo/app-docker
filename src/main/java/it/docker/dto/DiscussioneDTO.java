package it.docker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscussioneDTO {
	
	private Integer id;
	
	@NotNull
	private String nome;
	
	private UtenteDTO utente;

}
