package com.estudiospallione.nina.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id_cliente;
	private String nombre;
	private String apellido;
	private String posicion;
	private Long cuit;
	private String claveFiscal;
	private String mail;
	private String telefono;
	private String observaciones;
	private Boolean activo;
	public Cliente(String id_cliente, String nombre, String apellido, String posicion, Long cuit, String claveFiscal,
			String mail, String telefono, String observaciones, Boolean activo) {
		this.id_cliente = id_cliente;
		this.nombre = nombre;
		this.apellido = apellido;
		this.posicion = posicion;
		this.cuit = cuit;
		this.claveFiscal = claveFiscal;
		this.mail = mail;
		this.telefono = telefono;
		this.observaciones = observaciones;
		this.activo = activo;
	}
	public Cliente() {
	}
	public String getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(String id_cliente) {
		this.id_cliente = id_cliente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public Long getCuit() {
		return cuit;
	}
	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}
	public String getClaveFiscal() {
		return claveFiscal;
	}
	public void setClaveFiscal(String claveFiscal) {
		this.claveFiscal = claveFiscal;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "Cliente [id_cliente=" + id_cliente + ", nombre=" + nombre + ", apellido=" + apellido + ", posicion="
				+ posicion + ", cuit=" + cuit + ", claveFiscal=" + claveFiscal + ", mail=" + mail + ", telefono="
				+ telefono + ", observaciones=" + observaciones + ", activo=" + activo + "]";
	}
	
	
	
}
