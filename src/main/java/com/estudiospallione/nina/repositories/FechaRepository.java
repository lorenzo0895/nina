package com.estudiospallione.nina.repositories;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudiospallione.nina.entities.Fecha;

@Repository
public interface FechaRepository  extends JpaRepository<Fecha, Calendar> {

	@Query(value = "SELECT * FROM fecha WHERE activo = 1", nativeQuery = true)
	public List<Fecha> findFechasAbiertas();
	
	@Query(value = "SELECT * FROM fecha WHERE fecha = (SELECT MAX(fecha) FROM fecha WHERE activo = 1)", nativeQuery = true)
	public Fecha maxFecha();

}
