package com.example.ejercicio.exception;

/**
 * Clase de exception personalizada para el caso en que un Usuario no exista.
 */
public class UsuarioNoExisteException extends RuntimeException{
    public UsuarioNoExisteException (String mensaje){
        super(mensaje);
    }
}