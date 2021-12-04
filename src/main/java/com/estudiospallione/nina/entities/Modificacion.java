package com.estudiospallione.nina.entities;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Modificacion {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idModificacion;
	@ManyToOne
	private Usuario usuario;
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar datetime;
	@ManyToOne
	private Caja caja;
	public Modificacion(String idModificacion, Usuario usuario, Calendar datetime, Caja caja) {
		this.idModificacion = idModificacion;
		this.usuario = usuario;
		this.datetime = datetime;
		this.caja = caja;
	}
	public Modificacion() {
	}
	public String getIdModificacion() {
		return idModificacion;
	}
	public void setIdModificacion(String idModificacion) {
		this.idModificacion = idModificacion;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Calendar getDatetime() {
		return datetime;
	}
	public void setDatetime(Calendar datetime) {
		this.datetime = datetime;
	}
	public Caja getCaja() {
		return caja;
	}
	public void setCaja(Caja caja) {
		this.caja = caja;
	}
	@Override
	public String toString() {
		return "Modificacion [idModificacion=" + idModificacion + ", usuario=" + usuario + ", datetime=" + datetime
				+ ", caja=" + caja + "]";
	}
	
	
}
