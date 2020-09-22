package com.max.negozio.restcontroller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.max.negozio.model.Utente;
import com.max.negozio.repository.UtenteRepository;

@RestController
@RequestMapping("/apiutente")
@CrossOrigin(origins = "http://127.0.0.1:4200", allowedHeaders = "*") //imposto una porta per l'ascolto su angular
public class RestControllerUtente {
	@Autowired
	private UtenteRepository ur;
	
	@GetMapping("/utenti")
	public List<Utente> getUtenti(){
		return ur.findAll();
	}
	
	@GetMapping("/utente/{username}")
	public Optional<Utente> getUtente(@PathVariable String username) {
		return ur.findById(username);
	}
	
	@DeleteMapping("/delutente/{username}")
	public boolean deleteUtente(@PathVariable String username) {
		ur.deleteById(username);
		return true;
	}
	
	@PostMapping("/saveuser")
	public Utente saveUtente(@RequestBody Utente utente) {
		return ur.save(utente);
	}
	
	@PutMapping("/updateuser")
	public Utente updateUtente(@RequestBody Utente utente) { //@RequestBody prende tutti i dati di un form e viene inviato all'oggetto contatti
		return ur.save(utente);
	}
	

}
