package com.example.ejercicio.service.dto;

import lombok.Data;

@Data
public class CrearUsuarioResponse {

    private String creado;

    private String modificado;

    private String ultimoLogin;

    private String token;
    private boolean activo;

}
