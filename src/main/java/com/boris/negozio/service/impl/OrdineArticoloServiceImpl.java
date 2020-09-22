package com.boris.negozio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boris.negozio.model.OrdineArticolo;
import com.boris.negozio.repository.OrdineArticoloRepository;
import com.boris.negozio.service.OrdineArticoloService;

@Service
public class OrdineArticoloServiceImpl implements OrdineArticoloService{
	@Autowired
	private OrdineArticoloRepository oar; 
	

	@Override
	public void saveOrdineArticolo(OrdineArticolo oa) {
		oar.save(oa);
	}

	@Override
	public List<OrdineArticolo> getAll() {
		return oar.findAll();
	}

	@Override
	public List<String[]> getArticoli(long id) {
		return oar.getArticoli(id);
	}
}
