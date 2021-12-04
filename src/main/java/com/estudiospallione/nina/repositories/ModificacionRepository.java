package com.estudiospallione.nina.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudiospallione.nina.entities.Modificacion;

@Repository
public interface ModificacionRepository extends JpaRepository<Modificacion, String>{

}
