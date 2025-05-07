package com.example.ejercicio.controller;

import com.example.ejercicio.controller.mapper.UsuarioMapper;
import com.example.ejercicio.dto.*;
import com.example.ejercicio.service.UsuarioServiceImpl;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Clase Controller para Usuarios.
 */
@RestController
@Validated
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;

    private final UsuarioMapper usuarioMapper;

    /**
     * Constructor.
     *
     * @param usuarioService clase de usuarioService.
     * @param usuarioMapper clase de mapper para el controlador.
     */
    public UsuarioController(UsuarioServiceImpl usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    /**
     * POST: Endpoint para crear usuario '/usuario'.
     * @param requestDto de tipo CrearUsuarioRequestDto con los datos para crear un nuevo Usuario.
     * @return ResponseEntity OK 200.
     */
    @PostMapping
    public ResponseEntity<CrearUsuarioResponseDto> crearUsuario (@RequestBody @Valid CrearUsuarioRequestDto requestDto){
        return ResponseEntity.ok(usuarioMapper.toCrearUsuarioResponseDto(
                usuarioService.crearUsuario(
                        requestDto)));
    }

    /**
     * GET: Endpoint para obtener todos los usuarios '/usuario'.
     * @return ResponseEntity OK 200.
     */
    @GetMapping
    public ResponseEntity<ObtenerUsuariosResponseDto> obtenerUsuarios(){
        return ResponseEntity.ok(UsuarioMapper.toObtenerUsuariosResponseDto(
                usuarioService.obtenerTodosLosUsuarios()));
    }

    /**
     * PUT: Endpoint para editar usuario '/usuario/{id}'.
     * @param requestDto de tipo ModificarUsuarioRequestDto con los datos para editar un Usuario.
     * @return ResponseEntity OK 200.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModificarUsuarioResponseDto> modificarUsuario(@RequestBody @Valid ModificarUsuarioRequestDto requestDto, @PathVariable String id) {
        return ResponseEntity.ok(
                usuarioMapper.toModificarUsuarioResponseDto(
                        usuarioService.modificarUsuario(
                                id, requestDto)));
    }

    /**
     * DELETE: Endpoint para eliminar usuario '/usuario/{id}'.
     * @param id del usuario a eliminar.
     * @return ResponseEntity OK 200.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<EliminarUsuarioResponseDto> eliminarUsuarios(@PathVariable String id) {
        return ResponseEntity.ok(
                usuarioMapper.toEliminarUsuarioResponseDto(
                        usuarioService.eliminarUsuario(id)));
    }

    /**
     * DELETE: Endpoint para cambiar la contraseña a un usuario '/usuario/actualizarContrasena/{id}'.
     * @param id del usuario a actualizar la constraseña.
     * @return ResponseEntity OK 200.
     */
    @PatchMapping("/actualizarContrasena/{id}")
    public ResponseEntity<ActualizarContrasenaResponseDto> actualizarContrasenia(@PathVariable String id, @RequestBody ActualizarContrasenaRequestDto requestDto) {
        return ResponseEntity.ok(
                usuarioMapper.toActualizarContrasenaResponseDto(
                        usuarioService.actualizarContrasena(id, requestDto)));
    }
}
