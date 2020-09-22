package com.max.negozio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.max.negozio.model.OrdineArticolo;

@Repository
public interface OrdineArticoloRepository extends JpaRepository<OrdineArticolo, Long>{
	@Query(value="select marca, modello, prezzo, qta from articolo, ordine_articolo where articolo.id = id_articolo and id_ordine = ?1", nativeQuery = true)
	List<String[]> getArticoli(long id);
}
