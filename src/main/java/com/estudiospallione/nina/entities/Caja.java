package com.estudiospallione.nina.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Caja {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long idCaja;
	@ManyToOne
	private Fecha fecha;
	@ManyToOne
	private Cliente cliente;
	private String detalle;
	private Double efectivo;
	private Double transferencia;
	@OneToMany(mappedBy = "caja")
	private List<Cheque> listaCheques;
	@OneToMany(mappedBy = "caja")
	private List<Modificacion> listaModificaciones;
	private Boolean activo;

	public Caja() {
	}
	public Caja(Long idCaja, Fecha fecha, Cliente cliente, String detalle, Double efectivo, Double transferencia,
			List<Cheque> listaCheques, List<Modificacion> listaModificaciones, Boolean activo) {
		super();
		this.idCaja = idCaja;
		this.fecha = fecha;
		this.cliente = cliente;
		this.detalle = detalle;
		this.efectivo = efectivo;
		this.transferencia = transferencia;
		this.listaCheques = listaCheques;
		this.listaModificaciones = listaModificaciones;
		this.activo = activo;
	}
	public Long getIdCaja() {
		return idCaja;
	}

	public void setIdCaja(Long idCaja) {
		this.idCaja = idCaja;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Double getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(Double efectivo) {
		this.efectivo = efectivo;
	}

	public Double getTransferencia() {
		return transferencia;
	}

	public void setTransferencia(Double transferencia) {
		this.transferencia = transferencia;
	}

	public List<Cheque> getListaCheques() {
		return listaCheques;
	}

	public void setListaCheques(List<Cheque> listaCheques) {
		this.listaCheques = listaCheques;
	}

	public List<Modificacion> getListaModificaciones() {
		return listaModificaciones;
	}

	public void setListaModificaciones(List<Modificacion> listaModificaciones) {
		this.listaModificaciones = listaModificaciones;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}
	@Override
	public String toString() {
		return "Caja [idCaja=" + idCaja + ", fecha=" + fecha + ", cliente=" + cliente + ", detalle=" + detalle
				+ ", efectivo=" + efectivo + ", transferencia=" + transferencia + ", listaCheques=" + listaCheques
				+ ", listaModificaciones=" + listaModificaciones + ", activo=" + activo + "]";
	}
	
	

}
