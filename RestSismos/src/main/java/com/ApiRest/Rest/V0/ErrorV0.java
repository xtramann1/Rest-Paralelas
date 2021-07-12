package com.ApiRest.Rest.V0;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "error")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorV0 {
	public String message = null;
    public LocalDateTime date = LocalDateTime.now();

    public ErrorV0() {
        this.message = "Error genérico o desconocido";
        this.date = LocalDateTime.now();
    }

    public ErrorV0(String message) {
        this.message = message;
        this.date = LocalDateTime.now();
    }

    @ApiModelProperty(value = "Mensaje descriptivo con el error",
            required = true, example = "Error al validar")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @ApiModelProperty(value = "Fecha en que ocurre el error en el servidor",
            required = true)
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
