package it.docker.resources;

import java.util.List;

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
import it.docker.dto.DiscussioneDTO;
import it.docker.services.DiscussioneService;

@Tag(name = "Discussione", description = "Operazioni sulle discussioni", extensions = @Extension(properties = @ExtensionProperty(name = "ref", value = "DiscussioneDTO")))
@RestController
@RequestMapping(value = "/api/v1/discussione")
public class DiscussioneResource extends CommonResource<DiscussioneDTO, Integer, DiscussioneService> {
	
	
	@Operation(summary = "Salva una nuova discussione.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Discussione Creata.",content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = DiscussioneDTO.class)) }),
			@ApiResponse(responseCode = "500", description = "Errore interno del server.", content = @Content)
	})
	@PostMapping("/inserisci")
	public ResponseEntity<DiscussioneDTO> inserisci(@RequestBody DiscussioneDTO discussioneDto) {
		DiscussioneDTO result = service.inserisci(discussioneDto);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "Trova tutte le discussioni create da un utente.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Discussioni trovate.",content = {
					@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = DiscussioneDTO.class))) }),
			@ApiResponse(responseCode = "500", description = "Errore interno del server.", content = @Content)
	})
	@GetMapping("/trovaDaUtente/{id}")
	public ResponseEntity<List<DiscussioneDTO>> trovaDaUtente(@PathVariable("id") Integer idUser) {
		List<DiscussioneDTO> result = service.findByUtente(idUser);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
