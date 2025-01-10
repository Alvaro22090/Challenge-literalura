package com.aluracursos.Challenge_literalura.principal;

import com.aluracursos.Challenge_literalura.model.*;
import com.aluracursos.Challenge_literalura.repositorio.SerieRepository;
import com.aluracursos.Challenge_literalura.service.ConsumoAPI;
import com.aluracursos.Challenge_literalura.service.ConvirtiendoDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvirtiendoDatos conversor = new ConvirtiendoDatos();
    private SerieRepository repositorio;
    private List<Libros> libros;

    public Principal(SerieRepository repository) {
        this.repositorio = repository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ------------------------------------------------------
                    Elija la opción a través de su número:
                    1 - Buscar libro por título
                    2 - Buscar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                                  
                    0 - Salir
                    ------------------------------------------------------
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    buscarLibroRegistrado();
                    break;
                case 3:
                    buscarAutoresRegistrados();
                    break;
                case 4:
                    buscarAutoresPorFecha();
                    break;
                case 5:
                    bucarLibroPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void bucarLibroPorIdioma() {
        System.out.println("Escriba el genero/categoria de la serie que desea buscar:");
        var idioma = teclado.nextLine();
        var idiomas = Idioma.fromString(idioma);
        List<Libros> libroPorIdioma = repositorio.findByIdiomas(idiomas);
        System.out.println("Las series del idioma "+ idioma);
        libroPorIdioma.forEach(System.out::println);
    }

    private void buscarAutoresPorFecha() {
        System.out.println("Fecha inicial:  ");
        var fechaInicial = teclado.nextInt();
        teclado.nextLine();
        System.out.println("Fecha final: ");
        var fechaFinal = teclado.nextInt();
        teclado.nextLine();
        List<Autor> filtroAutor = repositorio.autoresPorFecha(fechaInicial, fechaFinal);
        filtroAutor.forEach(s ->
                System.out.println(s.getNombre()));
    }

    private void buscarAutoresRegistrados() {
        repositorio.autoresRegistrados();
        System.out.println(repositorio.autoresRegistrados());
    }

    private void buscarLibroRegistrado() {
        libros = repositorio.findAll();
        libros.stream()
                .sorted(Comparator.comparing(Libros::getTitulo))
                .forEach(System.out::println);
    }

    private DatosLibros getDatosLibros(){
        System.out.println("Escriba el nombre del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE+"?search="+nombreLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> librosBuscados = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();
        if(librosBuscados.isPresent()){
            return librosBuscados.get();
        } else {
            System.out.println("Libro no encontrado.");
            return null;
        }
    }

    private void buscarLibro() {
        DatosLibros datos = getDatosLibros();
        Libros libro = new Libros(datos);
        repositorio.save(libro);
        List<Autor> dato = datos.autor().stream()
                .map(e -> new Autor(e))
                .collect(Collectors.toList());
        libro.setAutor(dato);
        repositorio.save(libro);
        System.out.println(libro);
    }

}
