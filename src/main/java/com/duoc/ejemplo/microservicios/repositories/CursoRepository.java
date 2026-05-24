package com.duoc.ejemplo.microservicios.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.ejemplo.microservicios.models.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}