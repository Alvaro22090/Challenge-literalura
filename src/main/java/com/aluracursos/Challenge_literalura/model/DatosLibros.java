package com.aluracursos.Challenge_literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autor,
        @JsonAlias("languages") ArrayList<String> idiomas,
        @JsonAlias("download_count") Double descargas
) {
    @Override
    public String toString() {
        return autor.toString();
    }
}
