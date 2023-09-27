package it.docker.config;

import java.util.Collection;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;


@Configuration
@SuppressWarnings("rawtypes")
public class OpenApiConfig { 
	
	@Bean	
	public OpenApiCustomizer myOperationIdCustomizer() {
		return new CustomApiPaths();	
	}
	
	public class CustomApiPaths implements OpenApiCustomizer {
		
	
		@Override
		public void customise(OpenAPI openApi) {
			Collection<PathItem> paths = openApi.getPaths().values();
			for (PathItem path : paths) {
				for (Operation operation : path.readOperations()) {
					String tag = operation.getTags().stream().findFirst().orElseThrow();
					String method = operation.getOperationId();
					if (method.startsWith("findAll")) {
						operation.setSummary(String.format("Recupera tutti i/gli %s", tag.toLowerCase()));
						Tag apiTag = openApi.getTags().stream().filter(t -> t.getName().equals(tag)).findAny().orElseThrow();
						setResponses(operation, apiTag, tag);

					} else if (method.startsWith("findById")) {
						operation.setSummary(String.format("Recupera %s in base al loro id", tag.toLowerCase()));
						Tag apiTag = openApi.getTags().stream().filter(t -> t.getName().equals(tag)).findAny().orElseThrow();
						setResponses(operation, apiTag, tag);
					}
				}
			}
		}
		
		private void setResponses(Operation operation, Tag tag, String mess) {
			ApiResponses responses = new ApiResponses();
			ApiResponse responseOK = new ApiResponse()
				    .description(String.format("%s trovati", mess))
				    .content(new Content().addMediaType("application/json", new MediaType().schema(new Schema().$ref(tag.getExtensions().get("x-ref").toString()))));
			ApiResponse responseKO = new ApiResponse()
				    .description("Errore Interno del server")
				    .content(new Content().addMediaType("application/json", new MediaType()));
			responses.addApiResponse("200", responseOK);
			responses.addApiResponse("500", responseKO);
			operation.setResponses(responses);
		}
		
	}
}


