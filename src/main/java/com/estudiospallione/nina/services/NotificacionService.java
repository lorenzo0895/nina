package com.estudiospallione.nina.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class NotificacionService {

	@Autowired
	private JavaMailSender mailSender;
	
	@Async
	public void enviarMail(String cuerpo, String titulo, String mail) {
		SimpleMailMessage mensaje = new SimpleMailMessage();
		mensaje.setTo(mail);
		mensaje.setFrom("estudio2607@gmail.com");
		mensaje.setSubject(titulo);
		mensaje.setText(cuerpo);
		
		mailSender.send(mensaje);
	}
}
