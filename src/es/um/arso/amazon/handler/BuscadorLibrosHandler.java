package es.um.arso.amazon.handler;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import es.um.arso.pelicula.DescripcionLibroAmazon;
import es.um.arso.pelicula.Language;
import es.um.arso.pelicula.Languages;
import es.um.arso.pelicula.LibroAmazon;
import es.um.arso.pelicula.ObjectFactory;
import es.um.arso.pelicula.Price;

public class BuscadorLibrosHandler {

	private ObjectFactory of = new ObjectFactory();

	public List<LibroAmazon> almacenarResultadoLibros(Document document) {
		List<LibroAmazon> listaLibros = new LinkedList<LibroAmazon>();
		// Buscamos los items
		NodeList listaItems = document.getElementsByTagName("Item");
		for (int i = 0; i < listaItems.getLength(); i++) {
			Element item = (Element) listaItems.item(i);
			LibroAmazon libro = of.createLibroAmazon();
			Element asin = (Element) item.getElementsByTagName("ASIN").item(0);
			libro.setASIN(asin.getTextContent());
			Element title = (Element) item.getElementsByTagName("Title")
					.item(0);
			libro.setTitle(title.getTextContent());

			listaLibros.add(libro);
		}
		return listaLibros;
	}

	public List<LibroAmazon> almacenarInformacionLibros(Document document) {
		List<LibroAmazon> listaLibros = new LinkedList<LibroAmazon>();
		// Buscamos los items
		NodeList listaItems = document.getElementsByTagName("Item");
		for (int i = 0; i < listaItems.getLength(); i++) {
			Element item = (Element) listaItems.item(i);
			LibroAmazon libro = almacenarInformacionLibro(item);

			listaLibros.add(libro);
		}
		return listaLibros;
	}

	private LibroAmazon almacenarInformacionLibro(Element libro) {
		LibroAmazon libroAmazon = of.createLibroAmazon();
		DescripcionLibroAmazon descripcion = of.createDescripcionLibroAmazon();
		Element asin = (Element) libro.getElementsByTagName("ASIN").item(0);
		libroAmazon.setASIN(asin.getTextContent());
		Element title = (Element) libro.getElementsByTagName("Title").item(0);
		libroAmazon.setTitle(title.getTextContent());
		Element binding = (Element) libro.getElementsByTagName("Binding").item(
				0);
		if (binding != null)
			descripcion.setBinding(binding.getTextContent());
		Element detail = (Element) libro.getElementsByTagName("DetailPageURL")
				.item(0);
		if (detail != null)
			descripcion.setDetailPageURL(detail.getTextContent());
		Element ean = (Element) libro.getElementsByTagName("EAN").item(0);
		if (ean != null)
			descripcion.setEAN(ean.getTextContent());
		Element isbn = (Element) libro.getElementsByTagName("ISBN").item(0);
		if (isbn != null)
			descripcion.setISBN(isbn.getTextContent());

//		// Languages
		Languages languages = of.createLanguages();
		Element nodoLanguage = (Element) libro.getElementsByTagName("Language")
				.item(0);
		if (nodoLanguage != null) {
			Language language = of.createLanguage();
			List<Language> listaLanguages = languages.getLanguage();
			Element name = (Element) nodoLanguage.getElementsByTagName("Name")
					.item(0);
			if (name != null)
				language.setName(name.getTextContent());
			Element type = (Element) nodoLanguage.getElementsByTagName("Type")
					.item(0);
			if (type != null)
				language.setType(type.getTextContent());
			listaLanguages.add(language);
		}

		Element noPages = (Element) libro.getElementsByTagName("NumberOfPages")
				.item(0);
		if (noPages != null)
			descripcion.setNumberOfPages(BigInteger.valueOf(Long
					.valueOf(noPages.getTextContent())));

		// Price
		Element nodePrice = (Element) libro.getElementsByTagName("ListPrice")
				.item(0);
		if (nodePrice != null) {
			Price price = of.createPrice();
			Element amount = (Element) nodePrice.getElementsByTagName("Amount")
					.item(0);
			price.setAmount(BigInteger.valueOf(Long.valueOf(amount
					.getTextContent())));
			Element currencyCode = (Element) nodePrice.getElementsByTagName(
					"CurrencyCode").item(0);
			price.setCurrencyCode(currencyCode.getTextContent());
			Element formattedPrice = (Element) nodePrice.getElementsByTagName(
					"FormattedPrice").item(0);
			price.setFormattedPrice(formattedPrice.getTextContent());
			descripcion.setPrice(price);
		}

		Element publisher = (Element) libro.getElementsByTagName("Publisher")
				.item(0);
		if (publisher != null)
			descripcion.setPublisher(publisher.getTextContent());
		Element release = (Element) libro.getElementsByTagName("ReleaseDate")
				.item(0);
		if (release != null)
			descripcion.setReleaseDate(release.getTextContent());

		libroAmazon.setDescripcionLibroAmazon(descripcion);

		return libroAmazon;
	}
}
