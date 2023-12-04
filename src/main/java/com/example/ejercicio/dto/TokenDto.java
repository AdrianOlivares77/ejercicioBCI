package com.example.ejercicio.dto;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class TokenDto {
    private String jwt;
}
