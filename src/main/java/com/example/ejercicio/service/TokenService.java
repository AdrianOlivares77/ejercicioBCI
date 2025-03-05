package com.example.ejercicio.service;

/**
 * Interface para el servicio de Token.
 */
public interface TokenService {
    String getJWTToken(String username);
}
