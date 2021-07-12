package com.ApiRest.Rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ApiRest.Exception.CpyException;
import com.ApiRest.Rest.V0.ErrorV0;

@ControllerAdvice(basePackages = {"com.ApiRest.Rest"})
public class ManejadorErrores {
	 private static final Logger LOGGER = LoggerFactory.getLogger(ManejadorErrores.class);

	    @ExceptionHandler({CpyException.class})
	    public ResponseEntity handleException(CpyException e) {
	        LOGGER.error("Se ha atrapado un error en la ejecución: {}", e.getMessage());

	        final HttpStatus error = HttpStatus.valueOf(e.getHttpCode());
	        final ErrorV0 response = new ErrorV0(e.getMessage());

	        return new ResponseEntity<>(response, error);
	    }

	    @ExceptionHandler({Exception.class})
	    public ResponseEntity handleException(Exception e) {
	        LOGGER.error("Se ha atrapado un error en la ejecución: {}", e.getMessage());
	        LOGGER.debug("Se ha atrapado un error en la ejecución: {}", e.getMessage(), e);

	        final HttpStatus error = HttpStatus.INTERNAL_SERVER_ERROR;
	        final ErrorV0 response = new ErrorV0(e.getMessage());

	        return new ResponseEntity<>(response, error);
	    }
}
