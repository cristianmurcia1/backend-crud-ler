package com.example.backend_crud.persona;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática de los valores
    private Long id;
    private String nombre;
    @Column(unique = true) // Identificador único
    private Long cedula;
    private String genero;
    private LocalDate fechaNacimiento;

    public Persona() {
    }

    public Persona(Long id, String nombre, Long cedula, String genero, LocalDate fechaNacimiento) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Persona(String nombre, Long cedula, String genero, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
