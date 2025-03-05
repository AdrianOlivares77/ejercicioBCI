package com.example.ejercicio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase para obtener datos del pattern de validacion de contraseña.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatternMensajeDto {
    private String pattern;
    private String mensaje;
}
