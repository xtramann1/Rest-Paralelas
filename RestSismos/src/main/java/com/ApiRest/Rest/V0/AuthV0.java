package com.ApiRest.Rest.V0;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel(value = "auth")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AuthV0 implements Serializable{
	private static final long serialVersionUID = 8193165598058334208L;

    private String bearer = null;

    public AuthV0() {
    }

    public AuthV0(String bearer) {
        this.bearer = bearer;
    }

    @ApiModelProperty(value = "El token usado para autenticar las operaciones",
            required = true, example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
    public String getBearer() {
        return bearer;
    }

    public void setBearer(String bearer) {
        this.bearer = bearer;
    }
}
