package com.example.ejercicio.service;

import com.example.ejercicio.dto.CrearUsuarioResponseDto;
import com.example.ejercicio.dto.UsuarioDto;

public interface UsuarioService {
    CrearUsuarioResponseDto crearUsuario (UsuarioDto requestDto);
}
