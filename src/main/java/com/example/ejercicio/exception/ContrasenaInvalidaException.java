package com.example.ejercicio.exception;

/**
 * Clase de exception personalizada para el caso de una contraseña invalida.
 */
public class ContrasenaInvalidaException extends RuntimeException{
    public ContrasenaInvalidaException(String mensaje){
        super(mensaje);
    }
}
