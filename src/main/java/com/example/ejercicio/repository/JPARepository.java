package com.example.ejercicio.repository;

import com.example.ejercicio.dto.UsuarioDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JPARepository extends JpaRepository<UsuarioDto,String> {
}
