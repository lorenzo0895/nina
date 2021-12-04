package com.estudiospallione.nina.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudiospallione.nina.entities.Modificacion;
import com.estudiospallione.nina.repositories.ModificacionRepository;

@Service
public class ModificacionService {

	@Autowired
	ModificacionRepository repo;
	
	public List<Modificacion> listarModificaciones(){
		return repo.findAll();
	}
}
