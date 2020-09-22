package com.boris.negozio.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Ordine implements Serializable {
	private static final long serialVersionUID = -4136152526229738579L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idOrdine;
	@Column(nullable = false)
	private double totale;
	@Column(nullable = false)
	private Date data;
	
	@ManyToOne
	@JoinColumn(name ="username", nullable = false)
	private Utente utente;
	
	@OneToMany(mappedBy = "ordine", cascade = CascadeType.ALL)
	@JsonIgnore
	private Set<OrdineArticolo> oa = new HashSet<OrdineArticolo>();

	public long getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(long idOrdine) {
		this.idOrdine = idOrdine;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Set<OrdineArticolo> getOa() {
		return oa;
	}

	public void setOa(Set<OrdineArticolo> oa) {
		this.oa = oa;
	}
}
