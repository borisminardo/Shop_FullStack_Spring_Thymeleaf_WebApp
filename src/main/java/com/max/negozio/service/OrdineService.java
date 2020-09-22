package com.max.negozio.service;

import java.util.List;
import com.max.negozio.model.Ordine;

public interface OrdineService {
	void saveOrdine(Ordine o);
	List<Ordine> getAll();
	List<Ordine> findByUsername(String user);
}
