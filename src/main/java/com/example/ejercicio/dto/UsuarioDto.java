package com.example.ejercicio.dto;

import com.example.ejercicio.configuration.PatternProperties;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class UsuarioDto implements Serializable {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @NotEmpty(message = "Campo nombre no puede estar vacio")
    private String nombre;

    @NotEmpty(message = "Campo correo no puede estar vacio")
    @Email
    @Column(unique = true)
    private String correo;

    @NotEmpty(message = "Campo contraseña no puede estar vacio")
    private String contraseña;

    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(joinColumns = @JoinColumn(name = "id"))
    private List<TelefonoDto> telefonosList;

}
