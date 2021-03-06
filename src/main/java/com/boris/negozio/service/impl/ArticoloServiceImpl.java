package com.boris.negozio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boris.negozio.model.Articolo;
import com.boris.negozio.model.Utente;
import com.boris.negozio.repository.ArticoloRepository;
import com.boris.negozio.service.ArticoloService;

@Service
public class ArticoloServiceImpl implements ArticoloService {
	@Autowired
	private ArticoloRepository ar;
	
	@Override
	public void saveArticolo(Articolo a) {
		ar.save(a);
	}

	@Override
	public List<Articolo> getAll() {
		return ar.findAll();
	}

	@Override
	public Optional<Articolo> findById(Long id) {
		return ar.findById(id);
	}

	@Override
	public void delete(int id) {
		ar.deleteById((long) id);
	}


}
