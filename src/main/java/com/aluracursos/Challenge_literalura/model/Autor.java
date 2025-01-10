package com.aluracursos.Challenge_literalura.model;

import jakarta.persistence.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nombre;
    private Integer fechaDeNacimiento;
    private Integer fechaDeMuerte;
    @ManyToOne
    private Libros libros;

    public Libros getLibros() {
        return libros;
    }

    public void setLibros(Libros libros) {
        this.libros = libros;
    }

    public Autor(){}

    public Autor(DatosAutor datosAutor){
        this.nombre = datosAutor.nombre();;
        try {
            this.fechaDeNacimiento = datosAutor.fechaDeNacimiento();
        }catch (DateTimeException e){
            this.fechaDeNacimiento = null;
        }
        try {
            this.fechaDeMuerte = datosAutor.fechaDeMuerte();
        }catch (DateTimeException e){
            this.fechaDeMuerte = null;
        }
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

    public Integer getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(Integer fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public Integer getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(Integer fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    @Override
    public String toString() {
        return nombre.toString();
    }
}
