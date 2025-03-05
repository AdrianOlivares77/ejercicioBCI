package com.example.ejercicio.dto;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import lombok.Data;

/**
 * Clase de request para el endpoint de modificación de usuarios.
 */
@Data
public class ModificarUsuarioRequestDto {

    @NotEmpty(message = "Campo nombre no puede estar vacio")
    private String nombre;

    @NotEmpty(message = "Campo correo no puede estar vacio")
    @Email
    @Column(unique = true)
    private String correo;

    @NotEmpty(message = "Campo contraseña no puede estar vacio")
    private String contrasenia;

    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "id"))
    private List<TelefonoDto> telefonosList;

    private boolean activo;
}
