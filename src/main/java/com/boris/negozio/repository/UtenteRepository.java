package com.boris.negozio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boris.negozio.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, String>{
	@Query(value="select * from utente where username = ?1", nativeQuery=true)
	Optional<Utente> findByUsername(String user);
	
	@Query(value="select * from utente where username = ?1 and password = ?2", nativeQuery=true)
	Optional<Utente> controlloLogin(String user, String pass);

	@Query(value="update utente SET nome =?1, cognome=?2, indirizzo=?3, cap=?4, mail=?5, where username=?6", nativeQuery=true)
	Utente modificaUtente(String nome, String cognome, String indirizzo, String cap, String mail, String username);
}
