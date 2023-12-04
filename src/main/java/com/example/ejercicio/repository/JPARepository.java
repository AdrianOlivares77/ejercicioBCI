package com.example.ejercicio.repository;

import com.example.ejercicio.dto.UsuarioDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JPARepository extends JpaRepository<UsuarioDto,String> {
    public Optional<UsuarioDto> findById(String id);
    public UsuarioDto findUsuarioById(String id);
}
