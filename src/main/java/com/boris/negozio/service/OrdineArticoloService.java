package com.boris.negozio.service;

import java.util.List;

import com.boris.negozio.model.OrdineArticolo;

public interface OrdineArticoloService {
	void saveOrdineArticolo(OrdineArticolo oa);
	List<OrdineArticolo> getAll();
	List<String[]> getArticoli(long id);
}
