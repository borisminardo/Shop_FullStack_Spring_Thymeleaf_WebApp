package com.max.negozio.service;

import java.util.List;
import com.max.negozio.model.OrdineArticolo;

public interface OrdineArticoloService {
	void saveOrdineArticolo(OrdineArticolo oa);
	List<OrdineArticolo> getAll();
	List<String[]> getArticoli(long id);
}
