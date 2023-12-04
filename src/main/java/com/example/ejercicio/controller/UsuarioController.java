package com.example.ejercicio.controller;

import com.example.ejercicio.dto.CrearUsuarioResponseDto;
import com.example.ejercicio.dto.UsuarioDto;
import com.example.ejercicio.dto.TokenDto;
import com.example.ejercicio.service.TokenServiceImpl;
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

    private final TokenServiceImpl tokenService;

    private final UsuarioServiceImpl usuarioService;

    public UsuarioController(TokenServiceImpl tokenService, UsuarioServiceImpl usuarioService) {
        this.tokenService = tokenService;
        this.usuarioService = usuarioService;
    }

    @PostMapping(value = "/crear")
    public ResponseEntity<CrearUsuarioResponseDto> crearUsuario (@RequestBody @Valid UsuarioDto requestDto){
        CrearUsuarioResponseDto response = usuarioService.crearUsuario(requestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/token")
    public ResponseEntity<TokenDto> login(@RequestParam("user") String username) {
        String token = tokenService.getJWTToken(username);
        TokenDto jwt = new TokenDto();
        jwt.setJwt(token);
        return ResponseEntity.ok(jwt);
    }

    @RequestMapping(value = "/obtenerTodos", method = RequestMethod.GET)
    public List<UsuarioDto> getAllUsers(){
        List<UsuarioDto> usuarios = usuarioService.obtenerTodosLosUsuarios();
        return usuarios;
    }
}
