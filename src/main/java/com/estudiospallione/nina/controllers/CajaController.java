package com.estudiospallione.nina.controllers;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estudiospallione.nina.entities.Caja;
import com.estudiospallione.nina.entities.Cheque;
import com.estudiospallione.nina.entities.Cliente;
import com.estudiospallione.nina.entities.Concepto;
import com.estudiospallione.nina.entities.Fecha;
import com.estudiospallione.nina.entities.Usuario;
import com.estudiospallione.nina.error.ErrorException;
import com.estudiospallione.nina.services.CajaService;
import com.estudiospallione.nina.services.ChequeService;
import com.estudiospallione.nina.services.ClienteService;
import com.estudiospallione.nina.services.ConceptoService;
import com.estudiospallione.nina.services.FechaService;

@Controller
@RequestMapping("/aft")
public class CajaController {

	@Autowired
	FechaService fechaServicio;

	@Autowired
	ClienteService clienteServicio;

	@Autowired
	ConceptoService conceptoServicio;

	@Autowired
	ChequeService chequeServicio;

	@Autowired
	CajaService cajaServicio;

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("")
	public String inicio() {
		try {
			// ExcelExport.exportExcel();
		} catch (Exception e) {

		}

		return "caja-inicio";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("/dia")
	public String dia(HttpSession httpSession, Model model) {

		Usuario usuario = (Usuario) httpSession.getAttribute("usersession");
		if (!usuario.getPermiso_abrir_dias() && !usuario.getPermiso_cerrar_dias()) {
			return "redirect:/aft";
		}
		List<Fecha> listaFechasAbiertas = fechaServicio.listarFechasAbiertas();
		model.addAttribute("fechas", listaFechasAbiertas);
		return "caja-dia";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@PostMapping("/altaDia")
	public String altaDia(HttpSession httpSession, Model model, @RequestParam String dia,
			RedirectAttributes redirectAttrs) {

		Usuario usuario = (Usuario) httpSession.getAttribute("usersession");
		if (!usuario.getPermiso_abrir_dias()) {
			return "redirect:/aft";
		}

		try {
			Calendar c1 = Calendar.getInstance();
			int day = Integer.parseInt(dia.substring(8, 10));
			int month = Integer.parseInt(dia.substring(5, 7));
			int year = Integer.parseInt(dia.substring(0, 4));
			c1.set(year, (month - 1), day);
			fechaServicio.crearFecha(c1);
		} catch (ErrorException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/aft/dia";
		}
		redirectAttrs.addFlashAttribute("msj", "Día dado de alta correctamente.");
		return "redirect:/aft/dia";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@PostMapping("/bajaDia")
	public String bajaDia(HttpSession httpSession, Model model, @RequestParam(defaultValue = "0000/00/00") String fecha,
			@RequestParam(required = false) Float sobrante, RedirectAttributes redirectAttrs) {

		Usuario usuario = (Usuario) httpSession.getAttribute("usersession");
		if (!usuario.getPermiso_cerrar_dias()) {
			return "redirect:/aft";
		}

		try {

			fechaServicio.cerrarFecha(fecha, sobrante);
		} catch (ErrorException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
			return "redirect:/aft/dia";
		}
		redirectAttrs.addFlashAttribute("msj", "Día dado de baja correctamente.");
		return "redirect:/aft/dia";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("/alta")
	public String alta(HttpSession httpSession, Model model) {
		Usuario usuario = (Usuario) httpSession.getAttribute("usersession");
		if (!usuario.getPermiso_ingresar()) {
			return "redirect:/aft";
		}
		List<Fecha> listaFechas = fechaServicio.listarFechasAbiertas();
		List<Cliente> listaClientes = clienteServicio.listarClientesAlfabeticamente();
		List<Concepto> listaConceptos = conceptoServicio.listarConceptosAlfabeticamente();
		List<Cheque> listaCheques = chequeServicio.listarCheques();
		model.addAttribute("fechas", listaFechas);
		model.addAttribute("clientes", listaClientes);
		model.addAttribute("conceptos", listaConceptos);
		model.addAttribute("cheques", listaCheques);
		return "caja-alta";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@PostMapping("/alta")
	public String altaPost(HttpSession httpSession, RedirectAttributes redirectAttributes,
			@RequestParam(required = false) String fecha, @RequestParam(required = false) Cliente cliente,
			@RequestParam(required = false) String detalle,
			@RequestParam(defaultValue = "0") Double efectivo,
			@RequestParam(defaultValue = "0") Double transferencia,
			@RequestParam(required = false) Cheque cheque1,
			@RequestParam(required = false) Cheque cheque2,
			@RequestParam(required = false) Cheque cheque3,
			@RequestParam(required = false) Cheque cheque4) {

		// SI EL USUARIO NO TIENE PERMISOS LO REDIRECCIONAMOS A LA PRINCIPAL
		Usuario usuario = (Usuario) httpSession.getAttribute("usersession");
		if (!usuario.getPermiso_ingresar()) {
			return "redirect:/aft";
		}

		// CREAMOS CAJA
		try {
			cajaServicio.alta(fecha, cliente, detalle, efectivo, transferencia, null, null);
			return "redirect:/aft";
		} catch (ErrorException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage())
			.addFlashAttribute("fecha", fecha)
			.addFlashAttribute("cliente", cliente)
			.addFlashAttribute("detalle", detalle)
			.addFlashAttribute("efectivo", efectivo)
			.addFlashAttribute("transferencia", transferencia);
			
			return "redirect:/aft/alta";
		}

	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("/buscar")
	public String buscar(Model model) {
		List<Concepto> listaConceptos = conceptoServicio.listarConceptos();
		List<Cliente> listaClientes = clienteServicio.listarClientesAlfabeticamente();
		List<Fecha> listaFechas = fechaServicio.listarFechas();
		model.addAttribute("conceptos", listaConceptos);
		model.addAttribute("clientes", listaClientes);
		model.addAttribute("fechas", listaFechas);
		return "caja-busqueda";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("/busqueda")
	public String resultadoBusqueda(Model model,
			@RequestParam String cliente,
			@RequestParam String concepto,
			@RequestParam String desde,
			@RequestParam String hasta) {
		if (cliente.equals("Todos")) {
			cliente = "";
		}
		if (concepto.equals("Todos")) {
			concepto = "";
		}
		List<Caja> cajas = cajaServicio.busquedaAvanzada(cliente, desde, hasta);
		model.addAttribute("cajas", cajas);
		return "caja-listar";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("/imprimir")
	public String imprimir() {
		return "imprimir";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@PostMapping("/nuevoConcepto")
	public String nuevoConcepto(Model model, @RequestParam(defaultValue = "") String concepto,
			RedirectAttributes redirectAttrs) {
		try {
			conceptoServicio.nuevo(concepto);
		} catch (ErrorException e) {
			redirectAttrs.addFlashAttribute("errorConcepto", e.getMessage());
			return "redirect:/aft/alta";
		}
		redirectAttrs.addFlashAttribute("msj", "Concepto dado de alta correctamente.");
		return "redirect:/aft/alta";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("/cheque")
	public String altaCheque() {
		return "caja-cheque-alta";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@PostMapping("/cheque")
	public String nuevoChequePost(Model model, RedirectAttributes redirectAttrs, 
			@RequestParam(required = false) String numero,
			@RequestParam(defaultValue = "0000/00/00") String fecha,
			@RequestParam(required = false) String banco,
			@RequestParam(required = false) String sucursal,
			@RequestParam(required = false) Long cuit,
			@RequestParam(required = false) Float importe) {
		try {
			chequeServicio.nuevo(numero, fecha, banco, sucursal, cuit, importe);
			redirectAttrs.addFlashAttribute("msj", "Concepto dado de alta correctamente.");
		} catch (ErrorException e) {
			redirectAttrs.addFlashAttribute("error", e.getMessage());
		}
		return "redirect:/aft/cheque";
	}
	

}
