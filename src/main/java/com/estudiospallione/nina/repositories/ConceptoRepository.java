package com.estudiospallione.nina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estudiospallione.nina.entities.Concepto;

@Repository
public interface ConceptoRepository extends JpaRepository<Concepto, String>{

	@Query(value="SELECT * FROM concepto ORDER BY concepto", nativeQuery = true)
	public List<Concepto> listarConceptosAlfabeticamente();
	
}
