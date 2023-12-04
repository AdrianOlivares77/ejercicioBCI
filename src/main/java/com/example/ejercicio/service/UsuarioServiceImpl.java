package com.example.ejercicio.service;

import com.example.ejercicio.configuration.PatternProperties;
import com.example.ejercicio.dto.*;
import com.example.ejercicio.exception.ContraseñaInvalidaException;
import com.example.ejercicio.exception.UsuarioNoExisteException;
import com.example.ejercicio.repository.JPARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final JPARepository usuariosRepository;
    private final TokenServiceImpl tokenService;

    private final PatternProperties patternProperties;

    public UsuarioServiceImpl(JPARepository usuariosRepository, TokenServiceImpl tokenService, PatternProperties patternProperties) {
        this.usuariosRepository = usuariosRepository;
        this.tokenService = tokenService;
        this.patternProperties = patternProperties;
    }

    public CrearUsuarioResponseDto crearUsuario (CrearUsuarioRequestDto requestDto){
        try {
            validarContraseñaPattern(requestDto.getContraseña());
            UsuarioDto nuevoUsuario = new UsuarioDto();
            nuevoUsuario.setNombre(requestDto.getNombre());
            nuevoUsuario.setCorreo(requestDto.getCorreo());
            nuevoUsuario.setContraseña(requestDto.getContraseña());
            nuevoUsuario.setTelefonosList(requestDto.getTelefonosList());
            nuevoUsuario.setCreado(String.valueOf(new Date()));
            nuevoUsuario.setModificado(String.valueOf(new Date()));
            nuevoUsuario.setUltimoLogin(String.valueOf(new Date()));
            nuevoUsuario.setToken(tokenService.login(requestDto.getNombre()));
            nuevoUsuario.setActivo(true);

            usuariosRepository.save(nuevoUsuario);

            CrearUsuarioResponseDto response = new CrearUsuarioResponseDto();
            response.setCreado(nuevoUsuario.getCreado());
            response.setModificado(nuevoUsuario.getModificado());
            response.setUltimoLogin(nuevoUsuario.getUltimoLogin());
            response.setToken(nuevoUsuario.getToken());
            response.setActivo(nuevoUsuario.isActivo());
            response.setActivo(true);
            return response;

        } catch (Exception e){
            log.debug(String.valueOf(e.getCause()));
            throw e;
        }
    }

    private void validarContraseñaPattern(String contraseña) {
        String pattern = patternProperties.getPasswordUsuarioPattern().getPattern();
        if(!contraseña.matches(pattern)){
            throw new ContraseñaInvalidaException("contraseña no cumple con el pattern establecido");
        }
        return;
    }

    public List<UsuarioDto> obtenerTodosLosUsuarios() {
        return usuariosRepository.findAll();
    }

    public Optional<UsuarioDto> eliminarUsuario(String id) {
        Optional<UsuarioDto> usuario;
        try {
            usuario = usuariosRepository.findById(id);
            if (usuario.isPresent()) {
                usuariosRepository.deleteById(id);
            } else {
                throw new UsuarioNoExisteException("usuario a eliminar no existe");
            }
        } catch (DataAccessException e) {
            log.debug(String.valueOf(e.getCause()));
            throw e;
        }
        return usuario;
    }

    public UsuarioDto modificarUsuario (String id, ModificarUsuarioRequestDto usuario) {

        validarContraseñaPattern(usuario.getContraseña());
        UsuarioDto usuarioAModificar;

        try {
            usuarioAModificar = usuariosRepository.findUsuarioById(id);

            if (usuarioAModificar != null) {
                usuarioAModificar.setNombre(usuario.getNombre());
                usuarioAModificar.setActivo(usuario.isActivo());
                usuarioAModificar.setCorreo(usuario.getCorreo());
                usuarioAModificar.setContraseña(usuario.getContraseña());
                usuarioAModificar.setTelefonosList(usuario.getTelefonosList());
                usuarioAModificar.setModificado(String.valueOf(new Date()));
                usuarioAModificar.setCreado(usuarioAModificar.getCreado());
                usuarioAModificar.setToken(usuarioAModificar.getToken());
                usuarioAModificar.setUltimoLogin(usuarioAModificar.getUltimoLogin());
                usuariosRepository.save(usuarioAModificar);
            }
            else {
                throw new UsuarioNoExisteException("usuario con esa id no existe");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return usuarioAModificar;
    }

    public ActualizarContraseñaResponseDto actualizarContraseña (String id, ActualizarContraseñaRequestDto requestDto){

        validarContraseñaPattern(requestDto.getContraseña());
        UsuarioDto usuarioACambiarContraseña;

        try {
            usuarioACambiarContraseña = usuariosRepository.findUsuarioById(id);
            if (usuarioACambiarContraseña != null){
                usuarioACambiarContraseña.setContraseña(requestDto.getContraseña());
                usuariosRepository.save(usuarioACambiarContraseña);
            } else {
                throw new UsuarioNoExisteException("usuario con esa id no existe");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ActualizarContraseñaResponseDto response = new ActualizarContraseñaResponseDto();
        response.setResultado("Contraseña actualizada correctamente");
        return response;
    }
}
