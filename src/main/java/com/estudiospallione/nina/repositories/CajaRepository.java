package com.estudiospallione.nina.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.estudiospallione.nina.entities.Caja;

@Repository
public interface CajaRepository extends JpaRepository<Caja, Long> {

	
	@Query(value="SELECT * FROM caja c WHERE c.cliente_id_cliente LIKE %:id_cliente% "
			+ "AND c.fecha_fecha >= :desde "
			+ "AND c.fecha_fecha <= :hasta", nativeQuery = true)
	public List<Caja> busquedaAvanzada(
			@Param("id_cliente") String id_cliente,
			@Param("desde") String desde,
			@Param("hasta") String hasta);
		
}
