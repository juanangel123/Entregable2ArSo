package es.um.arso.buscador.controlador;

import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import es.um.arso.amazon.controlador.ControladorAmazon;
import es.um.arso.buscador.handler.BuscadorHandler;
import es.um.arso.buscador.handler.CriticasPrensaHandler;
import es.um.arso.buscador.handler.CriticasUsuarioHandler;
import es.um.arso.buscador.handler.DescripcionPeliculaHandler;
import es.um.arso.pelicula.CriticasPrensa;
import es.um.arso.pelicula.CriticasUsuario;
import es.um.arso.pelicula.DescripcionPelicula;
import es.um.arso.pelicula.LibroAmazon;
import es.um.arso.pelicula.ObjectFactory;
import es.um.arso.pelicula.Pelicula;
import es.um.arso.pelicula.PeliculaAmazon;
import es.um.arso.server.PeliculaException;

public class ControladorPeliculas {

	private final static ControladorPeliculas controladorPeliculasInstance = new ControladorPeliculas();

	// Otros controladores
	private ControladorAmazon controladorAmazon = new ControladorAmazon();

	// Factorias
	private final TransformerFactory tf = TransformerFactory.newInstance();
	private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	private ObjectFactory of = new ObjectFactory();
	private SAXParserFactory spf = SAXParserFactory.newInstance(
			"org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl", null);

	// Handlers
	private BuscadorHandler buscadorHandler;
	private DescripcionPeliculaHandler descripcionPeliculaHandler;
	private CriticasPrensaHandler criticasPrensaHandler;
	private CriticasUsuarioHandler criticasUsuarioHandler;

	// Describe los tres tipos disponbiles de busqueda
	public final static int TIPO_TITULO = 1;
	public final static int TIPO_REPARTO = 2;
	public final static int TIPO_DIRECTOR = 3;

	private final String URL_BUSQUEDA = "http://m.filmaffinity.com/es/search.php?stext=";
	private final String TIPO_BUSQUEDA = "&stype=";
	private final String URL_PELICULA = "http://m.filmaffinity.com/es/movie.php?id=";
	private final String URL_CRITICAS_PRENSA = "http://m.filmaffinity.com/es/moviereviews.php?id=";
	private final String URL_CRITICAS_USUARIO = "http://m.filmaffinity.com/es/movieuserreviews.php?movie_id=";

	public ControladorPeliculas() {
		// Permitimos espacios de nombres para las factorias
		dbf.setNamespaceAware(true);
		spf.setNamespaceAware(true);
	}

	public static ControladorPeliculas getInstance() {
		return controladorPeliculasInstance;
	}

	private String crearUrlBusqueda(String campoBusqueda, int tipo) {
		String urlBusqueda = URL_BUSQUEDA + campoBusqueda + TIPO_BUSQUEDA;
		if (tipo == TIPO_TITULO)
			urlBusqueda += "title";
		else if (tipo == TIPO_REPARTO)
			urlBusqueda += "cast";
		else if (tipo == TIPO_DIRECTOR)
			urlBusqueda += "director";

		return urlBusqueda;
	}

	// Busca las peliculas segun la web de filmaffinity, a traves de SAX
	// Busca las peliculas y libros en amazon (solo funcionaria cuando es el
	// tipo titulo)
	public List<Pelicula> buscar(String campoBusqueda, int tipo)
			throws PeliculaException {
		List<Pelicula> listaPeliculas = new LinkedList<Pelicula>();
		// Creamos el url de busqueda
		try {
			URL busqueda = new URL(crearUrlBusqueda(campoBusqueda, tipo));
			// Obtenemos el parser
			SAXParser saxParser = spf.newSAXParser();
			// Parseamos con el handler el stream XHTML
			// Cada vez que busquemos una pelicula debemos crear un nuevo
			// handler
			// sino los resultados de la busqueda anterior siguen ahi
			buscadorHandler = new BuscadorHandler();
			saxParser.parse(new InputSource(busqueda.openStream()),
					buscadorHandler);
			listaPeliculas = buscadorHandler.getResultadosBusqueda();

			for (Pelicula pelicula : listaPeliculas) {
				// Buscaremos articulos de amazon para cada pelicula
				List<PeliculaAmazon> listaPeliculasAmazon = pelicula
						.getPeliculaAmazon();
				List<LibroAmazon> listaLibrosAmazon = pelicula.getLibroAmazon();
				listaPeliculasAmazon.addAll(controladorAmazon
						.buscarPelicula(pelicula.getTitulo()));
				listaLibrosAmazon.addAll(controladorAmazon.buscarLibro(pelicula
						.getTitulo()));
			}
		} catch (Exception e) {
			throw new PeliculaException("No se ha podido buscar el campo"
					+ "\n" + e.toString());
		}
		return listaPeliculas;
	}

	// Crea una pelicula obteniendo TODOS LOS DATOS
	public Pelicula obtenerPelicula(long id) throws PeliculaException {
		Pelicula pelicula = of.createPelicula();
		try {
			pelicula.setId(BigInteger.valueOf(id));
			// Necesita todos los datos
			pelicula.setDescripcionPelicula(obtenerDescripcionPelicula(id));
			pelicula.setCriticasPrensa(obtenerCriticasPrensa(id));
			pelicula.setCriticasUsuario(obtenerCriticasUsuario(id));
			pelicula.setTitulo(pelicula.getDescripcionPelicula().getTitulo());

			// Copiamos una lista a otra
			List<PeliculaAmazon> listaPeliculas = pelicula.getPeliculaAmazon();
			listaPeliculas.addAll(controladorAmazon
					.obtenerInformacionPelicula(pelicula.getTitulo()));

			List<LibroAmazon> listaLibros = pelicula.getLibroAmazon();
			listaLibros.addAll(controladorAmazon
					.obtenerInformacionLibro(pelicula.getTitulo()));
		} catch (Exception e) {
			throw new PeliculaException("No se ha podido buscar el campo"
					+ "\n" + e.toString());
		}
		return pelicula;
	}

	public DescripcionPelicula obtenerDescripcionPelicula(long id)
			throws PeliculaException {
		try {
			// Necesitamos el dom de XHTML para trabajar
			// Creamos la url de la pelicula
			URL urlPelicula = new URL(URL_PELICULA + id);
			// Obtenemos el parser
			SAXParser saxParser = spf.newSAXParser();
			// Obtenemos un transformer para poder transformar el documento
			// XHTML a
			// DOM
			Transformer transformer = tf.newTransformer();
			// Para transformar necesitamos una fuente y un output
			// La fuente va a ser un contenedor SAX a partir de nuestro
			// parser y
			// el
			// HTML de la pelicula
			SAXSource source = new SAXSource(saxParser.getXMLReader(),
					new InputSource(urlPelicula.openStream()));
			// Problema: error 403, el servidor no quiere responder
			// Solucion: esperar un tiempo
			Thread.sleep(1000);
			// El resultado es un contenedor DOM para almacenar el documento
			// resultante de la transformacion
			DOMResult result = new DOMResult();
			transformer.transform(source, result);
			// Lo asignamos a nuestro documento
			Document domPelicula = (Document) result.getNode();
			descripcionPeliculaHandler = new DescripcionPeliculaHandler();
			DescripcionPelicula descripcionPelicula = descripcionPeliculaHandler
					.getDescripcionPelicula(domPelicula);
			return descripcionPelicula;
		} catch (Exception e) {
			throw new PeliculaException(
					"No se ha podido obtener la descripción" + "\n"
							+ e.toString());
		}
	}

	public CriticasPrensa obtenerCriticasPrensa(long id)
			throws PeliculaException {
		try {
			URL urlCriticasPrensa = new URL(URL_CRITICAS_PRENSA + id);
			SAXParser saxParser = spf.newSAXParser();
			criticasPrensaHandler = new CriticasPrensaHandler();
			saxParser.parse(new InputSource(urlCriticasPrensa.openStream()),
					criticasPrensaHandler);
			CriticasPrensa criticasPrensa = criticasPrensaHandler
					.getCriticasPrensa();
			return criticasPrensa;
		} catch (Exception e) {
			throw new PeliculaException(
					"No se ha podido obtener las críticas de prensa" + "\n"
							+ e.toString());
		}
	}

	public CriticasUsuario obtenerCriticasUsuario(long id)
			throws PeliculaException {
		try {
			URL urlCriticasUsuario = new URL(URL_CRITICAS_USUARIO + id);
			SAXParser saxParser = spf.newSAXParser();
			criticasUsuarioHandler = new CriticasUsuarioHandler();
			saxParser.parse(new InputSource(urlCriticasUsuario.openStream()),
					criticasUsuarioHandler);
			CriticasUsuario criticasUsuario = criticasUsuarioHandler
					.getCriticasUsuario();
			return criticasUsuario;
		} catch (Exception e) {
			throw new PeliculaException(
					"No se ha podido obtener las críticas de usuario" + "\n"
							+ e.toString());
		}
	}

	public Document obtenerDOM(long id) throws PeliculaException {
		try {
			Pelicula pelicula = obtenerPelicula(id);

			DocumentBuilder builder = dbf.newDocumentBuilder();
			Document domPeliculas = builder.newDocument();
			// Empaquetamos la pelicula en el nuevo DOM
			JAXBContext jc = JAXBContext.newInstance("es.um.arso.pelicula");
			Marshaller m = jc.createMarshaller();
			// Con UTF-8 (el por defecto) los caracteres no se leen bien
			m.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(pelicula, domPeliculas);

			return domPeliculas;
		} catch (Exception e) {
			throw new PeliculaException(
					"No se ha podido obtener la representación DOM" + "\n"
							+ e.toString());
		}
	}

	public void exportar(long id, String ruta) throws PeliculaException {
		try {
			Pelicula peliculas = obtenerPelicula(id);
			JAXBContext jc = JAXBContext.newInstance("es.um.arso.pelicula");
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(peliculas, new FileWriter(new File(ruta)));
		} catch (Exception e) {
			throw new PeliculaException("No se ha podido exportar la película"
					+ "\n" + e.toString());
		}
	}

	public void transformar(long id, String urlHtml, String urlXsl)
			throws PeliculaException {
		try {
			Transformer transformacion = tf.newTransformer(new StreamSource(
					urlXsl));
			transformacion.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
			transformacion.setOutputProperty(OutputKeys.METHOD, "html");
			transformacion
					.setOutputProperty(OutputKeys.MEDIA_TYPE, "text/html");
			transformacion.setOutputProperty(OutputKeys.INDENT, "yes");
			Document domPeliculas = obtenerDOM(id);

			DOMSource input = new DOMSource(domPeliculas);
			StreamResult output = new StreamResult(urlHtml);
			transformacion.transform(input, output);
		} catch (Exception e) {
			throw new PeliculaException(
					"No se ha podido transformar la película" + "\n"
							+ e.toString());
		}
	}

}
