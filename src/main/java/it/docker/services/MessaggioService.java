package it.docker.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.docker.dto.MessaggioDTO;
import it.docker.mapper.MessaggioMapper;
import it.docker.model.Messaggio;
import it.docker.repository.MessaggioRepository;

@Service
public class MessaggioService extends CommonService<Messaggio, Integer, MessaggioDTO, MessaggioRepository, MessaggioMapper> {
	
	@Transactional
	public MessaggioDTO inserisci(MessaggioDTO messaggioDto) {
		Messaggio entity = mapper.deserializza(messaggioDto);
		entity = repository.save(entity);
		return mapper.serializza(entity);
	}

}
