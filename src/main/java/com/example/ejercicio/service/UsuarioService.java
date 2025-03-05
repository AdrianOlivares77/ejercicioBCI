package com.example.ejercicio.service;

import com.example.ejercicio.dto.*;

import java.util.List;

/**
 * Interface para el servicio de Usuario.
 */
public interface UsuarioService {
    CrearUsuarioResponseDto crearUsuario (CrearUsuarioRequestDto requestDto);

    List<UsuarioDto> obtenerTodosLosUsuarios();

    EliminarUsuarioResponseDto eliminarUsuario(String id);

    ModificarUsuarioResponseDto modificarUsuario (String id, ModificarUsuarioRequestDto usuario);

    ActualizarContrasenaResponseDto actualizarContrasena(String id, ActualizarContrasenaRequestDto requestDto);
}
