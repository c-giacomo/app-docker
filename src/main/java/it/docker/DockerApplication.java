package it.docker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.docker.model.Discussione;
import it.docker.model.Messaggio;
import it.docker.model.Utente;
import it.docker.repository.DiscussioneRepository;
import it.docker.repository.MessaggioRepository;
import it.docker.repository.UtenteRepository;

@SpringBootApplication
public class DockerApplication implements CommandLineRunner {
	
	@Autowired
	DiscussioneRepository dRepository;
	
	@Autowired
	UtenteRepository uRepository;
	
	@Autowired
	MessaggioRepository mRepository;

	public static void main(String[] args) {
		SpringApplication.run(DockerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Utente u = Utente.builder().nome("Giacomo").cognome("Chiavolotti").build();
		
		uRepository.save(u);
		
		Discussione d = Discussione.builder().nome("Prima discussione").build();
		d.setUtente(Arrays.asList(u));
		
		Messaggio m = Messaggio.builder().testo("Ciao").build();
		m.setUtente(Arrays.asList(u));
		
		List<Messaggio> mList = new ArrayList<>();
		mList.add(m);
		
		d.setMessaggi(mList);
		
		List<Discussione> dList = new ArrayList<>();
		dList.add(d);
		
		List<Utente> uList = new ArrayList<>();
		uList.add(u);
		
		mRepository.save(m);
		
		dRepository.save(d);
		
	}

}
