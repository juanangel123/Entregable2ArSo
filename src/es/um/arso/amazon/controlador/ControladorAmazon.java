package es.um.arso.amazon.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import com.ecs.client.jax.AWSECommerceService;
import com.ecs.client.jax.AWSECommerceServicePortType;
import com.ecs.client.jax.Item;

import com.ecs.client.jax.ItemLookup;
import com.ecs.client.jax.ItemLookupRequest;
import com.ecs.client.jax.ItemLookupResponse;
import com.ecs.client.jax.ItemSearch;
import com.ecs.client.jax.ItemSearchRequest;
import com.ecs.client.jax.ItemSearchResponse;
import com.ecs.client.jax.Items;

import es.um.arso.amazon.handler.AwsHandlerResolver;
import es.um.arso.amazon.handler.BuscadorLibrosHandler;
import es.um.arso.amazon.handler.BuscadorPeliculasHandler;
import es.um.arso.amazon.util.SignedRequestsHelper;
import es.um.arso.pelicula.LibroAmazon;
import es.um.arso.pelicula.PeliculaAmazon;

public class ControladorAmazon {

	private final static ControladorAmazon controladorAmazonInstance = new ControladorAmazon();

	// Parametros necesarios para poder realizar la busqueda
	private static final String AWS_ACCESS_KEY_ID = "AKIAJHGHHY4CQNGY4SEA";
	private static final String ASSOCIATE_TAG = "arSoPracticas";
	private static final String AWS_SECRET_KEY = "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";
	private static final String ENDPOINT = "webservices.amazon.es";

	// Servicio
	private AWSECommerceService commerceService;
	private AWSECommerceServicePortType servicePortType;

	// Handlers
	private BuscadorPeliculasHandler buscadorPeliculasHandler = new BuscadorPeliculasHandler();
	private BuscadorLibrosHandler buscadorLibrosHandler = new BuscadorLibrosHandler();
	
	// Factorias
	private DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


	public static ControladorAmazon getInstance() {
		return controladorAmazonInstance;
	}

	public ControladorAmazon() {
		commerceService = new AWSECommerceService();
		commerceService.setHandlerResolver(new AwsHandlerResolver(
				AWS_SECRET_KEY));
		// Accedemos al portType español
		servicePortType = commerceService.getAWSECommerceServicePortES();
	}

	// Limitamos la busqueda a 10 (max), es más comodo y no hay que buscar nada
	// mas
	public List<PeliculaAmazon> buscarPelicula(String titulo) {
		ItemSearch itemSearch = new ItemSearch();
		itemSearch.setAWSAccessKeyId(AWS_ACCESS_KEY_ID);
		itemSearch.setAssociateTag(ASSOCIATE_TAG);

		ItemSearchRequest searchRequest = new ItemSearchRequest();
		searchRequest.setSearchIndex("DVD");
		// Salen mejores busquedas por palabras y con el criterio de ordenacion
		// que con solo el parametro title
		searchRequest.setKeywords(titulo);
		searchRequest.setSort("relevancerank");
		itemSearch.getRequest().add(searchRequest);

		List<String> responseGroup = searchRequest.getResponseGroup();
		responseGroup.add("ItemAttributes");

		ItemSearchResponse searchResponse = servicePortType
				.itemSearch(itemSearch);

		List<Items> listaResultados = searchResponse.getItems();

		List<Item> resultadosBusqueda = listaResultados.get(0).getItem();
		return buscadorPeliculasHandler
				.almacenarResultadoPeliculas(resultadosBusqueda);
	}

	public List<PeliculaAmazon> obtenerInformacionPelicula(String titulo) {
		// Se crea una nueva busqueda
		ItemSearch itemSearch = new ItemSearch();
		itemSearch.setAWSAccessKeyId(AWS_ACCESS_KEY_ID);
		itemSearch.setAssociateTag(ASSOCIATE_TAG);

		ItemSearchRequest searchRequest = new ItemSearchRequest();
		searchRequest.setSearchIndex("DVD");
		searchRequest.setKeywords(titulo);
		searchRequest.setSort("relevancerank");
		itemSearch.getRequest().add(searchRequest);

		List<String> responseGroup = searchRequest.getResponseGroup();
		responseGroup.add("ItemAttributes");

		ItemSearchResponse searchResponse = servicePortType
				.itemSearch(itemSearch);

		List<Items> listaResultados = searchResponse.getItems();
		List<Item> resultadosBusqueda = listaResultados.get(0).getItem();

		return buscadorPeliculasHandler
				.almacenarInformacionPeliculas(resultadosBusqueda);
	}

	public PeliculaAmazon obtenerInformacionPeliculaASIN(String asin) {
		ItemLookup itemLookup = new ItemLookup();
		itemLookup.setAWSAccessKeyId(AWS_ACCESS_KEY_ID);
		itemLookup.setAssociateTag(ASSOCIATE_TAG);

		ItemLookupRequest lookupRequest = new ItemLookupRequest();
		lookupRequest.setIdType("ASIN");
		lookupRequest.getItemId().add(asin);
		itemLookup.getRequest().add(lookupRequest);

		List<String> responseGroup = lookupRequest.getResponseGroup();
		responseGroup.add("ItemAttributes");

		ItemLookupResponse lookupResponse = servicePortType
				.itemLookup(itemLookup);

		List<Items> listaResultados = lookupResponse.getItems();
		List<Item> resultadosBusqueda = listaResultados.get(0).getItem();
		// Solo hay un resultado, el identificador es unico
		List<PeliculaAmazon> listaPeliculas = buscadorPeliculasHandler
				.almacenarInformacionPeliculas(resultadosBusqueda);
		// Si la lista está vacía no ha habido resultados
		if (listaPeliculas.isEmpty())
			return null;
		return listaPeliculas.get(0);
	}

	public List<LibroAmazon> buscarLibro(String titulo) throws LibroException {
		SignedRequestsHelper helper = null;
		try {
			helper = SignedRequestsHelper.getInstance(ENDPOINT,
					AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String requestUrl = null;

		// Codifico en forma de mapa
		Map<String, String> params = new HashMap<String, String>();
		params.put("Service", "AWSECommerceService");
		params.put("Version", "2011-08-01");
		params.put("AssociateTag", ASSOCIATE_TAG);
		params.put("Operation", "ItemSearch");
		params.put("SearchIndex", "Books");
		params.put("Sort", "relevancerank");
		params.put("Keywords", titulo);
		params.put("ResponseGroup", "ItemAttributes");

		requestUrl = helper.sign(params);

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(requestUrl);
			return buscadorLibrosHandler.almacenarResultadoLibros(doc);
		} catch (Exception e) {
			throw new LibroException("No se ha podido buscar el libro" + "\n"
					+ e.toString());
		}
	}

	public List<LibroAmazon> obtenerInformacionLibro(String titulo)
			throws LibroException {
		SignedRequestsHelper helper = null;
		try {
			helper = SignedRequestsHelper.getInstance(ENDPOINT,
					AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String requestUrl = null;

		// Codifico en forma de mapa
		Map<String, String> params = new HashMap<String, String>();
		params.put("Service", "AWSECommerceService");
		params.put("Version", "2011-08-01");
		params.put("AssociateTag", ASSOCIATE_TAG);
		params.put("Operation", "ItemSearch");
		params.put("SearchIndex", "Books");
		params.put("Sort", "relevancerank");
		params.put("Keywords", titulo);
		params.put("ResponseGroup", "ItemAttributes");

		requestUrl = helper.sign(params);

		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(requestUrl);
			return buscadorLibrosHandler.almacenarInformacionLibros(doc);
		} catch (Exception e) {
			throw new LibroException(
					"No se ha podido obtener la informacion del libro" + "\n"
							+ e.toString());
		}
	}
}
