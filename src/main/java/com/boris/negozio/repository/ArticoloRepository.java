package com.boris.negozio.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boris.negozio.model.Articolo;

@Repository
public interface ArticoloRepository extends JpaRepository<Articolo, Long>{

}
