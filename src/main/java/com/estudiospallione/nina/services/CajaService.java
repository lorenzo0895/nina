package com.estudiospallione.nina.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.estudiospallione.nina.entities.Caja;
import com.estudiospallione.nina.entities.Cheque;
import com.estudiospallione.nina.entities.Cliente;
import com.estudiospallione.nina.entities.Fecha;
import com.estudiospallione.nina.entities.Modificacion;
import com.estudiospallione.nina.error.ErrorException;
import com.estudiospallione.nina.repositories.CajaRepository;
import com.estudiospallione.nina.repositories.ChequeRepository;
import com.estudiospallione.nina.repositories.ModificacionRepository;

@Service
public class CajaService {

	@Autowired
	CajaRepository cajaRepo;

	@Autowired
	ChequeRepository chequeRepo;

	@Autowired
	ModificacionRepository modificacionRepo;
	
	@Autowired
	ChequeService chequeServicio;
	
	@Autowired
	FechaService fechaService;

	@Transactional(readOnly = true)
	public List<Caja> listar() {
		return cajaRepo.findAll();
	}
	
	@Transactional(readOnly = true)
	public List<Caja> busquedaAvanzada(String id_cliente, String desde, String hasta) {
		return cajaRepo.busquedaAvanzada(id_cliente, desde, hasta);
	}
	
	@Transactional
	public void alta(String fecha, Cliente cliente, String detalle, Double efectivo, Double transferencia, 
			List<Cheque> listaCheques, List<Modificacion> listaModificacioes) throws ErrorException {

		if (fecha == null || fecha.equals("fecha")) {
			throw new ErrorException("Debe seleccionar una fecha.");
		}
		Fecha f1 = fechaService.buscarFechaConString(fecha);
		if (!f1.getActivo()) {
			throw new ErrorException("La fecha ingresada se encuentra cerrada.");
		}
		if (cliente == null) {
			throw new ErrorException("Debe seleccionar un cliente.");
		}
		if (detalle == null || detalle.equals("")) {
			throw new ErrorException("Debe brindar un detalle.");
		}
		
		
		Float sumador = 0f;
		for (Cheque cheque : listaCheques) {
			sumador = sumador + cheque.getImporte();
		}
		if (efectivo == 0 && transferencia == 0 && sumador == 0) {
			throw new ErrorException("Debe ingresar algún monto.");
		}
		
		Caja caja = new Caja();
		caja.setFecha(f1);
		caja.setCliente(cliente);
		caja.setDetalle(detalle);
		caja.setEfectivo(efectivo);
		caja.setTransferencia(transferencia);
		caja.setListaCheques(listaCheques);
		caja.setListaModificaciones(listaModificacioes);
		caja.setActivo(true);

		System.out.println(caja);
		
		cajaRepo.save(caja);
		for (Cheque cheque : listaCheques) {
			chequeServicio.actualizarCheque(cheque, caja);
		}

	}

}
