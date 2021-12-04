package com.estudiospallione.nina.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.estudiospallione.nina.entities.Usuario;
import com.estudiospallione.nina.error.ErrorException;
import com.estudiospallione.nina.services.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;
	

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("")
	public String listarUsuarios(HttpSession httpSession, Model model) {
		Usuario usuario = (Usuario) httpSession.getAttribute("usersession");
		if (!usuario.getPermiso_modificar_usuarios()) {
			return "redirect:/";
		}
		List<Usuario> listaUsuarios = usuarioService.listarUsuarios();
		model.addAttribute("usuarios", listaUsuarios);
		return "usuario-listar";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("/editar/{id_usuario}")
	public String editar(HttpSession httpSession, Model model, @PathVariable Usuario id_usuario) {
		Usuario usuario = (Usuario) httpSession.getAttribute("usersession");
		if (!usuario.getPermiso_modificar_usuarios()) {
			return "redirect:/";
		}
		model.addAttribute("usuario", id_usuario);
		return "usuario-modificar-admin";
	}
	
	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@PostMapping("/editarUsuario")
	public String editarAtributos(HttpSession httpSession, Model model, 
			HttpServletRequest request,
			@RequestParam Usuario id_usuario,
			@RequestParam String username,
			@RequestParam String nombre,
			@RequestParam(defaultValue = "false") Boolean permiso_abrir_dias,
			@RequestParam(defaultValue = "false") Boolean permiso_cerrar_dias,
			@RequestParam(defaultValue = "false") Boolean permiso_ingresar,
			@RequestParam(defaultValue = "false") Boolean permiso_modificar,
			@RequestParam(defaultValue = "false") Boolean permiso_cerrar_modificaciones,
			@RequestParam(defaultValue = "false") Boolean permiso_modificar_usuarios,
			@RequestParam(defaultValue = "false") Boolean activo) 
					throws ErrorException {
		try {
			usuarioService.editarAtributosUsuario(
					id_usuario, username, nombre, permiso_abrir_dias, permiso_cerrar_dias, 
					permiso_ingresar, permiso_modificar, permiso_cerrar_modificaciones,
					permiso_modificar_usuarios, activo);
			

			//BORRAMOS LA SESIÓN
//			HttpSession session = request.getSession(false);
//			if (session != null) {
//				session.invalidate();
//			}
//			
//			//DAMOS DE ALTA LA SESIÓN DE NUEVO
//			usuarioService.loadUserByUsername(usuario.getUsername());

			return "redirect:/usuario";
		} catch (ErrorException e) {
//			rv.setUrl("redirect:/editar/" + id_usuario.getId_usuario() + "?" + e.getMessage());
			return "redirect:/usuario/editar/" + id_usuario.getId_usuario() + "?error=" + e.getMessage();
		}
		
	}
}
