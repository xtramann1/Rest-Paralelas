package com.ApiRest.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "Sismos")
public class DatosSismos implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk", nullable = false)
    private Long id = null;
	
	@Column(name = "ide", nullable = false)
	private int idd = 0;
	
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column(name = "FechaLocal", nullable = false)
    private Date FechaLocal = null;
    
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @Column(name = "FechaUTC", nullable = false)
    private Date FechaUTC = null;

	@Column(name = "Latitud", nullable = false)
    private Double Latitud = null;

	@Column(name = "Longitud", nullable = false)
    private Double Longitud = null;
    
	@Column(name = "Profundidad", nullable = false)
    private Integer Profundidad = null;
    
	@Column(name = "Magnitud", nullable = false)
    private Double Magnitud = null;
    
    @Column(name = "Referencia", nullable = false)
    private String Referencia_Geografica = null;
    
    public DatosSismos(int idd, Date FechaLocal, Date FechaUTC, Double Latitud, Double Longitud, Integer Profundidad, Double Magnitud, String Referencia_Geografica) {
    	this.idd = idd;
    	this.FechaLocal = FechaLocal;
    	this.FechaUTC = FechaUTC;
    	this.Latitud = Latitud;
    	this.Longitud = Longitud;
        this.Profundidad = Profundidad;
        this.Magnitud = Magnitud;
        this.Referencia_Geografica = Referencia_Geografica;
    }

    public DatosSismos() {
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getIdd() {
		return idd;
	}

	public void setIdd(int idd) {
		this.idd = idd;
	}

	public Date getFechaLocal() {
		return FechaLocal;
	}

	public void setFechaLocal(Date FechaLocal) {
		this.FechaLocal = FechaLocal;
	}

	public Date getFechaUTC() {
		return FechaUTC;
	}

	public void setFechaUTC(Date FechaUTC) {
		this.FechaUTC = FechaUTC;
	}

	public Double getLatitud() {
		return Latitud;
	}

	public void setLatitud(Double Latitud) {
		this.Latitud = Latitud;
	}

	public Double getLongitud() {
		return Longitud;
	}

	public void setLongitud(Double Longitud) {
		this.Longitud = Longitud;
	}

	public Integer getProfundidad() {
		return Profundidad;
	}

	public void setProfundidad(Integer Profundidad) {
		this.Profundidad = Profundidad;
	}

	public Double getMagnitud() {
		return Magnitud;
	}

	public void setMagnitud(Double Magnitud) {
		this.Magnitud = Magnitud;
	}

	public String getReferencia_Geografica() {
		return Referencia_Geografica;
	}

	public void setReferencia_Geografica(String Referencia_Geografica) {
		this.Referencia_Geografica = Referencia_Geografica;
	}
    
    
}
