package com.estudiospallione.nina.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorsController implements ErrorController {

	@RequestMapping(value = "/error", method = {RequestMethod.GET, RequestMethod.POST})
	public String mostrarPaginaErrores(Model model, HttpServletRequest httpServletRequest) {
		String mensajeError = "";
		int codigo = (int)httpServletRequest.getAttribute("javax.servlet.error.status_code");
		switch (codigo) {
		case 400:
			mensajeError = "El recurso solicitado no existe.";
			break;
		case 401:
			mensajeError = "No se encuentra autorizado.";
			break;
		case 403:
			mensajeError = "No tiene permiso para acceder al recuros.";
			break;
		case 404:
			mensajeError = "El recurso solicitado no se ha encontrado.";
			break;
		case 500:
			mensajeError = "El recurso solicitado no existe.";
			break;
		default:
			mensajeError = "El servidor no pudo realizar la petición con exito.";
			break;
		}
		model.addAttribute("codigo", codigo);
		model.addAttribute("mensaje", mensajeError);
		return "error";
	}
	
}
