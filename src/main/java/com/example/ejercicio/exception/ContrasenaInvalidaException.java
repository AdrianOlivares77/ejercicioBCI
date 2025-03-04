package com.example.ejercicio.exception;

public class ContrasenaInvalidaException extends RuntimeException{
    public ContrasenaInvalidaException(String mensaje){
        super(mensaje);
    }
}
