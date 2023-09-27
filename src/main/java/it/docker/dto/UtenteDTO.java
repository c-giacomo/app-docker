package it.docker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UtenteDTO {
	
	private Integer id;
	
	@NotNull
	private String nome;
	@NotNull
	private String cognome;

}
