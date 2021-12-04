package com.estudiospallione.nina.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudiospallione.nina.entities.Concepto;
import com.estudiospallione.nina.error.ErrorException;
import com.estudiospallione.nina.repositories.ConceptoRepository;

@Service
public class ConceptoService {

	@Autowired
	ConceptoRepository repo;
	
	public List<Concepto> listarConceptos(){
		return repo.findAll();
	}
	
	public List<Concepto> listarConceptosAlfabeticamente(){
		return repo.listarConceptosAlfabeticamente();
	}
	
	public void nuevo(String concepto) throws ErrorException{
		if (concepto.equals("")) {
			throw new ErrorException("El concepto no puede ser nulo.");
		}
		Concepto c = new Concepto();
		c.setConcepto(concepto);
		repo.save(c);
	}
}
