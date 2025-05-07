package com.example.ejercicio.service;

import com.example.ejercicio.dto.ActualizarContrasenaRequestDto;
import com.example.ejercicio.dto.CrearUsuarioRequestDto;
import com.example.ejercicio.dto.ModificarUsuarioRequestDto;
import com.example.ejercicio.dto.UsuarioDto;
import com.example.ejercicio.service.dto.ActualizarContrasenaResponse;
import com.example.ejercicio.service.dto.CrearUsuarioResponse;
import com.example.ejercicio.service.dto.EliminarUsuarioResponse;
import com.example.ejercicio.service.dto.ModificarUsuarioResponse;
import java.util.List;

/**
 * Interface para el servicio de Usuario.
 */
public interface UsuarioService {
    CrearUsuarioResponse crearUsuario (CrearUsuarioRequestDto requestDto);

    List<UsuarioDto> obtenerTodosLosUsuarios();

    EliminarUsuarioResponse eliminarUsuario(String id);

    ModificarUsuarioResponse modificarUsuario(String id, ModificarUsuarioRequestDto usuario);

    ActualizarContrasenaResponse actualizarContrasena(String id, ActualizarContrasenaRequestDto requestDto);
}
