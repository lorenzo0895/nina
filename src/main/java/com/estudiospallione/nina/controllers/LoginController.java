package com.estudiospallione.nina.controllers;

import javax.servlet.http.HttpServletRequest;
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

import com.estudiospallione.nina.entities.Usuario;
import com.estudiospallione.nina.error.ErrorException;
import com.estudiospallione.nina.services.UsuarioService;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	UsuarioService usuarioServicio;

	// @PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("/")
	public String inicio(Model model, RedirectAttributes redirectAttributes, HttpSession httpSession,
			HttpServletRequest request) {
		Usuario usuario = (Usuario) httpSession.getAttribute("usersession");
		if (usuario != null && !usuario.getActivo()) {
			redirectAttributes.addFlashAttribute("error",
					"Su usuario no está activo. Solicite permiso al administrador.");
			HttpSession session = request.getSession(false);
			if (session != null) {
				session.invalidate();
			}
			return "redirect:/login";
		}
		return "redirect:/aft";
	}

	@GetMapping("/login")
	public String login(HttpSession httpSession, HttpServletRequest request,
			@RequestParam(required = false) String error, Model model) {

		// Si el usuario no tiene las credenciales matamos la sesión y enviamos
		// por modelo el mensaje de error
		HttpSession session = request.getSession(false);
		if (error != null) {
			model.addAttribute("error", "Nombre de usuario o clave incorrectos.");
			if (session != null) {
				session.invalidate();
			}
			return "login";
		} else {
			// Si el usuario ya está logeado redirigimos al index
			Usuario usuario = (Usuario) session.getAttribute("usersession");
			if (usuario != null) {
				return "redirect:/";
			}
			return "login";
		}
	}

	@GetMapping("/logout")
	public String logout() {
		return "login";
	}

	@GetMapping("/new-user")
	public String newUser() {
		return "usuario-alta";
	}

	@PostMapping("/new-user")
	public String crearUser(Model model, @RequestParam String username, @RequestParam String nombre,
			@RequestParam String password1, @RequestParam String password2,
			@RequestParam(defaultValue = "false") Boolean permiso_abrir_dias,
			@RequestParam(defaultValue = "false") Boolean permiso_cerrar_dias,
			@RequestParam(defaultValue = "false") Boolean permiso_ingresar,
			@RequestParam(defaultValue = "false") Boolean permiso_modificar,
			@RequestParam(defaultValue = "false") Boolean permiso_cerrar_modificaciones,
			@RequestParam(defaultValue = "false") Boolean permiso_modificar_usuarios) throws ErrorException {
		try {
			usuarioServicio.crearUsuario(username, nombre, password1, password2, permiso_abrir_dias,
					permiso_cerrar_dias, permiso_ingresar, permiso_modificar, permiso_cerrar_modificaciones,
					permiso_modificar_usuarios);
			return "login";
		} catch (ErrorException e) {
			model.addAttribute("error", e.getMessage());
			model.addAttribute("username", username);
			model.addAttribute("nombre", nombre);
			model.addAttribute("password1", password1);
			model.addAttribute("password2", password2);
			return "usuario-alta";
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@GetMapping("/perfil")
	public String perfil(HttpSession httpSession, Model model) {
		Usuario usuario = (Usuario) httpSession.getAttribute("usersession");
		model.addAttribute("usuario", usuario);
		return "usuario-perfil";
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@PostMapping("/editarPerfil")
	public String editarPerfil(HttpSession httpSession, RedirectAttributes redirectAttributes,
			@RequestParam String username, @RequestParam String nombre) {
		try {
			Usuario usuario = (Usuario) httpSession.getAttribute("usersession");
			usuarioServicio.editarPerfil(usuario, username, nombre);
			redirectAttributes.addFlashAttribute("msj", "Datos actualizados correctamente.");
			return "redirect:/perfil";
		} catch (ErrorException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			redirectAttributes.addFlashAttribute("username", username);
			redirectAttributes.addFlashAttribute("nombre", nombre);
			return "redirect:/perfil";
		}
	}

	@PreAuthorize("hasAnyRole('ROLE_ACTIVO')")
	@PostMapping("/cambioPaswword")
	public String editarPassword(HttpSession httpSession, RedirectAttributes redirectAttributes,
			@RequestParam String passwordAnterior, @RequestParam String password1, @RequestParam String password2) {
		try {
			Usuario usuario = (Usuario) httpSession.getAttribute("usersession");
			usuarioServicio.editarPassword(usuario, passwordAnterior, password1, password2);
			redirectAttributes.addFlashAttribute("msj", "Contraseña actualizada correctamente.");
			return "redirect:/perfil";
		} catch (ErrorException e) {
			redirectAttributes.addFlashAttribute("error", e.getMessage());
			return "redirect:/perfil";
		}
	}
}
