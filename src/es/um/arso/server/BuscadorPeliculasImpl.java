package es.um.arso.server;

import java.util.List;

import javax.jws.WebService;

import es.um.arso.buscador.controlador.ControladorPeliculas;

import es.um.arso.pelicula.Pelicula;

@WebService(endpointInterface = "es.um.arso.server.BuscadorPeliculas", targetNamespace="http://server.arso.um.es/")
public class BuscadorPeliculasImpl implements BuscadorPeliculas {

	private ControladorPeliculas controladorPeliculas = new ControladorPeliculas();

	public List<Pelicula> buscarPorTitulo(String titulo)
			throws PeliculaException {
		List<Pelicula> peliculas = controladorPeliculas.buscar(titulo,
				ControladorPeliculas.TIPO_TITULO);
		return peliculas;
	}

	public Pelicula obtenerInformacion(long identificador)
			throws PeliculaException {
		Pelicula pelicula = controladorPeliculas.obtenerPelicula(identificador);
		return pelicula;
	}
}