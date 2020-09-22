package com.boris.negozio.service;

import java.util.List;

import com.boris.negozio.model.Ordine;

public interface OrdineService {
	void saveOrdine(Ordine o);
	List<Ordine> getAll();
	List<Ordine> findByUsername(String user);
}
