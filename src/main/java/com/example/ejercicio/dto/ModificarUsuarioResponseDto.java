package com.example.ejercicio.dto;

import lombok.Data;

import java.util.List;

@Data
public class ModificarUsuarioResponseDto {

    private String id;

    private String nombre;

    private String correo;

    private String contrasenia;

    private List<TelefonoDto> telefonosList;

    private String creado;

    private String modificado;

    private String ultimoLogin;

    private String token;

    private boolean activo;
}
