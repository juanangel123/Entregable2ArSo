package es.um.arso.amazon.handler;

import java.util.LinkedList;
import java.util.List;

import com.ecs.client.jax.Item;
import com.ecs.client.jax.ItemAttributes;
import com.ecs.client.jax.ItemAttributes.Languages;
import com.ecs.client.jax.ItemAttributes.Languages.Language;

import es.um.arso.pelicula.ObjectFactory;
import es.um.arso.pelicula.PeliculaAmazon;
import es.um.arso.pelicula.DescripcionPeliculaAmazon;

public class BuscadorPeliculasHandler {

	// Factorias
	private ObjectFactory of = new ObjectFactory();
	
	// Solo se almacena el asin y el titulo
	public List<PeliculaAmazon> almacenarResultadoPeliculas(List<Item> items) {
		List<PeliculaAmazon> listaPeliculas = new LinkedList<PeliculaAmazon>();

		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			PeliculaAmazon peliculaActual = of.createPeliculaAmazon();
			// Añadimos la informacion
			peliculaActual.setASIN(item.getASIN());
			ItemAttributes attributes = item.getItemAttributes();
			peliculaActual.setTitle(attributes.getTitle());
			// Añado la pelicula a la lista
			listaPeliculas.add(peliculaActual);
		}
		return listaPeliculas;
	}

	// El procesamiento de los item se realiza aqui
	public List<PeliculaAmazon> almacenarInformacionPeliculas(List<Item> items) {
		List<PeliculaAmazon> listaPeliculas = new LinkedList<PeliculaAmazon>();

		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			PeliculaAmazon peliculaActual = almacenarInformacionPelicula(item);
			// Guardamos todos los datos
			// Añado la pelicula a la lista
			listaPeliculas.add(peliculaActual);
		}

		return listaPeliculas;
	}

	private PeliculaAmazon almacenarInformacionPelicula(Item item) {
		PeliculaAmazon peliculaActual = of.createPeliculaAmazon();
		DescripcionPeliculaAmazon descripcionPeliculaActual = of.createDescripcionPeliculaAmazon();
		ItemAttributes attributes = item.getItemAttributes();		
		// Guardamos todos los datos
		peliculaActual.setASIN(item.getASIN());
		peliculaActual.setTitle(attributes.getTitle());
		
		descripcionPeliculaActual.setDetailPageURL(item.getDetailPageURL());


		descripcionPeliculaActual.setBinding(attributes.getBinding());
		descripcionPeliculaActual.setEAN(attributes.getEAN());
		descripcionPeliculaActual.setReleaseDate(attributes.getReleaseDate());
		descripcionPeliculaActual.setStudio(attributes.getStudio());

		descripcionPeliculaActual.setNumberOfDiscs(attributes.getNumberOfDiscs());

		// Este price es el mismo que el otro, pero hay que hacer la
		// conversion
		com.ecs.client.jax.Price price = attributes.getListPrice();
		if (price != null) {
			es.um.arso.pelicula.Price newPrice = of.createPrice();
			newPrice.setAmount(price.getAmount());
			newPrice.setCurrencyCode(price.getCurrencyCode());
			newPrice.setFormattedPrice(price.getFormattedPrice());
			descripcionPeliculaActual.setPrice(newPrice);
		}

		// Formatos
		List<String> listaFormatos = attributes.getFormat();
		for (String formato : listaFormatos)
			descripcionPeliculaActual.getFormat().add(formato);
				

		// Idiomas
		Languages languages = attributes.getLanguages();
		if (languages != null) {
			List<Language> language = languages.getLanguage();
			es.um.arso.pelicula.Languages newLanguages = of
					.createLanguages();
			for (Language lang : language) {
				es.um.arso.pelicula.Language newLanguage = of
						.createLanguage();
				newLanguage.setName(lang.getName());
				newLanguage.setType(lang.getType());
				newLanguage.setAudioFormat(lang.getAudioFormat());
				newLanguages.getLanguage().add(newLanguage);
			}
			// Añadimos idiomas
			descripcionPeliculaActual.setLanguages(newLanguages);
		}
		peliculaActual.setDescripcionPeliculaAmazon(descripcionPeliculaActual);
		return peliculaActual;
	}
}
