package com.example.ejercicio.dto;

import lombok.Data;

/**
 * Clase de response para el endpoint de creación de usuarios.
 */
@Data
public class CrearUsuarioResponseDto {
    private String creado;

    private String modificado;

    private String ultimoLogin;

    private String token;

    private boolean activo;
}
