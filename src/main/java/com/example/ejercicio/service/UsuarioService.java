package com.example.ejercicio.service;

import com.example.ejercicio.dto.CrearUsuarioResponseDto;
import com.example.ejercicio.dto.CrearUsuarioRequestDto;

public interface UsuarioService {
    CrearUsuarioResponseDto crearUsuario (CrearUsuarioRequestDto requestDto);
}
