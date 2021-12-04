package com.estudiospallione.nina.entities;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Fecha {

	@Id
	@Temporal(TemporalType.DATE)
	private Calendar fecha;
	private Float sobrante;
	@Column(nullable = false)
	private Boolean activo;
	public Fecha(Calendar fecha, Float sobrante, Boolean activo) {
		super();
		this.fecha = fecha;
		this.sobrante = sobrante;
		this.activo = activo;
	}
	public Fecha() {
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public Float getSobrante() {
		return sobrante;
	}
	public void setSobrante(Float sobrante) {
		this.sobrante = sobrante;
	}
	public Boolean getActivo() {
		return activo;
	}
	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "Fecha [fecha=" + fecha + ", sobrante=" + sobrante + ", activo=" + activo + "]";
	}
	
}
