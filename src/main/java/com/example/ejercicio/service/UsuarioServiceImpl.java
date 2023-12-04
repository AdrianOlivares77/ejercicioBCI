package com.example.ejercicio.service;

import com.example.ejercicio.configuration.PatternProperties;
import com.example.ejercicio.dto.CrearUsuarioResponseDto;
import com.example.ejercicio.dto.UsuarioDto;
import com.example.ejercicio.exception.ContraseñaInvalidaException;
import com.example.ejercicio.repository.JPARepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    public CrearUsuarioResponseDto crearUsuario (UsuarioDto requestDto){
        try {
            if (validarContraseñaPattern(requestDto.getContraseña()))
            {
                CrearUsuarioResponseDto response = new CrearUsuarioResponseDto();
                response.setCreado(String.valueOf(new Date()));
                response.setModificado(String.valueOf(new Date()));
                response.setUltimoLogin(String.valueOf(new Date()));
                response.setToken(tokenService.login(requestDto.getNombre()));
                response.setActivo(true);
                usuariosRepository.save(requestDto);
                return response;
            } else {
                throw new ContraseñaInvalidaException("contraseña no cumple con el pattern establecido");
            }
        } catch (Exception e){
            log.debug(String.valueOf(e.getCause()));
            throw e;
        }
    }

    private boolean validarContraseñaPattern(String contraseña) {
        String pattern = patternProperties.getPasswordUsuarioPattern().getPattern();
        return contraseña.matches(pattern);
    }

    public List<UsuarioDto> obtenerTodosLosUsuarios() {
        return usuariosRepository.findAll();
    }
}
