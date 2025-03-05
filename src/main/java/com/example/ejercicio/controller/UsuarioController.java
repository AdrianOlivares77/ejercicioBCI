package com.example.ejercicio.controller;

import javax.validation.Valid;
import java.util.List;
import com.example.ejercicio.dto.*;
import com.example.ejercicio.service.UsuarioServiceImpl;
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

    /**
     * Constructor.
     * @param usuarioService clase de usuarioService.
     */
    public UsuarioController(UsuarioServiceImpl usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * POST: Endpoint para crear usuario '/usuario'.
     * @param requestDto de tipo CrearUsuarioRequestDto con los datos para crear un nuevo Usuario.
     * @return ResponseEntity OK 200.
     */
    @PostMapping
    public ResponseEntity<CrearUsuarioResponseDto> crearUsuario (@RequestBody @Valid CrearUsuarioRequestDto requestDto){
        CrearUsuarioResponseDto response = usuarioService.crearUsuario(requestDto);
        return ResponseEntity.ok(response);
    }

    /**
     * GET: Endpoint para obtener todos los usuarios '/usuario'.
     * @return ResponseEntity OK 200.
     */
    @GetMapping
    public ResponseEntity<List<UsuarioDto>> obtenerUsuarios(){
        List<UsuarioDto> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return ResponseEntity.ok(usuarios);
    }

    /**
     * PUT: Endpoint para editar usuario '/usuario/{id}'.
     * @param requestDto de tipo ModificarUsuarioRequestDto con los datos para editar un Usuario.
     * @return ResponseEntity OK 200.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ModificarUsuarioResponseDto> modificarUsuario(@RequestBody @Valid ModificarUsuarioRequestDto requestDto, @PathVariable String id) {
        ModificarUsuarioResponseDto response = usuarioService.modificarUsuario(id, requestDto);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE: Endpoint para eliminar usuario '/usuario/{id}'.
     * @param id del usuario a eliminar.
     * @return ResponseEntity OK 200.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<EliminarUsuarioResponseDto> eliminarUsuarios(@PathVariable String id) {
        EliminarUsuarioResponseDto response = usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok(response);
    }

    /**
     * DELETE: Endpoint para cambiar la contraseña a un usuario '/usuario/actualizarContrasena/{id}'.
     * @param id del usuario a actualizar la constraseña.
     * @return ResponseEntity OK 200.
     */
    @PatchMapping("/actualizarContrasena/{id}")
    public ResponseEntity<ActualizarContrasenaResponseDto> actualizarContrasenia(@PathVariable String id, @RequestBody ActualizarContrasenaRequestDto requestDto) {
        ActualizarContrasenaResponseDto response = usuarioService.actualizarContrasena(id, requestDto);
        return ResponseEntity.ok(response);
    }
}
