package com.example.ejercicio.service;

import com.example.ejercicio.configuration.PatternProperties;
import com.example.ejercicio.dto.ActualizarContrasenaRequestDto;
import com.example.ejercicio.dto.ActualizarContrasenaResponseDto;
import com.example.ejercicio.dto.CrearUsuarioResponseDto;
import com.example.ejercicio.dto.EliminarUsuarioResponseDto;
import com.example.ejercicio.dto.UsuarioDto;
import com.example.ejercicio.dto.CrearUsuarioRequestDto;
import com.example.ejercicio.dto.ModificarUsuarioRequestDto;
import com.example.ejercicio.dto.ModificarUsuarioResponseDto;
import com.example.ejercicio.exception.ContrasenaInvalidaException;
import com.example.ejercicio.exception.UsuarioNoExisteException;
import com.example.ejercicio.repository.JPARepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 * Servicio para las métodos referentes a usuarios.
 */
@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final JPARepository usuariosRepository;
    private final TokenServiceImpl tokenService;
    private final PatternProperties patternProperties;

    /**
     * Constructor.
     * @param usuariosRepository clase repository de usuarios.
     * @param tokenService clase service de token.
     * @param patternProperties clase config para obtener el pattern de contraseña.
     */
    public UsuarioServiceImpl(JPARepository usuariosRepository,
                              TokenServiceImpl tokenService,
                              PatternProperties patternProperties) {
        this.usuariosRepository = usuariosRepository;
        this.tokenService = tokenService;
        this.patternProperties = patternProperties;
    }

    /**
     * Función que crea un nuevo usuario.
     * @param requestDto de tipo CrearUsuarioRequestDto con los datos para crear un nuevo Usuario.
     * @return CrearUsuarioResponseDto con la data del nuevo usuario creado.
     */
    public CrearUsuarioResponseDto crearUsuario (CrearUsuarioRequestDto requestDto){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = LocalDateTime.now().format(formatter);
            validarContrasenaPattern(requestDto.getContrasenia());
            UsuarioDto nuevoUsuario = new UsuarioDto();
            nuevoUsuario.setNombre(requestDto.getNombre());
            nuevoUsuario.setCorreo(requestDto.getCorreo());
            nuevoUsuario.setContrasenia(requestDto.getContrasenia());
            nuevoUsuario.setTelefonosList(requestDto.getTelefonosList());
            nuevoUsuario.setCreado((formattedDateTime));
            nuevoUsuario.setModificado(formattedDateTime);
            nuevoUsuario.setUltimoLogin(formattedDateTime);
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

        } catch (DataAccessException e){
            log.debug(String.valueOf(e.getCause()));
            throw e;
        }
    }

    /**
     * Función que valida la contraseña.
     * @param contrasena contraseña a validar
     */
    private void validarContrasenaPattern(String contrasena) {
        String pattern = patternProperties.getPasswordUsuarioPattern().getPattern();
        if(!contrasena.matches(pattern)){
            throw new ContrasenaInvalidaException("La contraseña no cumple con el pattern establecido");
        }
    }

    /**
     * Función que obtiene todos los usuarios.
     * @return List<UsuarioDto> lista de usuarios.
     */
    public List<UsuarioDto> obtenerTodosLosUsuarios() {
        return usuariosRepository.findAll();
    }

    /**
     * Función que elimina un usuario.
     * @param id del usuario que se desea eliminar.
     * @return EliminarUsuarioResponseDto con un mensaje de éxito.
     */
    public EliminarUsuarioResponseDto eliminarUsuario(String id) {
        Optional<UsuarioDto> usuario;
        EliminarUsuarioResponseDto usuarioEliminado;
        try {
            usuario = usuariosRepository.findById(id);
            if (usuario.isPresent()) {
                usuariosRepository.deleteById(id);
                usuarioEliminado = new EliminarUsuarioResponseDto();
                usuarioEliminado.setId(usuario.get().getId());
            } else {
                throw new UsuarioNoExisteException("Usuario a eliminar no existe");
            }
        } catch (DataAccessException e) {
            log.debug(String.valueOf(e.getCause()));
            throw e;
        }
        return usuarioEliminado;
    }

    /**
     * Función que modifica un usuario.
     * @param id del usuario a modificar.
     * @param usuario objeto con los valores a modificar del usuario.
     * @return ModificarUsuarioResponseDto con los datos modificados del nuevo Usuario.
     */
    public ModificarUsuarioResponseDto modificarUsuario (String id, ModificarUsuarioRequestDto usuario) {

        validarContrasenaPattern(usuario.getContrasenia());
        UsuarioDto usuarioAModificar;
        ModificarUsuarioResponseDto usuarioResponse;

        usuarioAModificar = usuariosRepository.findUsuarioById(id);

        if (usuarioAModificar != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = LocalDateTime.now().format(formatter);
            usuarioAModificar.setNombre(usuario.getNombre());
            usuarioAModificar.setActivo(usuario.isActivo());
            usuarioAModificar.setCorreo(usuario.getCorreo());
            usuarioAModificar.setContrasenia(usuario.getContrasenia());
            usuarioAModificar.setTelefonosList(usuario.getTelefonosList());
            usuarioAModificar.setModificado(formattedDateTime);
            usuarioAModificar.setCreado(usuarioAModificar.getCreado());
            usuarioAModificar.setToken(usuarioAModificar.getToken());
            usuarioAModificar.setUltimoLogin(usuarioAModificar.getUltimoLogin());

            usuariosRepository.save(usuarioAModificar);

            usuarioResponse = new ModificarUsuarioResponseDto();
            usuarioResponse.setId(id);
            usuarioResponse.setNombre(usuarioAModificar.getNombre());
            usuarioResponse.setActivo(usuarioAModificar.isActivo());
            usuarioResponse.setCorreo(usuarioAModificar.getCorreo());
            usuarioResponse.setContrasenia(usuarioAModificar.getContrasenia());
            usuarioResponse.setTelefonosList(usuarioAModificar.getTelefonosList());
            usuarioResponse.setModificado(formattedDateTime);
            usuarioResponse.setCreado(usuarioAModificar.getCreado());
            usuarioResponse.setToken(usuarioAModificar.getToken());
            usuarioResponse.setUltimoLogin(usuarioAModificar.getUltimoLogin());
        } else {
            throw new UsuarioNoExisteException("Usuario con esa id no existe");
        }

        return usuarioResponse;
    }

    /**
     * Función para actualizar la contraseña de un Usuario específico.
     * @param id dle usuario a modificar la contraseña.
     * @param requestDto objeto con la nueva constraseña
     * @return ActualizarContrasenaResponseDto con mensaje de éxito.
     */
    public ActualizarContrasenaResponseDto actualizarContrasena(String id, ActualizarContrasenaRequestDto requestDto) {

        validarContrasenaPattern(requestDto.getContrasenia());
        UsuarioDto usuarioACambiarContrasena;

        usuarioACambiarContrasena = usuariosRepository.findUsuarioById(id);
        if (usuarioACambiarContrasena != null){
            usuarioACambiarContrasena.setContrasenia(requestDto.getContrasenia());
            usuariosRepository.save(usuarioACambiarContrasena);
        } else {
            throw new UsuarioNoExisteException("usuario con esa id no existe");
        }
        ActualizarContrasenaResponseDto response = new ActualizarContrasenaResponseDto();
        response.setResultado("Contraseña actualizada correctamente");
        return response;
    }
}
