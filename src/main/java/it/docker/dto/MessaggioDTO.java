package it.docker.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessaggioDTO {
	
	private Integer id;
	
	@NotNull
	private String testo;
	
	@NotNull
	private Integer idDiscussione;
	
	@NotNull
	private Integer idUtente;

}
