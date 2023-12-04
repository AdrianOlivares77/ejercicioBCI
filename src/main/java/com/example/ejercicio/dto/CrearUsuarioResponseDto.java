package com.example.ejercicio.dto;

import lombok.Data;

@Data
public class CrearUsuarioResponseDto {
    private String creado;

    private String modificado;

    private String ultimoLogin;

    private String token;

    private boolean activo;
}
