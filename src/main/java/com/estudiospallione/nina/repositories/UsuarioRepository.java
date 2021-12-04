package com.estudiospallione.nina.repositories;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.estudiospallione.nina.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	@Query("SELECT c FROM usuario c WHERE c.username = :username")
	public Usuario findByUsername(@Param("username") String username);
	
}
