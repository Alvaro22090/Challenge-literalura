package com.aluracursos.Challenge_literalura.repositorio;

import com.aluracursos.Challenge_literalura.model.Autor;
import com.aluracursos.Challenge_literalura.model.Idioma;
import com.aluracursos.Challenge_literalura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SerieRepository extends JpaRepository<Libros, Long> {
    List<Libros> findByIdiomas(Idioma idioma);
    @NativeQuery("SELECT * FROM autores")
    List<Autor> autoresRegistrados();
    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :fechaInicial AND a.fechaDeMuerte >= :fechaFinal ")
    List<Autor> autoresPorFecha(Integer fechaInicial, Integer fechaFinal);
}
