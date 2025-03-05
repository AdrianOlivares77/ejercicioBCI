package com.example.ejercicio.service;

import com.example.ejercicio.dto.TokenDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

/**
 * Servicio para las funciones referentes al token de autorización.
 */
@Service
public class TokenServiceImpl implements TokenService {

    /**
     * Clase para hacer login.
     * @param username usuario que se quiere obtener el token.
     * @return string con la forma "Bearer {token}"
     */
    public String login(String username) {
        String token = getJWTToken(username);
        TokenDto jwt = new TokenDto();
        jwt.setJwt(token);
        return jwt.getJwt();
    }

    /**
     * Clase para generar un token a partir de un username.
     * @param username usuario a generar el token.
     * @return string con la forma "Bearer {token}"
     */
    public String getJWTToken(String username) {
        String secretKey = "mySecretKey";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("JWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
