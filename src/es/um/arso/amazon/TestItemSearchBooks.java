package es.um.arso.amazon;

import java.math.BigInteger;
import java.util.List;

import com.ecs.client.jax.AWSECommerceService;
import com.ecs.client.jax.AWSECommerceServicePortType;
import com.ecs.client.jax.Item;
import com.ecs.client.jax.ItemAttributes;
import com.ecs.client.jax.ItemSearchResponse;
import com.ecs.client.jax.ItemAttributes.Languages;
import com.ecs.client.jax.ItemAttributes.Languages.Language;
import com.ecs.client.jax.ItemSearch;
import com.ecs.client.jax.ItemSearchRequest;
import com.ecs.client.jax.Items;

import es.um.arso.amazon.handler.AwsHandlerResolver;

public class TestItemSearchBooks {
	// Parametros necesarios para poder realizar la busqueda
	private static final String AWS_ACCESS_KEY_ID = "AKIAJHGHHY4CQNGY4SEA";
	private static final String AWS_SECRET_KEY = "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";
	// Hace falta definir alguno, pero no importa cual (no hace falta ser
	// afiliado, no es una aplicacion con aniimo de lucro)
	private static final String ASSOCIATE_TAG = "arSoPracticas";

	public static void main(String[] args) {
		TestItemSearchBooks ist = new TestItemSearchBooks();
		ist.runSearch();
	}

	public void runSearch() {
		AWSECommerceService service = new AWSECommerceService();
		service.setHandlerResolver(new AwsHandlerResolver(AWS_SECRET_KEY));
		// Accedemos al portType español
		AWSECommerceServicePortType port = service
				.getAWSECommerceServicePortES();

		// Se crea una nueva busqueda
		ItemSearch itemSearch = new ItemSearch();
		itemSearch.setAWSAccessKeyId(AWS_ACCESS_KEY_ID);
		itemSearch.setAssociateTag(ASSOCIATE_TAG);

		// Nuevo conjunto de parametros de busqueda (admite muchas busquedas)
		ItemSearchRequest searchRequest = new ItemSearchRequest();
		// Categoría "DVD" (para locale es)
		searchRequest.setSearchIndex("Books");
		searchRequest.setKeywords("Los Juegos del Hambre");
		searchRequest.setSort("relevancerank");

		// Se añade a la busqueda
		itemSearch.getRequest().add(searchRequest);

		// Aqui se especifican los atributos (item) que se van a devolver
		List<String> responseGroup = searchRequest.getResponseGroup();
		// Default: Small
		responseGroup.add("ItemAttributes");

		// Realiza la busqueda
		ItemSearchResponse searchResponse = port.itemSearch(itemSearch);

		List<Items> listaResultados = searchResponse.getItems();
		System.out.println("Numero busquedas: " + listaResultados.size());
		// Se coge la lista de parametros del primer resultado (lista de "item")
		List<Item> resultadosBusqueda = listaResultados.get(0).getItem();

		BigInteger totalResults = listaResultados.get(0).getTotalResults();
		System.out.println("Total resultados: " + totalResults);

		// Solo se muestran los 10 primeros (razon?)
		for (int i = 0; i < resultadosBusqueda.size(); ++i) {
			Item item = resultadosBusqueda.get(i);
			System.out.println("Resultado numero: " + (i + 1));
			// Estos dos importantes
			System.out.println(item.getASIN());
			System.out.println(item.getDetailPageURL());
			ItemAttributes attributes = item.getItemAttributes();
			System.out.println("Binding: " + attributes.getBinding());
			System.out.println("Ean: " + attributes.getEAN());
			List<String> lista =  attributes.getAuthor();
			for (String str : lista) {
				System.out.println("Author: " + str);
			}
			System.out.println("ISBN: " + attributes.getISBN());
			System.out.println("NoPages: " + attributes.getNumberOfPages());
			System.out.println("Publisher: " + attributes.getPublisher());
			if (attributes.getListPrice() != null) {
				System.out.println("Price: " + attributes.getListPrice().getFormattedPrice());
				System.out.println("Price2: " + attributes.getListPrice().getAmount());
				// Va a ser siempre Euros porque estamos en españa (no almacenar)
				System.out.println("Price3: " + attributes.getListPrice().getCurrencyCode());
			} 
			lista = attributes.getFormat();
			for (String str : lista) {
			System.out.println("Format: " + str);
			Languages languages = attributes.getLanguages();
			if (languages != null) {
				List<Language> language = languages.getLanguage();
				for (Language lang : language) {
					System.out.println(lang.getName());
					System.out.println(lang.getType());
				}
			}
		}
		}
	}
}