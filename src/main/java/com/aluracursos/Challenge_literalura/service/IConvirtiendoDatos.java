package com.aluracursos.Challenge_literalura.service;

public interface IConvirtiendoDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
