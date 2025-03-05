package com.example.ejercicio.dto;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


/**
 * Clase Telefono con sus datos.
 */
@Data
@Embeddable
public class TelefonoDto {

    @NotEmpty(message = "Campo numero no puede estar vacio")
    private String numero;

    @NotEmpty(message = "Campo codigo ciudad no puede estar vacio")
    private String codigoCiudad;

    @NotEmpty(message = "Campo codigo pais no puede estar vacio")
    private String codigoPais;
}
