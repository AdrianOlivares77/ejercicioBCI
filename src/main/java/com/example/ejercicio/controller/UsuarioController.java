package com.example.ejercicio.controller;

import com.example.ejercicio.dto.*;
import com.example.ejercicio.service.UsuarioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<CrearUsuarioResponseDto> crearUsuario (@RequestBody @Valid CrearUsuarioRequestDto requestDto){
        CrearUsuarioResponseDto response = usuarioService.crearUsuario(requestDto);
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "/obtenerTodos")
    public ResponseEntity<List<UsuarioDto>> getAllUsers(){
        List<UsuarioDto> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/modificar/{id}")
    public ResponseEntity<UsuarioDto> update(@RequestBody @Valid ModificarUsuarioRequestDto requestDto, @PathVariable String id) {
        UsuarioDto response = usuarioService.modificarUsuario(id, requestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Optional<UsuarioDto>> delete(@PathVariable String id) {
        Optional<UsuarioDto> response = usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/actualizarContrasena/{id}")
    public ResponseEntity<ActualizarContraseñaResponseDto> updatePassword(@PathVariable String id, @RequestBody ActualizarContraseñaRequestDto requestDto) {
        ActualizarContraseñaResponseDto response = usuarioService.actualizarContraseña(id, requestDto);
        return ResponseEntity.ok(response);
    }
}
