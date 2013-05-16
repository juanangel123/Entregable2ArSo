package es.um.arso.server;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import es.um.arso.pelicula.Pelicula;

@WebService
public interface BuscadorPeliculas {
	@WebMethod
	@WebResult (name="pelicula", targetNamespace="http://server.arso.um.es/")
	public List<Pelicula> buscarPorTitulo(@WebParam (name="titulo") String titulo) throws PeliculaException;
	
	@WebMethod
	@WebResult (name="pelicula", targetNamespace="http://server.arso.um.es/")
	public Pelicula obtenerInformacion(@WebParam (name="identificador") long identificador) throws PeliculaException;
}
