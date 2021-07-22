package com.ApiRest.Rest.V0;



import java.util.Date;

import com.ApiRest.model.DatosSismos;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.StringUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "datosSismos")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DatosV0 {
	private int idd = 0;
	private Date fechalocal= null;
	private Date fechautc = null;
	private Double latitud = 0.0;
	private Double longitud = 0.0;
	private Integer profundidad = 0;
	private Double magnitud = 0.0;
	private String referencia = null;
	
	public DatosV0() {
		
	}
	
	public DatosV0(DatosSismos sismito) {
		
		this.idd = sismito.getIdd();
        this.fechalocal = sismito.getFechaLocal();
        this.fechautc = sismito.getFechaUTC();
        this.referencia = StringUtils.upperCase(StringUtils.normalizeSpace(StringUtils.trimToEmpty(sismito.getReferencia_Geografica())));
        this.latitud = sismito.getLatitud();
        this.longitud = sismito.getLongitud();
        this.profundidad = sismito.getProfundidad();
        this.magnitud = sismito.getMagnitud();
    }
	
	@ApiModelProperty(value = "Fecha local",required = true)
	public Date getFechaLocal() {
		return fechalocal;
	}
	
	public void setFechaLocal(Date fechalocal) {
		this.fechalocal = fechalocal;
	}
	
	@ApiModelProperty(value = "ide",required = true)
	public int getIdd() {
		return idd;
	}

	public void setIdd(int idd) {
		this.idd = idd;
	}
	
	@ApiModelProperty(value = "Fecha utc",required = true)
	public Date getFechaUTC() {
		return fechautc;
	}

	public void setFechaUTC(Date fechautc) {
		this.fechautc = fechautc;
	}

	@ApiModelProperty(value = "latitud sector", required = true, example = "-20.523")
	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}
	
	@ApiModelProperty(value = "longitud sector", required = true, example = "-69.54")
	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
	
	@ApiModelProperty(value = "profundidad sector", required = true, example = "12")
	public Integer getProfundidad() {
		return profundidad;
	}

	public void setProfundidad(Integer profundidad) {
		this.profundidad = profundidad;
	}
	
	@ApiModelProperty(value = "magnitud sector", required = true, example = "3.1")
	public Double getMagnitud() {
		return magnitud;
	}

	public void setMagnitud(Double magnitud) {
		this.magnitud = magnitud;
	}
	
	@ApiModelProperty(value = "referencia geografica", required = true, example = "78 km al S de Socaire")
	public String getReferencia_Geografica() {
		return referencia;
	}

	public void setReferencia_Geografica(String referencia) {
		this.referencia = referencia;
	}
}
