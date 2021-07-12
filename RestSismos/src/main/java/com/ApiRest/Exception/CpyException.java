package com.ApiRest.Exception;

public class CpyException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	final Integer httpCode;

    public CpyException() {
        super("Error desconocido");
        this.httpCode = 412;
    }

    public CpyException(Integer httpCode, String message) {
        super(message);
        this.httpCode = httpCode;
    }

    public CpyException(String message) {
        super(message);
        this.httpCode = 412;
    }

    public CpyException(String message, Throwable cause) {
        super(message, cause);
        this.httpCode = 412;
    }

    public CpyException(Throwable cause) {
        super(cause);
        this.httpCode = 412;
    }

    public Integer getHttpCode() {
        return httpCode;
    }

}
