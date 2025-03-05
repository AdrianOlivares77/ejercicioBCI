package com.example.ejercicio.controller;

import com.example.ejercicio.dto.TokenDto;
import com.example.ejercicio.service.TokenServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Clase Controller para los endpoints de token.
 */
@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenServiceImpl tokenService;

    /**
     * Constructor de la clase.
     * @param tokenService clase token.
     */
    public TokenController(TokenServiceImpl tokenService) {
        this.tokenService = tokenService;
    }

    /**
     * Endpoint /token/login que response con un token de autorización para un usuario específico.
     * @param username usuario a hacer login.
     * @return ResponseEntity 200.
     */
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestParam("user") String username) {
        String token = tokenService.getJWTToken(username);
        TokenDto jwt = new TokenDto();
        jwt.setJwt(token);
        return ResponseEntity.ok(jwt);
    }
}
