package com.example.ejercicio.controller.mapper;

import com.example.ejercicio.dto.*;
import com.example.ejercicio.service.dto.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    public CrearUsuarioResponseDto toCrearUsuarioResponseDto(CrearUsuarioResponse crearUsuario) {
        CrearUsuarioResponseDto dto = new CrearUsuarioResponseDto();
        dto.setCreado(crearUsuario.getCreado());
        dto.setModificado(crearUsuario.getModificado());
        dto.setUltimoLogin(crearUsuario.getUltimoLogin());
        dto.setToken(crearUsuario.getToken());
        dto.setActivo(crearUsuario.isActivo());
        return dto;
    }

    public static Telefono toTelefono(TelefonoDto dto) {
        Telefono tel = new Telefono();
        tel.setNumero(dto.getNumero());
        tel.setCodigoCiudad(dto.getCodigoCiudad());
        tel.setCodigoPais(dto.getCodigoPais());
        return tel;
    }

    public static List<Telefono> toTelefonos(List<TelefonoDto> telefonosDto) {
        List<Telefono> telefonos = new ArrayList<>();
        for (TelefonoDto dto : telefonosDto) {
            Telefono tel = new Telefono();
            tel.setNumero(dto.getNumero());
            tel.setCodigoCiudad(dto.getCodigoCiudad());
            tel.setCodigoPais(dto.getCodigoPais());
            telefonos.add(tel);
        }
        return telefonos;
    }

    public static TelefonoDto toTelefonoDto(Telefono dto) {
        TelefonoDto tel = new TelefonoDto();
        tel.setNumero(dto.getNumero());
        tel.setCodigoCiudad(dto.getCodigoCiudad());
        tel.setCodigoPais(dto.getCodigoPais());
        return tel;
    }

    public static UsuarioDto toUsuarioDto(Usuario dto) {
        UsuarioDto res = new UsuarioDto();
        res.setId(dto.getId());
        res.setNombre(dto.getNombre());
        res.setCorreo(dto.getCorreo());
        res.setContrasenia(dto.getContrasenia());

        List<TelefonoDto> telefonos = dto.getTelefonosList()
                .stream()
                .map(UsuarioMapper::toTelefonoDto)
                .collect(Collectors.toList());
        res.setTelefonosList(telefonos);

        res.setCreado(dto.getCreado());
        res.setModificado(dto.getModificado());
        res.setUltimoLogin(dto.getUltimoLogin());
        res.setToken(dto.getToken());
        res.setActivo(dto.isActivo());
        return res;
    }

    public static ObtenerUsuariosResponseDto toObtenerUsuariosResponseDto(List<UsuarioDto> obtenerTodosLosUsuarios) {
        ObtenerUsuariosResponseDto response = new ObtenerUsuariosResponseDto();
        response.setUsuarios(obtenerTodosLosUsuarios);
        return response;
    }

    public ModificarUsuarioResponseDto toModificarUsuarioResponseDto(ModificarUsuarioResponse modificarUsuario) {
        ModificarUsuarioResponseDto dto = new ModificarUsuarioResponseDto();
        dto.setId(modificarUsuario.getId());
        dto.setNombre(modificarUsuario.getNombre());
        dto.setCorreo(modificarUsuario.getCorreo());
        dto.setContrasenia(modificarUsuario.getContrasenia());

        List<TelefonoDto> telefonosDto = modificarUsuario.getTelefonosList()
                .stream()
                .map(UsuarioMapper::toTelefonoDto)
                .collect(Collectors.toList());
        dto.setTelefonosList(telefonosDto);

        dto.setCreado(modificarUsuario.getCreado());
        dto.setModificado(modificarUsuario.getModificado());
        dto.setUltimoLogin(modificarUsuario.getUltimoLogin());
        dto.setToken(modificarUsuario.getToken());
        dto.setActivo(modificarUsuario.isActivo());
        return dto;
    }

    public EliminarUsuarioResponseDto toEliminarUsuarioResponseDto(EliminarUsuarioResponse eliminarUsuario) {
        EliminarUsuarioResponseDto response = new EliminarUsuarioResponseDto();
        response.setId(eliminarUsuario.getId());
        return response;
    }

    public ActualizarContrasenaResponseDto toActualizarContrasenaResponseDto(ActualizarContrasenaResponse actualizarContrasena) {
        ActualizarContrasenaResponseDto response = new ActualizarContrasenaResponseDto();
        response.setResultado(actualizarContrasena.getResultado());
        return response;
    }
}
