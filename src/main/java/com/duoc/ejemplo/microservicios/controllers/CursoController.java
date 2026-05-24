package com.duoc.ejemplo.microservicios.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.ejemplo.microservicios.models.Curso;
import com.duoc.ejemplo.microservicios.services.CursoService;

@RestController
@RequestMapping("/api")
public class CursoController {
    
    @Autowired
    private CursoService cursoService;
    
    // GET /api/cursos - Listar todos los cursos
    @GetMapping("/cursos")
    public ResponseEntity<List<Curso>> listarCursos() {
        return ResponseEntity.ok(cursoService.listarCursos());
    }
    
    // POST /api/cursos - Agregar nuevo curso
    @PostMapping("/cursos")
    public ResponseEntity<Map<String, Object>> agregarCurso(@RequestBody Curso curso) {
        Curso nuevoCurso = cursoService.agregarCurso(curso);
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Curso agregado exitosamente");
        response.put("curso", nuevoCurso);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}