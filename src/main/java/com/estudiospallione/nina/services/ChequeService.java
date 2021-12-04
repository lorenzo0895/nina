package com.estudiospallione.nina.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudiospallione.nina.entities.Cheque;
import com.estudiospallione.nina.error.ErrorException;
import com.estudiospallione.nina.repositories.ChequeRepository;

@Service
public class ChequeService {

	@Autowired
	ChequeRepository repo;

	public List<Cheque> listarCheques() {
		return repo.findAll();
	}

	// DEVUELVE TRUE SI ALGÚN ELEMENTO DEL CHEQUE ESTÁ COMPLETO (ASÍ SABEMOS SI HAY
	// QUE CONSIDERARLO O NO
	public Boolean validar(String numero, String fecha, String banco, String sucursal, Long cuit, Float importe) {
		return (numero != null || !fecha.equals("0000/00/00") || banco != null || sucursal != null || cuit != null
				|| importe != null);
	}

	@Transactional
	public void nuevo(String numero, String fecha, String banco, String sucursal, Long cuit, Float importe)
			throws ErrorException {
		if (numero == null || numero.equals("")) {
			throw new ErrorException("El número del cheque no puede ser nulo.");
		}
		if (banco == null || banco.equals("")) {
			throw new ErrorException("El banco del cheque no puede ser nulo.");
		}
		if (sucursal == null || sucursal.equals("")) {
			throw new ErrorException("La sucursal del cheque no puede ser nula.");
		}
		if (cuit == null) {
			throw new ErrorException("El CUIT del cheque no puede ser nulo.");
		}
		if (fecha == null || fecha.equals("0000/00/00")) {
			throw new ErrorException("La fecha del cheque no puede ser nula.");
		}
		if (importe == null) {
			throw new ErrorException("El importe del cheque no puede ser nulo.");
		}
		Cheque cheque = new Cheque();
		cheque.setNumero(numero);
		cheque.setFecha(FechaService.transformarStringToCalendar(fecha));
		cheque.setBanco(banco);
		cheque.setSucursal(sucursal);
		cheque.setCuit(cuit);
		cheque.setImporte(importe);

		repo.save(cheque);
	}

}
