package com.estudiospallione.nina.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estudiospallione.nina.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>{
	
	@Query(value="SELECT * FROM cliente ORDER BY apellido", nativeQuery = true)
	public List<Cliente> listarClientesAlfabeticamente();
	
}
