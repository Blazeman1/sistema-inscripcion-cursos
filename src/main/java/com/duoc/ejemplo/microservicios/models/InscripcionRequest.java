package com.duoc.ejemplo.microservicios.models;

import java.util.List;

public class InscripcionRequest {
    private String email;
    private List<Long> cursosIds;
    
    // Getters y Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public List<Long> getCursosIds() { return cursosIds; }
    public void setCursosIds(List<Long> cursosIds) { this.cursosIds = cursosIds; }
}