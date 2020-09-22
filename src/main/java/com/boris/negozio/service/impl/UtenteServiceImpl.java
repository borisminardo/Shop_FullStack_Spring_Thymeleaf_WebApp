package com.boris.negozio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boris.negozio.model.Utente;
import com.boris.negozio.repository.UtenteRepository;
import com.boris.negozio.service.UtenteService;

@Service
public class UtenteServiceImpl implements UtenteService{
	@Autowired
	private UtenteRepository ur;

	@Override
	public void saveUtente(Utente u) {
		ur.save(u);
	}

	@Override
	public List<Utente> getAll() {
		return ur.findAll();
	}

	@Override
	public Optional<Utente> findByUsername(String username) {
		return ur.findByUsername(username);
	}

	@Override
	public Optional<Utente> controlloLogin(String user, String pass) {
		return ur.controlloLogin(user, pass);
	}

	@Override
	public Utente update(String nome, String cognome, String indirizzo, String cap, String mail, String username) {
		return ur.modificaUtente(nome, cognome, indirizzo, cap, mail,username);
	}
}
