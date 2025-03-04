package com.example.ejercicio

import com.example.ejercicio.configuration.PatternProperties
import com.example.ejercicio.dto.ActualizarContrasenaRequestDto
import com.example.ejercicio.dto.CrearUsuarioRequestDto
import com.example.ejercicio.dto.PatternMensajeDto
import com.example.ejercicio.dto.TelefonoDto
import com.example.ejercicio.dto.UsuarioDto
import com.example.ejercicio.exception.ContrasenaInvalidaException
import com.example.ejercicio.repository.JPARepository
import com.example.ejercicio.service.TokenServiceImpl
import com.example.ejercicio.service.UsuarioServiceImpl
import spock.lang.Specification
import spock.lang.Subject


class UsuarioServiceImplSpec extends Specification {

    JPARepository usuariosRepository = Mock(JPARepository)

    TokenServiceImpl tokenService = Mock(TokenServiceImpl)

    PatternProperties patternProperties = Mock(PatternProperties)

    @Subject
    UsuarioServiceImpl usuarioService = new UsuarioServiceImpl(usuariosRepository, tokenService, patternProperties)

    def "crearUsuario deberia crear un nuevo usuario exitosamente"() {
        given: "un request DTO con datos válidos"
        def telefono1 = new TelefonoDto(numero: "123456789", codigoCiudad: "011", codigoPais: "AR")
        def telefono2 = new TelefonoDto(numero: "987654321", codigoCiudad: "021", codigoPais: "US")
        def requestDto = new CrearUsuarioRequestDto(
                nombre: "Juan",
                correo: "juan@example.com",
                contrasenia: "Password123!",
                telefonosList: [telefono1, telefono2]
        )
        def token = "fakeToken"

        and: "los mocks devuelven los valores esperados"
        tokenService.login(requestDto.getNombre()) >> token
        usuariosRepository.save(_) >> { args -> args[0] }

        def patternMensajeDto = new PatternMensajeDto(pattern: ".*")
        patternProperties.getPasswordUsuarioPattern() >> patternMensajeDto

        when: "llamamos al método crearUsuario"
        def response = usuarioService.crearUsuario(requestDto)

        then: "el usuario es creado correctamente y se devuelve una respuesta"
        response != null
        response.token == token
        response.activo
        1 * usuariosRepository.save(_)
    }

    def "crearUsuario deberia lanzar una excepcion si la contrasenia no cumple con el patron"() {
        given: "un request DTO con una contraseña inválida"
        def telefono1 = new TelefonoDto(numero: "123456789", codigoCiudad: "011", codigoPais: "AR")
        def telefono2 = new TelefonoDto(numero: "987654321", codigoCiudad: "021", codigoPais: "US")
        def requestDto = new CrearUsuarioRequestDto(
                nombre: "Juan",
                correo: "juan@example.com",
                contrasenia: "123",
                telefonosList: [telefono1, telefono2]
        )

        and: "el patrón de contraseña esperado"
        def patternMensajeDto = new PatternMensajeDto(pattern: "^.{4,}\$") // Establecemos un patrón válido
        patternProperties.getPasswordUsuarioPattern() >> patternMensajeDto

        when: "llamamos al método crearUsuario"
        usuarioService.crearUsuario(requestDto)

        then: "debe lanzar una excepción de contraseña inválida"
        thrown(ContrasenaInvalidaException)
    }

    def "eliminarUsuario deberia eliminar un usuario exitosamente"() {
        given: "un usuario existente"
        def telefono1 = new TelefonoDto(numero: "123456789", codigoCiudad: "011", codigoPais: "AR")
        def usuarioExistente = new UsuarioDto(
                id: "1",
                nombre: "Juan",
                correo: "juan@example.com",
                contrasenia: "Password123!",
                telefonosList: [telefono1],
                creado: "2021-01-01",
                modificado: "2021-01-01",
                ultimoLogin: "2021-01-01",
                token: "fakeToken",
                activo: true
        )

        when: "mockear la respuesta de findById para devolver un usuario existente"
        usuariosRepository.findById("1") >> Optional.of(usuarioExistente)

        and: "llamamos al método eliminarUsuario"
        def response = usuarioService.eliminarUsuario("1")

        then: "el usuario es eliminado correctamente"
        response.id == "1"
        1 * usuariosRepository.deleteById("1") // Verificamos que deleteById haya sido llamado una vez
    }

    def "actualizarContrasena deberia actualizar la contrasena de un usuario"() {
        given: "un request DTO para actualizar la contraseña"
        def telefono1 = new TelefonoDto(numero: "123456789", codigoCiudad: "011", codigoPais: "AR")
        def requestDto = new ActualizarContrasenaRequestDto(contrasenia: "NewPassword123!")
        def usuarioExistente = new UsuarioDto(
                id: "1",
                nombre: "Juan",
                correo: "juan@example.com",
                contrasenia: "Password123!",
                telefonosList: [telefono1],
                creado: "2021-01-01",
                modificado: "2021-01-01",
                ultimoLogin: "2021-01-01",
                token: "fakeToken",
                activo: true
        )
        def patternMensajeDto = new PatternMensajeDto(pattern: ".*")
        patternProperties.getPasswordUsuarioPattern() >> patternMensajeDto

        when: "mockear la respuesta de findUsuarioById para devolver un usuario existente"
        usuariosRepository.findUsuarioById("1") >> usuarioExistente
        usuariosRepository.save(_) >> { }

        and: "llamamos al método actualizarContrasena"
        def response = usuarioService.actualizarContrasena("1", requestDto)

        then: "la contraseña es actualizada correctamente"
        response.resultado == "Contraseña actualizada correctamente"
        1 * usuariosRepository.save(_)
    }
}