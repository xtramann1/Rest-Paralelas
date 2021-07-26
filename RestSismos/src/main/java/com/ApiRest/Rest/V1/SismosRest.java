package com.ApiRest.Rest.V1;

import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.InetAddressValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.apache.commons.collections.CollectionUtils;
import com.ApiRest.Exception.CpyException;
import com.ApiRest.Manager.DatosSismosManager;
import com.ApiRest.Rest.V0.DatosV0;
import com.ApiRest.Rest.V0.ErrorV0;
import com.ApiRest.model.DatosSismos;
import com.ApiRest.utils.IPUtils;

@RestController
@RequestMapping(value = "/v1/Sismo", consumes = {"application/json;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
public class SismosRest implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private transient DatosSismosManager sismito;
	
	@Autowired
    private transient HttpServletRequest httpRequest;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SismosRest.class);
	
	@ApiOperation(value = "Permite ver los datos de los ultimos sismos")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Respuesta fue exitosa", response = DatosV0.class, responseContainer = "List"),
        @ApiResponse(code = 401, message = "Acceso no autorizado", response = ErrorV0.class),
        @ApiResponse(code = 403, message = "No tiene permisos", response = ErrorV0.class),
        @ApiResponse(code = 404, message = "No se ha encontrado la información solicitada", response = ErrorV0.class),
        @ApiResponse(code = 412, message = "Falló alguna precondición", response = ErrorV0.class)
    })
	@CrossOrigin(origins = "http://localhost:8100")
	@GetMapping(value = "/listar", consumes = {"*/*"}, produces = {"application/json;charset=utf-8"})
	public ResponseEntity getAll() throws IOException, ParseException{
        final String ip = IPUtils.getClientIpAddress(httpRequest);
        if (!InetAddressValidator.getInstance().isValid(ip)) {
            LOGGER.error("IP NO válida, posible ataque");
            throw new CpyException(403, "No tiene permiso para acceder a este recurso");
        }
        sismito.extraerdatos();
        List<DatosSismos> sismos = sismito.getAllSismos();
        if (CollectionUtils.isEmpty(sismos)) {
            LOGGER.error("Lista de sismos vacía");
            throw new CpyException(404, "No se encontraron datos");
        }

        List<DatosV0> resultList = new ArrayList<>();
        for (DatosSismos datos : sismos) {
            resultList.add(new DatosV0(datos));
        }
        sismos.clear();
        return ResponseEntity.ok(resultList);
	}
}
