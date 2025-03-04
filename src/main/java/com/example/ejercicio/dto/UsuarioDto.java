package com.example.ejercicio.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class UsuarioDto implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private String nombre;

    @Email
    @Column(unique = true)
    private String correo;


    private String contrasenia;

    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "id"))
    private List<TelefonoDto> telefonosList;

    private String creado;

    private String modificado;

    private String ultimoLogin;

    private String token;

    private boolean activo;
}
