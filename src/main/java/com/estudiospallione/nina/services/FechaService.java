package com.estudiospallione.nina.services;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudiospallione.nina.entities.Fecha;
import com.estudiospallione.nina.error.ErrorException;
import com.estudiospallione.nina.repositories.FechaRepository;

@Service
public class FechaService {

	@Autowired
	FechaRepository repo;

	public Fecha buscarPorFecha(Calendar fecha) {
		Optional<Fecha> optional = repo.findById(fecha);
		if (optional.isPresent()) {
			return optional.get();

		}
		return null;
	}

	public List<Fecha> listarFechas() {
		return repo.findAll();
	}

	public List<Fecha> listarFechasAbiertas() {
		return repo.findFechasAbiertas();
	}

	@Transactional
	public void crearFecha(Calendar dia) throws ErrorException {

		Fecha fechaBuscada = new Fecha();
		Optional<Fecha> optional = repo.findById(dia);
		if (optional.isPresent()) {
			fechaBuscada = optional.get();
			if (fechaBuscada.getFecha().equals(dia)) {
				throw new ErrorException("La fecha ya existe en la base de datos.");
			}
		}

		// Valida que no se cree una fecha anterior a la ultima creada
		Fecha ultimaFecha = repo.maxFecha();
		if (ultimaFecha != null && ultimaFecha.getFecha().after(dia)) {
			throw new ErrorException("No puede ingresar una fecha anterior a otra ya creada.");
		}

		// Valida que no se cree una fecha anterior a hoy
		Calendar hoy = new GregorianCalendar();
		hoy.set(Calendar.HOUR_OF_DAY, 0);
		hoy.set(Calendar.MINUTE, 0);
		hoy.set(Calendar.SECOND, 0);
		hoy.set(Calendar.MILLISECOND, 0);
		if (hoy.after(dia)) {
			throw new ErrorException("No puede ingresar una fecha anterior al día de hoy.");
		}

		// Valida limite máximo de dias posteriores a hoy que se puede crear fecha.
		int diasPermitidos = 1;
		Calendar diaPosterior = hoy;
		diaPosterior.add(Calendar.DATE, diasPermitidos);
		if (!diaPosterior.after(dia)) {
			throw new ErrorException("No puede ingresar una fecha " + diasPermitidos + " día/s posterior/es a hoy.");
		}

		// Validar que no pueda haber 2 fechas abiertas
		int fechasAbiertasPermitidas = 1;
		List<Fecha> fechasAbiertas = repo.findFechasAbiertas();
		if (fechasAbiertas.size() == fechasAbiertasPermitidas) {
			throw new ErrorException("SÃ³lo se permite/n tener " + fechasAbiertasPermitidas + " dÃ­a/s abierto/s.");
		}

		Fecha fecha1 = new Fecha();
		fecha1.setFecha(dia);
		fecha1.setActivo(true);
		fecha1.setSobrante(null);

		repo.save(fecha1);

	}

	public static Calendar transformarStringToCalendar(String fecha) {
		// FORMATO DE FECHA = YYYY/MM/DD
		Calendar c1 = Calendar.getInstance();
		int day = Integer.parseInt(fecha.substring(8, 10));
		int month = Integer.parseInt(fecha.substring(5, 7));
		int year = Integer.parseInt(fecha.substring(0, 4));
		c1.set(year, (month - 1), day);
		
		return c1;
	}

	public Fecha buscarFechaConString(String fecha) {
		
		Calendar c1 = transformarStringToCalendar(fecha);
	
		Optional<Fecha> optional = repo.findById(c1);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}

	}

	@Transactional
	public void cerrarFecha(String fecha, Float sobrante) throws ErrorException {

		if (fecha.equals("0000/00/00")) {
			throw new ErrorException("Debe ingresar una fecha.");
		}
		Fecha fecha1 = buscarFechaConString(fecha);
		if (fecha1 == null) {
			throw new ErrorException("No se encontrÃ³ la fecha ingresada.");
		}
		if (sobrante == null) {
			throw new ErrorException("Debe ingresar un sobrante.");
		}

		fecha1.setActivo(false);
		fecha1.setSobrante(sobrante);
		repo.save(fecha1);
	}

}
