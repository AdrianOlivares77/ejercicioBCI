package com.example.ejercicio.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidacionExceptions(MethodArgumentNotValidException ex) {
        Map<String,String> errors =  new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String nombreCampo = ((FieldError) error).getField();
            String mensaje = error.getDefaultMessage();
            errors.put("mensaje", mensaje);
        });
        return errors;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException.class)
    public Map<String, String> handleCorreoUnicoException(org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException ex) {
        Map<String,String> errors =  new HashMap<String, String>();
        errors.put("mensaje", "el correo ya esta registrado");
        return errors;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ContrasenaInvalidaException.class)
    public Map<String, String> handleContrasenaPatternException(ContrasenaInvalidaException ex) {
        Map<String,String> errors =  new HashMap<String, String>();
        errors.put("mensaje", ex.getMessage());
        return errors;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UsuarioNoExisteException.class)
    public Map<String, String> handleUsuarioNoExisteException(UsuarioNoExisteException ex) {
        Map<String,String> errors =  new HashMap<String, String>();
        errors.put("mensaje", ex.getMessage());
        return errors;
    }
}
