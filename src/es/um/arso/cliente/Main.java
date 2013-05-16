package es.um.arso.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import javax.ws.rs.core.MediaType;

import es.um.arso.cliente.tipos.BuscadorPeliculas;
import es.um.arso.cliente.tipos.BuscadorPeliculasImplService;
import es.um.arso.cliente.tipos.LibroAmazon;
import es.um.arso.cliente.tipos.Pelicula;
import es.um.arso.cliente.tipos.PeliculaAmazon;
import es.um.arso.cliente.tipos.PeliculaException_Exception;

public class Main {

	private static BuscadorPeliculasImplService buscadorService = new BuscadorPeliculasImplService();
	private static BuscadorPeliculas buscadorPort = buscadorService
			.getBuscadorPeliculasImplPort();
	
	private final static String ATOM = "atom";
	private final static String JSON = "json";
	private final static String HTML = "html";
	private final static String XML = "xml";

	public static void main(String[] args) {
		long identificador = 929558;
		String titulo = "juegos hambre";
		
		System.out.println("Creando una petición de búsqueda por SOAP...");
		System.out.println("Término de búsqueda: juegos hambre");

		buscarPeliculaSOAP(titulo);

		System.out.println("Creando una petición de información por SOAP...");
		System.out.println("Término de búsqueda: 929558 (DJango)");

		obtenerInformacionSOAP(identificador);

		System.out
				.println("Creando una petición de búsqueda por ATOM / REST...");
		System.out.println("Término de búsqueda: juegos hambre");

		buscarPeliculaREST(titulo, ATOM);

		System.out
				.println("Creando una petición de búsqueda por JSON / REST...");
		System.out.println("Término de búsqueda: juegos hambre");

		buscarPeliculaREST(titulo, JSON);

		System.out
				.println("Creando una petición de informacion por XML / REST...");
		System.out.println("Termino de búsqueda: 929558 (DJango)");

		obtenerInformacionREST(identificador, XML);

		System.out
				.println("Creando una petición de informacion por JSON / REST...");
		System.out.println("Termino de búsqueda: 929558 (DJango)");

		obtenerInformacionREST(identificador, JSON);

		System.out
				.println("Creando una petición de informacion por HTML / REST...");
		System.out.println("Termino de búsqueda: 929558 (DJango)");

		obtenerInformacionREST(identificador, HTML);
	}

	private static void buscarPeliculaSOAP(String titulo) {
		// Llamamos al servicio
		List<Pelicula> listaPeliculas;
		try {
			listaPeliculas = buscadorPort.buscarPorTitulo(titulo);
		} catch (PeliculaException_Exception e) {
			System.out.println("No se pudo realizar la peticion");
			return;
		}

		System.out.println("Número de resultados de peliculas filmaffinity: "
				+ listaPeliculas.size());

		for (Pelicula pelicula : listaPeliculas) {
			mostrarPeliculaSOAP(pelicula);
		}
	}

	private static void obtenerInformacionSOAP(long identificador) {
		Pelicula pelicula;
		try {
			pelicula = buscadorPort.obtenerInformacion(identificador);
		} catch (PeliculaException_Exception e) {
			System.out.println("No se pudo realizar la peticion");
			return;
		}

		mostrarPeliculaSOAP(pelicula);
	}

	private static void mostrarPeliculaSOAP(Pelicula pelicula) {
		System.out.println("Titulo pelicula: " + pelicula.getTitulo());
		List<PeliculaAmazon> listaPeliculasAmazon = pelicula
				.getPeliculaAmazon();

		System.out.println("Número de resultados de peliculas amazon: "
				+ listaPeliculasAmazon.size());

		for (PeliculaAmazon peliculaAmazon : listaPeliculasAmazon) {
			System.out.println("Titulo pelicula amazon: "
					+ peliculaAmazon.getASIN());
		}

		List<LibroAmazon> listaLibrosAmazon = pelicula.getLibroAmazon();

		System.out.println("Número de resultados de libros amazon: "
				+ listaLibrosAmazon.size());

		for (LibroAmazon libroAmazon : listaLibrosAmazon) {
			System.out.println("Titulo libro amazon: " + libroAmazon.getASIN());
		}
	}

	private static void buscarPeliculaREST(String titulo, String tipo) {
		try {
			URL urlPeticion = new URL(
					"http://localhost:8080/Entregable2/buscadorpeliculas/buscarportitulo/" + tipo + "?titulo="
							+ URLEncoder.encode(titulo, "UTF-8"));

			HttpURLConnection conn = (HttpURLConnection) urlPeticion
					.openConnection();
			conn.setRequestMethod("GET");
			if (tipo == ATOM)
				conn.setRequestProperty("Accept", MediaType.APPLICATION_ATOM_XML);
			else if (tipo == JSON) 
				conn.setRequestProperty("Accept", MediaType.APPLICATION_JSON);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Salida...");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void obtenerInformacionREST(long identificador, String tipo) {
		try {
			URL urlPeticion = new URL(
					"http://localhost:8080/Entregable2/buscadorpeliculas/obtenerinformacion/" + tipo + "?id="
							+ identificador);

			HttpURLConnection conn = (HttpURLConnection) urlPeticion
					.openConnection();
			conn.setRequestMethod("GET");
			if (tipo == XML)
				conn.setRequestProperty("Accept", "application/xml");
			else if (tipo == JSON)
				conn.setRequestProperty("Accept", "application/json");
			else if (tipo == HTML)
				conn.setRequestProperty("Accept", "text/html");

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Salida...");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}