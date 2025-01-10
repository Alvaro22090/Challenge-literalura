package com.aluracursos.Challenge_literalura.model;

public enum Idioma {
    ESPAÑOL("es", "Español"),
    INGLES("en", "Inglés"),
    FRANCES("fr", "Francés"),
    PORTUGUES("pt", "Portugués");
    private String idiomaAPI;
    private String idiomaEspañol;

    Idioma (String idiomaAPI, String idiomaEspañol){
        this.idiomaAPI = idiomaAPI;
        this.idiomaEspañol = idiomaEspañol;
    }

    public static Idioma fromString(String text){
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaAPI.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun libro de ese idioma encontrado: " + text);
    }
    public static Idioma fromEspañol(String text){
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaEspañol.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Ningun libro de ese idioma encontrado: " + text);
    }

}
