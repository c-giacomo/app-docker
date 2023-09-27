package it.docker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscussioneDTO {
	
	private Integer id;
	
	private String nome;
	
	@NotNull
	private Integer user;

}
