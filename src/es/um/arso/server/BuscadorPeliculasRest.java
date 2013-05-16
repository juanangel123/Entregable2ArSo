package es.um.arso.server;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.jboss.resteasy.plugins.providers.atom.Content;
import org.jboss.resteasy.plugins.providers.atom.Entry;
import org.jboss.resteasy.plugins.providers.atom.Feed;
import org.jboss.resteasy.plugins.providers.atom.Link;
import org.jboss.resteasy.plugins.providers.atom.Person;

import es.um.arso.buscador.controlador.ControladorPeliculas;

import es.um.arso.pelicula.Pelicula;

@Path("/")
public class BuscadorPeliculasRest {

	private ControladorPeliculas controladorPeliculas = ControladorPeliculas
			.getInstance();

	private final String BASE_URI = "http://localhost:8080/Entregable2";
	private final String BASE_PATH = "Entregable2";
	private final String SERVICE_URI = "/buscadorpeliculas";
	private final String METODO_BUSCAR = "/buscarportitulo";
	private final String METODO_INFORMACION = "/obtenerinformacion";

	private final String ATOM = "/atom";
	private final String JSON = "/json";
	private final String HTML = "/html";
	private final String XML = "/xml";

	@GET
	@Path(METODO_BUSCAR + ATOM)
	@Produces(MediaType.APPLICATION_ATOM_XML)
	// No se puede cambiar el charset, problema
	public Feed buscarPorTituloXML(@QueryParam("titulo") String titulo) {
		Feed feed = new Feed();

		// Buscamos la pelicula
		List<Pelicula> peliculas = new LinkedList<Pelicula>();
		try {
			peliculas = controladorPeliculas.buscar(titulo,
					ControladorPeliculas.TIPO_TITULO);
		} catch (PeliculaException e) {
			return feed;
		}

		try {
			feed.setId(new URI(BASE_URI + SERVICE_URI));
		} catch (URISyntaxException e1) {
			// No pasa nada, la URI está bien escrita
		}
		feed.setTitle("Feed de busqueda");
		feed.setUpdated(new Date());

		feed.getAuthors().add(new Person("Juan Angel Espigares Marin"));

		for (Pelicula pelicula : peliculas) {
			Entry entry = new Entry();
			entry.setTitle("Resultados busqueda");

			Content content = new Content();
			content.setJAXBObject(pelicula);
			// Añadimos el contenido
			entry.setContent(content);

			// Links
			Link link = new Link();
			try {
				link.setBase(new URI(BASE_URI));
				link.setHref(new URI(SERVICE_URI + METODO_BUSCAR + ATOM
						+ "/?id=" + pelicula.getId()));
			} catch (URISyntaxException e) {
			}
			// Añadimos el link a la entrada
			entry.getLinks().add(link);
			// Añadimos la entrada al feed
			feed.getEntries().add(entry);
		}
		return feed;
	}

	@GET
	@Path(METODO_BUSCAR + JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response buscarPorTituloJSON(@QueryParam("titulo") String titulo) {
		// Buscamos la pelicula
		List<Pelicula> peliculas = new LinkedList<Pelicula>();
		try {
			peliculas = controladorPeliculas.buscar(titulo,
					ControladorPeliculas.TIPO_TITULO);
		} catch (PeliculaException e) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		// Si no se devuelven resultados, se devuelve respuesta NOT FOUND
		if (peliculas.isEmpty())
			return Response.status(Status.NOT_FOUND).build();

		return Response.status(Status.OK).entity(peliculas).build();

	}

	@GET
	@Path(METODO_INFORMACION + XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response obtenerInformacionXML(@QueryParam("id") long identificador) {
		// Buscamos la pelicula
		Pelicula pelicula = null;
		try {
			pelicula = controladorPeliculas.obtenerPelicula(identificador);
		} catch (PeliculaException e) {
			// Puede ser not found o error interno, pero no se puede distinguir
			// en principio
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.status(Status.OK).entity(pelicula).build();
	}

	@GET
	@Path(METODO_INFORMACION + JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerInformacionJSON(@QueryParam("id") long identificador) {
		// Buscamos la pelicula
		Pelicula pelicula = null;
		try {
			pelicula = controladorPeliculas.obtenerPelicula(identificador);
		} catch (PeliculaException e) {
			// Puede ser not found o error interno, pero no se puede distinguir
			// en principio, ya que se encapsulan todas las excepciones en
			// PeliculaExcpetion
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.status(Status.OK).entity(pelicula).build();

	}

	@GET
	@Path(METODO_INFORMACION + HTML)
	@Produces(MediaType.TEXT_HTML)
	public Response obtenerInformacionHTML(@QueryParam("id") long identificador) {
		try {
			controladorPeliculas.transformar(identificador, getBasePath()
					+ "/pelicula.html", getBasePath() + "/xsl/pelicula.xsl");
		} catch (PeliculaException e) {
			// No se ha podido procesar la pelicula
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		// Problema: los CSS no se encuentran
		// Solucion: habia un conflicto con la uri del servlet
		return Response.status(Status.OK)
				.entity(new File(getBasePath() + "/pelicula.html")).build();
	}

	private String getBasePath() {
		String filePath = "";
		URL url = BuscadorPeliculasRest.class
				.getResource("BuscadorPeliculasRest.class");
		String className = url.getFile();
		filePath = className.substring(0, className.indexOf(BASE_PATH)
				+ BASE_PATH.length());

		return filePath;

	}
}
