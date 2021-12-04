package com.estudiospallione.nina.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudiospallione.nina.entities.Cliente;
import com.estudiospallione.nina.error.ErrorException;
import com.estudiospallione.nina.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository repo;
	
	public List<Cliente> listarClientes (){
		return repo.findAll();
	}
	
	public List<Cliente> listarClientesAlfabeticamente (){
		return repo.listarClientesAlfabeticamente();
	}
	
	@Transactional
	public void crearCliente(String nombre,
			String apellido,
			String posicion,
			Long cuit,
			String claveFiscal,
			String mail,
			String telefono,
			String observaciones) throws ErrorException {
		
		validar(nombre, apellido, posicion, cuit, claveFiscal, mail, telefono, observaciones);
		
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setPosicion(posicion);
		cliente.setCuit(cuit);
		cliente.setClaveFiscal(claveFiscal);
		cliente.setMail(mail);
		cliente.setTelefono(telefono);
		cliente.setObservaciones(observaciones);
		cliente.setActivo(true);
		repo.save(cliente);
		
	}
	
	@Transactional
	public void editarCliente(Cliente cliente,
			String nombre,
			String apellido,
			String posicion,
			Long cuit,
			String claveFiscal,
			String mail,
			String telefono,
			String observaciones) throws ErrorException {
		
		validar(nombre, apellido, posicion, cuit, claveFiscal, mail, telefono, observaciones);
		
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setPosicion(posicion);
		cliente.setCuit(cuit);
		cliente.setClaveFiscal(claveFiscal);
		cliente.setMail(mail);
		cliente.setTelefono(telefono);
		cliente.setObservaciones(observaciones);
		cliente.setActivo(true);
		repo.save(cliente);
		
	}
	
	public void validar(String nombre,
			String apellido,
			String posicion,
			Long cuit,
			String claveFiscal,
			String mail,
			String telefono,
			String observaciones) throws ErrorException {
		if (nombre == null || nombre.equals("")) {
			throw new ErrorException("El nombre no puede ser nulo.");
		}
		if (apellido == null || apellido.equals("")) {
			throw new ErrorException("El apellido no puede ser nulo.");
		}
		if (posicion == null || posicion.equals("") || posicion.equals("posición")) {
			throw new ErrorException("La posicion no puede ser nula.");
		}
		if (!posicion.equals("Responsable Inscripto") && !posicion.equals("Monotributista") &&
				!posicion.equals("Excento") && !posicion.equals("Consumidor Final")) {
			throw new ErrorException("La posicion elegida no es valida.");
		}
		int largoCuit = cuit.toString().length();
		if (largoCuit != 11) {
			throw new ErrorException("El CUIT debe tener 11 dígitos");
		}
		int primerosDigitos = Integer.parseInt(cuit.toString().substring(0, 2));
		if (primerosDigitos != 20 && primerosDigitos != 23 && primerosDigitos != 24 && primerosDigitos != 25 &&
				primerosDigitos != 26 && primerosDigitos != 27 && primerosDigitos != 30 && primerosDigitos != 33 &&
				primerosDigitos != 34) {
			throw new ErrorException("Los primeros dígitos deben ser 20, 23, 24, 25, 26, 27, 30, 33 o 34.");
		}
		if (claveFiscal == null || claveFiscal.equals("")) {
			throw new ErrorException("La Clave Fiscal no puede ser nula.");
		}
	}
	
}
