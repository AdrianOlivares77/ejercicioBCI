package com.example.ejercicio.exception;

public class ContraseñaInvalidaException extends RuntimeException{
    public ContraseñaInvalidaException (String mensaje){
        super(mensaje);
    }
}
