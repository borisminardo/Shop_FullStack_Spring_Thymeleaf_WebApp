package com.boris.negozio.restcontroller;

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

import com.boris.negozio.model.Ordine;
import com.boris.negozio.repository.OrdineRepository;

@RestController
@RequestMapping("/apiordine")
@CrossOrigin(origins = "http://127.0.0.1:4200", allowedHeaders = "*") //imposto una porta per l'ascolto su angular
public class RestControllerOrdine {
	@Autowired
	private OrdineRepository or;
	
	@GetMapping("/ordini")
	public List<Ordine> getOrdine(){
		return or.findAll();
	}
	
	@GetMapping("/ordine/{id}")
	public Optional<Ordine> getOrdine(@PathVariable Long id) {
		return or.findById(id);
	}
	
	@DeleteMapping("/delordine/{id}")
	public boolean deleteOrdine(@PathVariable Long id) {
		or.deleteById(id);
		return true;
	}
	
	@PostMapping("/saveord")
	public Ordine saveOrdine(@RequestBody Ordine ordine) {
		return or.save(ordine);
	}
	
	@PutMapping("/updateord")
	public Ordine updateOrdine(@RequestBody Ordine ordine) { //@RequestBody prende tutti i dati di un form e viene inviato all'oggetto contatti
		return or.save(ordine);
	}
	

}
