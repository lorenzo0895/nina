package com.estudiospallione.nina.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estudiospallione.nina.entities.Cliente;
import com.estudiospallione.nina.error.ErrorException;
import com.estudiospallione.nina.services.ClienteService;


@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteService clienteServicio;

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("")
	public String listar(Model model) {
		List<Cliente> listaClientes = clienteServicio.listarClientesAlfabeticamente();
		model.addAttribute("clientes", listaClientes);
		return "cliente-listar";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("/nuevo")
	public String nuevoCliente(Model model) {
		return "cliente-alta";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@PostMapping("/nuevo")
	public String nuevoClientePost(@RequestParam(defaultValue = "") String nombre,
			@RequestParam(defaultValue = "") String apellido, @RequestParam(defaultValue = "") String posicion,
			@RequestParam(defaultValue = "0") Long cuit, @RequestParam(defaultValue = "") String claveFiscal,
			@RequestParam(defaultValue = "") String mail, @RequestParam(defaultValue = "") String telefono,
			@RequestParam(defaultValue = "") String observaciones, Model model) throws ErrorException {

		try {
			clienteServicio.crearCliente(nombre, apellido, posicion, cuit, claveFiscal, mail, telefono, observaciones);
		} catch (ErrorException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("nombre", nombre);
			model.addAttribute("apellido", apellido);
			model.addAttribute("posicion", posicion);
			model.addAttribute("cuit", cuit);
			model.addAttribute("claveFiscal", claveFiscal);
			model.addAttribute("mail", mail);
			model.addAttribute("telefono", telefono);
			model.addAttribute("observaciones", observaciones);
			return "cliente-alta";
		}

		return "redirect:/cliente";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("/{id}")
	public String editar(Model model, @PathVariable Cliente id) {
		model.addAttribute("cliente", id);
		return "cliente-editar";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@PostMapping("/editar")
	public String editarCliente(@RequestParam Cliente id_cliente, @RequestParam(defaultValue = "") String nombre,
			@RequestParam(defaultValue = "") String apellido, @RequestParam(defaultValue = "") String posicion,
			@RequestParam(defaultValue = "0") Long cuit, @RequestParam(defaultValue = "") String claveFiscal,
			@RequestParam(defaultValue = "") String mail, @RequestParam(defaultValue = "") String telefono,
			@RequestParam(defaultValue = "") String observaciones, Model model, RedirectAttributes redirectAttrs) {

		try {
			clienteServicio.editarCliente(id_cliente, nombre, apellido, posicion, cuit, claveFiscal, mail, telefono,
					observaciones);
		} catch (ErrorException e) {
			redirectAttrs.addAttribute("id", id_cliente.getId_cliente()).addFlashAttribute("error", e.getMessage());
			redirectAttrs.addFlashAttribute("nombre", nombre);
			redirectAttrs.addFlashAttribute("apellido", apellido);
			redirectAttrs.addFlashAttribute("posicion", posicion);
			redirectAttrs.addFlashAttribute("cuit", cuit);
			redirectAttrs.addFlashAttribute("claveFiscal", claveFiscal);
			redirectAttrs.addFlashAttribute("mail", mail);
			redirectAttrs.addFlashAttribute("telefono", telefono);
			redirectAttrs.addFlashAttribute("observaciones", observaciones);
			return "redirect:/cliente/{id}";
		}
		redirectAttrs.addAttribute("id", id_cliente.getId_cliente()).addFlashAttribute("msj",
				"Actualizado correctamente.");
		return "redirect:/cliente/{id}";
	}
}
