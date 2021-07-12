package com.ApiRest.Rest.V1;

import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ApiRest.Exception.CpyException;
import com.ApiRest.Manager.PersonaManager;
import com.ApiRest.Rest.V0.AuthV0;
import com.ApiRest.Rest.V0.ErrorV0;
import com.ApiRest.Rest.V0.LoginV0;
import com.ApiRest.model.Persona;
import com.ApiRest.utils.IPUtils;
import com.ApiRest.utils.JwtUtils;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping(value = "/v1/authentications", consumes = {"application/json;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
public class AuthRest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Autowired
    private transient PersonaManager personaManager;

    @Autowired
    private transient HttpServletRequest httpRequest;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthRest.class);

    @ApiOperation(value = "Permite obtener un JWT válido para consumir las otras operaciones del servicio REST")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Respuesta fue exitosa", response = AuthV0.class),
        @ApiResponse(code = 400, message = "Petición es inválida", response = ErrorV0.class),
        @ApiResponse(code = 401, message = "Acceso no autorizado", response = ErrorV0.class),
        @ApiResponse(code = 403, message = "No tiene permisos", response = ErrorV0.class),
        @ApiResponse(code = 412, message = "Falló alguna precondición", response = ErrorV0.class)
    })
    @PostMapping(value = "/login", consumes = {"application/json;charset=utf-8"}, produces = {"application/json;charset=utf-8"})
    public ResponseEntity login(@RequestBody LoginV0 request) {
        if (request == null) {
            LOGGER.error("No hay cuerpo de mensaje");
            throw new CpyException(400, "La petición es inválida");
        }
        
        final String app = StringUtils.trimToEmpty(request.getApp());
        if (StringUtils.isBlank(app)) {
            LOGGER.error("Falta el parámetro del nombre de la aplicación");
            throw new CpyException("El atributo app es requerido");
        }

        final String password = StringUtils.trimToEmpty(request.getPassword());
        if (StringUtils.isBlank(password)) {
            LOGGER.error("Falta el parámetro de la contraseña de la aplicación");
            throw new CpyException("El atributo password es requerido");
        }

        final Persona persona = personaManager.getPersonaByApp(app);
        if (!persona.isActive()) {
            LOGGER.error("Credencial NO activa");
            throw new CpyException(403, "No tiene permiso para acceder a este recurso");
        }

        boolean equals = StringUtils.equals(persona.getPassword(), password);
        if (!equals) {
            LOGGER.error("La contraseña fue incorrecta");
            throw new CpyException(401, "Credenciales inválidas");
        }

        final String ip = IPUtils.getClientIpAddress(httpRequest);
        if (!InetAddressValidator.getInstance().isValid(ip)) {
            LOGGER.error("IP NO válida, posible ataque");
            throw new CpyException(403, "No tiene permiso para acceder a este recurso");
        }

        final String jwt = JwtUtils.createJwt("/v1/authentications/login", ip, persona);
        if (StringUtils.isBlank(jwt)) {
            LOGGER.error("NO pude generar el JWT");
            throw new CpyException("No fue posible completar su petición");
        }

        return ResponseEntity.ok(new AuthV0(app));
    }
}
