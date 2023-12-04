package com.example.ejercicio.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatternMensajeDto {
    private String pattern;
    private String mensaje;
}
