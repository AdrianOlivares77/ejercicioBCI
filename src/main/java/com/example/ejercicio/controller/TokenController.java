package com.example.ejercicio.controller;

import com.example.ejercicio.dto.TokenDto;
import com.example.ejercicio.service.TokenServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {

    private final TokenServiceImpl tokenService;

    public TokenController(TokenServiceImpl tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestParam("user") String username) {
        String token = tokenService.getJWTToken(username);
        TokenDto jwt = new TokenDto();
        jwt.setJwt(token);
        return ResponseEntity.ok(jwt);
    }
}
