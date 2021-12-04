package com.estudiospallione.nina.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id_usuario;
	private String username;
	private String nombre;
	private String password;
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_alta;
	private Boolean permiso_abrir_dias;
	private Boolean permiso_cerrar_dias;
	private Boolean permiso_ingresar;
	private Boolean permiso_modificar;
	private Boolean permiso_cerrar_modificaciones;
	private Boolean permiso_modificar_usuarios;
	private Boolean activo;
	public Usuario(String id_usuario, String username, String nombre, String password, Date fecha_alta, Boolean permiso_abrir_dias,
			Boolean permiso_cerrar_dias, Boolean permiso_ingresar, Boolean permiso_modificar,
			Boolean permiso_cerrar_modificaciones, Boolean permiso_modificar_usuarios ,Boolean activo) {
		this.id_usuario = id_usuario;
		this.username = username;
		this.nombre = nombre;
		String encriptada = new BCryptPasswordEncoder().encode(password);
		this.password = encriptada;
		this.fecha_alta = fecha_alta;
		this.permiso_abrir_dias = permiso_abrir_dias;
		this.permiso_cerrar_dias = permiso_cerrar_dias;
		this.permiso_ingresar = permiso_ingresar;
		this.permiso_modificar = permiso_modificar;
		this.permiso_cerrar_modificaciones = permiso_cerrar_modificaciones;
		this.permiso_modificar_usuarios = permiso_modificar_usuarios;
		this.activo = activo;
	}
	public Usuario() {
	}
	public String getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(String id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	public Boolean getPermiso_abrir_dias() {
		return permiso_abrir_dias;
	}
	public void setPermiso_abrir_dias(Boolean permiso_abrir_dias) {
		this.permiso_abrir_dias = permiso_abrir_dias;
	}
	public Boolean getPermiso_cerrar_dias() {
		return permiso_cerrar_dias;
	}
	public void setPermiso_cerrar_dias(Boolean permiso_cerrar_dias) {
		this.permiso_cerrar_dias = permiso_cerrar_dias;
	}
	public Boolean getPermiso_ingresar() {
		return permiso_ingresar;
	}
	public void setPermiso_ingresar(Boolean permiso_ingresar) {
		this.permiso_ingresar = permiso_ingresar;
	}
	public Boolean getPermiso_modificar() {
		return permiso_modificar;
	}
	public void setPermiso_modificar(Boolean permiso_modificar) {
		this.permiso_modificar = permiso_modificar;
	}
	public Boolean getPermiso_cerrar_modificaciones() {
		return permiso_cerrar_modificaciones;
	}
	public void setPermiso_cerrar_modificaciones(Boolean permiso_cerrar_modificaciones) {
		this.permiso_cerrar_modificaciones = permiso_cerrar_modificaciones;
	}
	public Boolean getPermiso_modificar_usuarios() {
		return permiso_modificar_usuarios;
	}
	public void setPermiso_modificar_usuarios(Boolean permiso_modificar_usuarios) {
		this.permiso_modificar_usuarios = permiso_modificar_usuarios;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "Usuario [id_usuario=" + id_usuario + ", username=" + username + ", nombre=" + nombre + ", password="
				+ password + ", fecha_alta=" + fecha_alta + ", permiso_abrir_dias=" + permiso_abrir_dias
				+ ", permiso_cerrar_dias=" + permiso_cerrar_dias + ", permiso_ingresar=" + permiso_ingresar
				+ ", permiso_modificar=" + permiso_modificar + ", permiso_cerrar_modificaciones="
				+ permiso_cerrar_modificaciones + ", permiso_modificar_usuarios=" + permiso_modificar_usuarios
				+ ", activo=" + activo + "]";
	}
	
}
