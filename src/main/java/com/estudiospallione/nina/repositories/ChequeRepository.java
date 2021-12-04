package com.estudiospallione.nina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estudiospallione.nina.entities.Cheque;

@Repository
public interface ChequeRepository extends JpaRepository<Cheque, String> {

	@Query(value="SELECT * FROM cheque c WHERE c.caja_id_caja IS NULL", nativeQuery = true)
	public List<Cheque> listarChequesSinCaja();
	
}
