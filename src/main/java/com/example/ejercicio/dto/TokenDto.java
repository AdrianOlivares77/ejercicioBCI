package com.example.ejercicio.dto;

import javax.persistence.Embeddable;
import lombok.Data;

/**
 * Clase de Token.
 */
@Data
@Embeddable
public class TokenDto {
    private String jwt;
}
