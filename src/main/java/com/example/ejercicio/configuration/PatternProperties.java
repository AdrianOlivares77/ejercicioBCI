package com.example.ejercicio.configuration;

import com.example.ejercicio.dto.PatternMensajeDto;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * Clase que contiene los properties de un archivo yml.
 */
@Data
@Configuration
@ConfigurationProperties (prefix = "patterns")
public class PatternProperties {
    private PatternMensajeDto passwordUsuarioPattern;
}
