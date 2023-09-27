package it.docker.resources;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.docker.dto.UtenteDTO;
import it.docker.services.UtenteService;
import jakarta.validation.Valid;

@Tag(name = "Utenti", description = "Operazioni sugli utenti", extensions = @Extension(properties = @ExtensionProperty(name = "ref", value = "UtenteDTO")))
@RestController
@RequestMapping(value = "/api/v1/utente")
public class UtenteResource extends CommonResource<UtenteDTO, Integer, UtenteService> {
	
	
	@Operation(summary = "Salva un nuovo utente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Utente Creato.",content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = UtenteDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Errore interno del server.", content = @Content)
	})
	@PostMapping("/inserisci")
	public ResponseEntity<UtenteDTO> inserisci(@RequestBody @Valid UtenteDTO utenteDto) {
		UtenteDTO result = service.inserisci(utenteDto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Recupera gli utenti che partecipano ad una discussione.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Utenti Trovati.",content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UtenteDTO.class))) }),
			@ApiResponse(responseCode = "500", description = "Errore interno del server.", content = @Content)
	})
	@GetMapping("/partecipanti/discussioni/{id}")
	public ResponseEntity<List<UtenteDTO>> partecipanti(@PathVariable("id") Integer id) {
		List<UtenteDTO> result = service.findPartecipanti(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "Recupera gli utenti che hanno creato una discussione.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Utenti Trovati.",content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UtenteDTO.class))) }),
			@ApiResponse(responseCode = "500", description = "Errore interno del server.", content = @Content)
	})
	@GetMapping("/creatori/discussioni")
	public ResponseEntity<Set<UtenteDTO>> creatori() {
		Set<UtenteDTO> result = service.findCreatori();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}


}
