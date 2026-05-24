package com.duoc.ejemplo.microservicios.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import main.java.com.duoc.ejemplo.microservicios.models.InscripcionRequest;

@Entity
@Table(name = "cursos")
public class Curso {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @Column(nullable = false, length = 100)
    private String instructor;
    
    @Column(nullable = false)
    private Integer duracion;
    
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal costo;
    
    public Curso() {}
    
    public Curso(String nombre, String instructor, Integer duracion, BigDecimal costo) {
        this.nombre = nombre;
        this.instructor = instructor;
        this.duracion = duracion;
        this.costo = costo;
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    
    public Integer getDuracion() { return duracion; }
    public void setDuracion(Integer duracion) { this.duracion = duracion; }
    
    public BigDecimal getCosto() { return costo; }
    public void setCosto(BigDecimal costo) { this.costo = costo; }

    // POST /api/inscripciones - Inscribir estudiante a cursos
    @PostMapping("/inscripciones")
    public ResponseEntity<Map<String, Object>> inscribirCursos(@RequestBody InscripcionRequest request) {
    try {
        List<Curso> cursosSeleccionados = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;
        
        for (Long id : request.getCursosIds()) {
            Curso curso = cursoService.obtenerCursoPorId(id);
            cursosSeleccionados.add(curso);
            total = total.add(curso.getCosto());
        }
        
        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Inscripción exitosa");
        response.put("estudiante", request.getEmail());
        response.put("cursos", cursosSeleccionados);
        response.put("totalPagar", total);
        response.put("fecha", LocalDateTime.now().toString());
        
        return ResponseEntity.ok(response);
    } catch (RuntimeException e) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
}