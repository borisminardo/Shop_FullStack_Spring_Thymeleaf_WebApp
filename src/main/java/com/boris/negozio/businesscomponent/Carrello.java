package com.boris.negozio.businesscomponent;

import java.util.Enumeration;
import java.util.Hashtable;

public class Carrello {
	private Hashtable<String, String[]>prodotti = new Hashtable<String,String[]>();
	private int articoli;
	
	public Carrello() {
		articoli = 0;
	}
	
	public int getArticoli() {
		return articoli;
	}
	
	public void aggiungiArticolo(String id, String marca, String modello, double prezzo) {
		String[] record = {marca, modello, Double.toString(prezzo),"1",id}; 
		articoli++;
		if(prodotti.containsKey(id)) {
			String[] dati = prodotti.get(id);
			int quantita = Integer.parseInt(dati[3]);
			quantita++;
			dati[3] = Integer.toString(quantita);
			prodotti.put(id, dati);
		} else {
			prodotti.put(id, record);
		}
	}
		
	public void rimuoviArticolo(String id) {
		if(prodotti.containsKey(id)) {
			articoli--;
			String[] dati = prodotti.get(id);
			if(Integer.parseInt(dati[3]) == 1) {
				prodotti.remove(id);
			} else {
				int quantita = Integer.parseInt(dati[3]);
				quantita--;
				dati[3] = Integer.toString(quantita);
				prodotti.put(id, dati);
			}
		}
	}
	
	public double totaleComplessivo() {
		double totale = 0.00;
		Enumeration<String[]> enumerazione = listaProdotti();
		String[] dati;
		while(enumerazione.hasMoreElements()) {
			dati = enumerazione.nextElement();
			totale += Double.parseDouble(dati[2]) * Integer.parseInt(dati[3]);
		}
		return totale;
	}
	
	public double totaleParziale(String id) {
		double totale = 0.00;
		String[] dati = prodotti.get(id);
		totale += Double.parseDouble(dati[2]) * Integer.parseInt(dati[3]);
		return totale;
	}
	
	public Enumeration<String[]> listaProdotti(){
		return prodotti.elements();
	}
}

