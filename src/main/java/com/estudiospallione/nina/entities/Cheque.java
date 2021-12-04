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
public class Cheque {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id_cheque;
	private String numero;
	@Temporal(TemporalType.DATE)
	private Calendar fecha;
	private String banco;
	private String sucursal;
	private Long cuit;
	private Float importe;
	@ManyToOne()
	private Caja caja;
	
	public Cheque() {
	}
	
	public Cheque(String id_cheque, String numero, Calendar fecha, String banco, String sucursal, Long cuit,
			Float importe, Caja caja) {
		this.id_cheque = id_cheque;
		this.numero = numero;
		this.fecha = fecha;
		this.banco = banco;
		this.sucursal = sucursal;
		this.cuit = cuit;
		this.importe = importe;
		this.caja = caja;
	}

	public String getId_cheque() {
		return id_cheque;
	}
	public void setId_cheque(String id_cheque) {
		this.id_cheque = id_cheque;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Calendar getFecha() {
		return fecha;
	}
	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public Long getCuit() {
		return cuit;
	}
	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}
	public Float getImporte() {
		return importe;
	}
	public void setImporte(Float importe) {
		this.importe = importe;
	}
	public Caja getCaja() {
		return caja;
	}
	public void setCaja(Caja caja) {
		this.caja = caja;
	}

	@Override
	public String toString() {
		return "Cheque id_cheque=" + id_cheque;
	}
	
	
}
