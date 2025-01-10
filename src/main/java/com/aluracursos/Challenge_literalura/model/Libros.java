package com.aluracursos.Challenge_literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libros {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;;
    @Enumerated(EnumType.STRING)
    private Idioma idiomas;
    private Double descargas;
    @OneToMany(mappedBy = "libros", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor;

    public Libros (){}

    public Libros(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idiomas = Idioma.fromString(datosLibros.idiomas().get(0).trim());
        this.descargas = datosLibros.descargas();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        this.autor = autor;
    }

    public Idioma getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idioma idiomas) {
        this.idiomas = idiomas;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    @Override
    public String toString() {
        return "----- LIBRO -----" +
                "\nTitulo: " + titulo +
                "\nAutor: " + autor +
                "\nIdioma: " + idiomas +
                "\nDescargas: " + descargas +
                "\n-----------------";
    }
}
