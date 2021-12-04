package com.estudiospallione.nina.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.estudiospallione.nina.entities.Usuario;
import com.estudiospallione.nina.error.ErrorException;
import com.estudiospallione.nina.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	UsuarioRepository repo;

	@Autowired
	NotificacionService notificacionServicio;

	@Transactional
	public void crearUsuario(String username, String nombre, String password1, String password2,
			Boolean permiso_abrir_dias, Boolean permiso_cerrar_dias, Boolean permiso_ingresar,
			Boolean permiso_modificar, Boolean permiso_cerrar_modificaciones, Boolean permiso_modificar_usuarios)
			throws ErrorException {
		if (buscarPorUsuario(username) != null) {
			throw new ErrorException("El usuario ya existe.");
		}
		if (username == null || username.isEmpty()) {
			throw new ErrorException("El usuario no puede ser nulo.");
		}
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorException("El nombre no puede ser nulo.");
		}
		if (password1 == null || password1.isEmpty()) {
			throw new ErrorException("Debe ingresar ambas contraseñas.");
		}
		if (password2 == null || password2.isEmpty()) {
			throw new ErrorException("Debe ingresar ambas contraseñas.");
		}
		if (!password1.equals(password2)) {
			throw new ErrorException("Las contraseñas no son iguales.");
		}
		Usuario usuario1 = new Usuario();
		usuario1.setUsername(username);
		usuario1.setNombre(nombre);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario1.setPassword(encoder.encode(password1));
		usuario1.setFecha_alta(new Date());
		usuario1.setPermiso_abrir_dias(permiso_abrir_dias);
		usuario1.setPermiso_cerrar_dias(permiso_cerrar_dias);
		usuario1.setPermiso_ingresar(permiso_ingresar);
		usuario1.setPermiso_modificar(permiso_modificar);
		usuario1.setPermiso_cerrar_modificaciones(permiso_cerrar_modificaciones);
		usuario1.setPermiso_modificar_usuarios(permiso_modificar_usuarios);
		usuario1.setActivo(false);
		System.out.println(usuario1);
		repo.save(usuario1);
	}

	@Transactional
	public void editarAtributosUsuario(Usuario usuario1, String username, String nombre, Boolean permiso_abrir_dias,
			Boolean permiso_cerrar_dias, Boolean permiso_ingresar, Boolean permiso_modificar,
			Boolean permiso_cerrar_modificaciones, Boolean permiso_modificar_usuarios, Boolean activo)
			throws ErrorException {
		if (buscarPorUsuario(username) != null && !usuario1.getUsername().equals(username)) {
			throw new ErrorException("El usuario ya existe.");
		}
		if (username == null || username.isEmpty()) {
			throw new ErrorException("El usuario no puede ser nulo.");
		}
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorException("El nombre no puede ser nulo.");
		}

		usuario1.setUsername(username);
		usuario1.setNombre(nombre);
		usuario1.setPermiso_abrir_dias(permiso_abrir_dias);
		usuario1.setPermiso_cerrar_dias(permiso_cerrar_dias);
		usuario1.setPermiso_ingresar(permiso_ingresar);
		usuario1.setPermiso_modificar(permiso_modificar);
		usuario1.setPermiso_cerrar_modificaciones(permiso_cerrar_modificaciones);
		usuario1.setPermiso_modificar_usuarios(permiso_modificar_usuarios);
		usuario1.setActivo(activo);
		repo.save(usuario1);
	}

	@Transactional
	public void editarPerfil(Usuario usuario1, String username, String nombre) throws ErrorException {
		if (buscarPorUsuario(username) != null && !usuario1.getUsername().equals(username)) {
			throw new ErrorException("El usuario ya existe.");
		}
		if (username == null || username.isEmpty()) {
			throw new ErrorException("El usuario no puede ser nulo.");
		}
		if (nombre == null || nombre.isEmpty()) {
			throw new ErrorException("El nombre no puede ser nulo.");
		}
		usuario1.setUsername(username);
		usuario1.setNombre(nombre);
		repo.save(usuario1);
	}

	@Transactional
	public void editarPassword(Usuario usuario1, String passwordAnterior, String password1, String password2)
			throws ErrorException {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (!encoder.matches(passwordAnterior, usuario1.getPassword())) {
			throw new ErrorException("La contraseña actual es incorrecta.");
		}
		if (password1 == null || password1.isEmpty()) {
			throw new ErrorException("Debe ingresar ambas contraseñas.");
		}
		if (password1 == null || password1.isEmpty()) {
			throw new ErrorException("Debe ingresar ambas contraseñas.");
		}
		if (password2 == null || password2.isEmpty()) {
			throw new ErrorException("Debe ingresar ambas contraseñas.");
		}
		if (!password1.equals(password2)) {
			throw new ErrorException("Las contraseñas no son iguales.");
		}
		usuario1.setPassword(encoder.encode(password1));
		repo.save(usuario1);
	}

	public Usuario buscarPorUsuario(String username) {
		Optional<Usuario> optional = Optional.ofNullable(repo.findByUsername(username));
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	public List<Usuario> listarUsuarios() {
		return repo.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = repo.findByUsername(username);
		if (usuario != null) {
			List<GrantedAuthority> permisos = new ArrayList<>();
			if (usuario.getActivo()) {
				GrantedAuthority activo = new SimpleGrantedAuthority("ROLE_ACTIVO");
				permisos.add(activo);
			}

			// Guardamos sus atributos
			ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session = attr.getRequest().getSession(true);
			session.setAttribute("usersession", usuario);

			User user = new User(usuario.getUsername(), usuario.getPassword(), permisos);
			return user;
		} else {
			return null;
		}
	}

}
