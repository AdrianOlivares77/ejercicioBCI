package com.example.ejercicio.controller;

import com.example.ejercicio.dto.*;
import com.example.ejercicio.service.UsuarioServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "")
    public ResponseEntity<CrearUsuarioResponseDto> crearUsuario (@RequestBody @Valid CrearUsuarioRequestDto requestDto){
        CrearUsuarioResponseDto response = usuarioService.crearUsuario(requestDto);
        return ResponseEntity.ok(response);
    }


    @GetMapping(value = "")
    public ResponseEntity<List<UsuarioDto>> obtenerUsuarios(){
        List<UsuarioDto> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModificarUsuarioResponseDto> modificarUsuario(@RequestBody @Valid ModificarUsuarioRequestDto requestDto, @PathVariable String id) {
        ModificarUsuarioResponseDto response = usuarioService.modificarUsuario(id, requestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EliminarUsuarioResponseDto> eliminarUsuarios(@PathVariable String id) {
        EliminarUsuarioResponseDto response = usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/actualizarContrasena/{id}")
    public ResponseEntity<ActualizarContrasenaResponseDto> actualizarContrasenia(@PathVariable String id, @RequestBody ActualizarContrasenaRequestDto requestDto) {
        ActualizarContrasenaResponseDto response = usuarioService.actualizarContrasena(id, requestDto);
        return ResponseEntity.ok(response);
    }
}
