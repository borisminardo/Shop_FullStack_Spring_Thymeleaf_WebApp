package com.max.negozio.service;

import java.util.List;
import java.util.Optional;

import com.max.negozio.model.Utente;

public interface UtenteService {
	void saveUtente(Utente u);
	List<Utente> getAll();
	Optional<Utente> findByUsername(String username);
	Optional<Utente> controlloLogin(String user, String pass);
	Utente update(String nome, String cognome, String indirizzo, String cap, String mail, String username);
}
