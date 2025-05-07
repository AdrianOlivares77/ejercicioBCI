package com.example.ejercicio.service.dto;

import java.util.List;
import lombok.Data;

@Data
public class ModificarUsuarioResponse {

    private String id;

    private String nombre;

    private String correo;

    private String contrasenia;

    private List<Telefono> telefonosList;

    private String creado;

    private String modificado;

    private String ultimoLogin;

    private String token;

    private boolean activo;

}
