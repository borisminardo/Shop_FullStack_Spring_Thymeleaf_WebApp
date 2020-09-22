package com.boris.negozio.service;

import java.util.List;
import java.util.Optional;

import com.boris.negozio.model.Articolo;

public interface ArticoloService {
	void saveArticolo(Articolo a);
	List<Articolo> getAll();
	Optional<Articolo> findById(Long id);
	public void delete(int id);
}
