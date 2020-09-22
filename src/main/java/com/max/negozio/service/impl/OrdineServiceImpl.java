package com.max.negozio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.max.negozio.model.Ordine;
import com.max.negozio.repository.OrdineRepository;
import com.max.negozio.service.OrdineService;

@Service
public class OrdineServiceImpl implements OrdineService {
	@Autowired
	private OrdineRepository or;
	
	@Override
	public void saveOrdine(Ordine o) {
		or.save(o);
	}

	@Override
	public List<Ordine> getAll() {
		return or.findAll();
	}

	@Override
	public List<Ordine> findByUsername(String user) {
		return or.findByUsername(user);
	}
}
