package com.example.ejercicio.exception;

import org.h2.message.DbException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.PersistenceException;
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
            errors.put("mensaje", nombreCampo + " " + mensaje);
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
    @ExceptionHandler(ContraseñaInvalidaException.class)
    public Map<String, String> handleContraseñaPatternException(ContraseñaInvalidaException ex) {
        Map<String,String> errors =  new HashMap<String, String>();
        errors.put("mensaje", ex.getMessage());
        return errors;
    }
}
