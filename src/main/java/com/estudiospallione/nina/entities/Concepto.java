package com.estudiospallione.nina.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Concepto {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String idConcepto;
	private String concepto;
	public Concepto(String idConcepto, String concepto) {
		this.idConcepto = idConcepto;
		this.concepto = concepto;
	}
	public Concepto() {
	}
	public String getIdConcepto() {
		return idConcepto;
	}
	public void setIdConcepto(String idConcepto) {
		this.idConcepto = idConcepto;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	
}
