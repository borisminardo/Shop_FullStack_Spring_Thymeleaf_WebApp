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

import com.max.negozio.model.Articolo;
import com.max.negozio.repository.ArticoloRepository;

@RestController
@RequestMapping("/apiarticolo")
@CrossOrigin(origins = "http://127.0.0.1:4200", allowedHeaders = "*") //imposto una porta per l'ascolto su angular
public class RestControllerArticolo {
	@Autowired
	private ArticoloRepository ar;
	
	@GetMapping("/articoli")
	public List<Articolo> getArticolo(){
		return ar.findAll();
	}
	
	@GetMapping("/articolo/{id}")
	public Optional<Articolo> getArticolo(@PathVariable Long id) {
		return ar.findById(id);
	}
	
	@DeleteMapping("/delarticolo/{id}")
	public boolean deleteArticolo(@PathVariable Long id) {
		ar.deleteById(id);
		return true;
	}
	
	@PostMapping("/saveart")
	public Articolo saveArticolo(@RequestBody Articolo articolo) {
		return ar.save(articolo);
	}
	
	@PutMapping("/updateart")
	public Articolo updateArticolo(@RequestBody Articolo articolo) { //@RequestBody prende tutti i dati di un form e viene inviato all'oggetto contatti
		return ar.save(articolo);
	}
	

}
