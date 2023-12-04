package com.example.ejercicio.dto;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

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
