package it.docker.resources;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import it.docker.dto.MessaggioDTO;
import it.docker.dto.UtenteDTO;
import it.docker.services.MessaggioService;
import jakarta.validation.Valid;

@Tag(name = "Messaggi", description = "Operazioni sui messaggi", extensions = @Extension(properties = @ExtensionProperty(name = "ref", value = "MessaggioDTO")))
@RestController
@RequestMapping(value = "/api/v1/messaggi")
public class MessaggioResource extends CommonResource<MessaggioDTO, Integer, MessaggioService> {
	
	@Operation(summary = "Salva una nuovo messaggio.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Messaggio Creato.",content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = MessaggioDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Errore interno del server.", content = @Content)
	})
	@PostMapping("/inserisci")
	public ResponseEntity<MessaggioDTO> inserisci(@RequestBody @Valid MessaggioDTO messaggioDto) {
		MessaggioDTO result = service.inserisci(messaggioDto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@Operation(summary = "Recupera i messaggi scritti da un utente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Messaggio Creato.",content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = MessaggioDTO.class))) }),
			@ApiResponse(responseCode = "500", description = "Errore interno del server.", content = @Content)
	})
	@PostMapping("/utente")
	public ResponseEntity<List<MessaggioDTO>> inserisci(@RequestBody UtenteDTO utenteDto) {
		List<MessaggioDTO> result = service.daUtente(utenteDto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
