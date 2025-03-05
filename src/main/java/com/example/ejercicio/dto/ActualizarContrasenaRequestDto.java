package com.example.ejercicio.dto;

import lombok.Data;

/**
 * Clase de request para el endpoint de actualizacion de contraseña.
 */
@Data
public class ActualizarContrasenaRequestDto {
    private String contrasenia;
}
