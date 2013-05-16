package es.um.arso.buscador.handler;

import java.math.BigInteger;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import es.um.arso.pelicula.DescripcionPelicula;
import es.um.arso.pelicula.ObjectFactory;

public class DescripcionPeliculaHandler {
	
	// Elementos de la pelicula
	private final String TITULO_ORIGINAL = "TÍTULO ORIGINAL";
	private final String ANO_PAIS = "AÑO / PAÍS";
	private final String ESTRENO = "ESTRENO";
	private final String DURACION = "DURACIÓN";
	private final String DIRECTOR = "DIRECTOR";
	private final String REPARTO = "REPARTO";
	private final String GUION = "GUIÓN";
	private final String MUSICA = "MÚSICA";
	private final String FOTOGRAFIA = "FOTOGRAFÍA";
	private final String PRODUCTORA = "PRODUCTORA";
	private final String GENERO = "GÉNERO";
	private final String PREMIOS = "PREMIOS";
	private final String SINOPSIS = "SINOPSIS";
	private final String PUNTUACION = "movie-rating";
	private final String VOTOS = "count";
	private final String IMAGEN = "movie-poster";
	private final String TITULO = "titulo ui-state-default";
	
	// Elementos de HTML
	private final String DIV = "div";
	private final String CLASS = "class";
	private final String HREF = "href";
	private final String A = "a";
	private final String SPAN = "span";

	private DescripcionPelicula descripcionPelicula = null;

	private final ObjectFactory of = new ObjectFactory();
	
	public DescripcionPelicula getDescripcionPelicula(Document documento)
			throws Exception {
		if (descripcionPelicula == null)
			obtenerDescripcion(documento);
		return descripcionPelicula;
	}

	// Obtiene la descripcion de la pelicula a partir del dom de la pagina de
	// filmaffinity, trabaja con el DOM
	private void obtenerDescripcion(Document documento) throws Exception {
		// Creamos el objeto descripcion
		descripcionPelicula = of.createDescripcionPelicula();

		// Buscar todos los atributos en el DOM
		// Buscamos los elementos div
		NodeList listaNodos = documento.getElementsByTagName(DIV);
		for (int i = 0; i < listaNodos.getLength(); i++) {
			Element nodoDiv = (Element) listaNodos.item(i);
			String valorClass = nodoDiv.getAttribute(CLASS);
			// Si tiene un atributo de tipo class
			if (!valorClass.isEmpty()) {
				// Si su valor es movie rating, almacenamos la PUNTUACION
				if (valorClass.equals(PUNTUACION)) {
					// Francia es uno de los paises que tiene la coma como
					// separador entre entero y decimal
					NumberFormat format = NumberFormat
							.getInstance(Locale.FRANCE);
					Number numberPuntuacion = format.parse(nodoDiv
							.getTextContent());
					double puntuacion = numberPuntuacion.doubleValue();
					descripcionPelicula.setPuntuacion(puntuacion);
					// Si su valor es count, almacenamos los VOTOS
				} else if (valorClass.equals(VOTOS)) {
					String votos = nodoDiv.getTextContent();
					Pattern pattern = Pattern.compile("\\d+");
					Matcher matcher = pattern.matcher(votos.replace(".", ""));
					matcher.find();
					BigInteger numeroVotos = new BigInteger(matcher.group());
					descripcionPelicula.setVotos(numeroVotos);
				}
				// Almacenamos la IMAGEN en url
				else if (valorClass.equals(IMAGEN)) {
					Node nodoHijo1 = nodoDiv.getFirstChild();
					// Obtenemos el nodo hijo siguiente hasta obtener el
					// siguiente div
					while (nodoHijo1 != null
							&& (nodoHijo1.getNodeType() != Node.ELEMENT_NODE)
							&& !nodoHijo1.getNodeName().equals(DIV))
						nodoHijo1 = nodoHijo1.getNextSibling();
					Node nodoHijo2 = nodoHijo1.getFirstChild();
					// Lo mismo pero para el elemento a
					while (nodoHijo2 != null
							&& (nodoHijo2.getNodeType() != Node.ELEMENT_NODE)
							&& !nodoHijo2.getNodeName().equals(A))
						nodoHijo2 = nodoHijo2.getNextSibling();
					descripcionPelicula.setImagen(((Element) nodoHijo2)
							.getAttribute(HREF));
				}
				// Almacenamos titulo de la pelicula
				else if (valorClass.equals(TITULO))
					descripcionPelicula.setTitulo(nodoDiv.getTextContent()
							.trim());
			}
		}

		// El resto de los atributos son de nombre span y valor el texto del div
		listaNodos = documento.getElementsByTagName(SPAN);
		for (int i = 0; i < listaNodos.getLength(); i++) {
			// Obtenemos el nodo span
			Element nodoSpan = (Element) listaNodos.item(i);
			// Obtenemos el nodo div siguiente
			Node elementoDiv = nodoSpan.getNextSibling();
			while (elementoDiv != null
					&& elementoDiv.getNodeType() != Node.ELEMENT_NODE)
				elementoDiv = elementoDiv.getNextSibling();
			if (nodoSpan.getTextContent().equals(TITULO_ORIGINAL))
				descripcionPelicula.setTituloOriginal(elementoDiv
						.getTextContent());
			// Es el atributo AÑO y el atributo PAIS
			else if (nodoSpan.getTextContent().equals(ANO_PAIS)) {
				String anoPais = elementoDiv.getTextContent();
				SimpleDateFormat format = new SimpleDateFormat("yyyy");
				String[] tokensAnoPais = anoPais.split("/");
				String ano = tokensAnoPais[0].substring(0,
						tokensAnoPais[0].length() - 1);
				String pais = tokensAnoPais[1].substring(2,
						tokensAnoPais[1].length());
				GregorianCalendar c = new GregorianCalendar();
				c.setTime(format.parse(ano));
				XMLGregorianCalendar date = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(c);
				descripcionPelicula.setAno(date);
				descripcionPelicula.setPais(pais);
			}
			// Es el atributo ESTRENO
			else if (nodoSpan.getTextContent().equals(ESTRENO)) {
				String estreno = elementoDiv.getTextContent();
				// El formato en el que se muestra la fecha
				SimpleDateFormat format = new SimpleDateFormat(
						"dd 'de' MMMM 'de' yyyy");
				GregorianCalendar c = new GregorianCalendar();
				c.setTime(format.parse(estreno));
				XMLGregorianCalendar date = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(c);
				descripcionPelicula.setEstreno(date);
			} else if (nodoSpan.getTextContent().equals(DURACION)) {
				String duracion = elementoDiv.getTextContent();
				Pattern pattern = Pattern.compile("\\d+");
				Matcher matcher = pattern.matcher(duracion);
				matcher.find();
				BigInteger numeroDuracion;
				try {
					// Problemas si no hay minutos asignados (peliculas sin
					// estrenar)
					numeroDuracion = new BigInteger(matcher.group());
				} catch (IllegalStateException e) {
					numeroDuracion = new BigInteger("0");
				}
				descripcionPelicula.setDuracion(numeroDuracion);
			} else if (nodoSpan.getTextContent().equals(DIRECTOR)) {
				String director = elementoDiv.getTextContent().trim();
				String[] directores = director.split(",");
				for (String str : directores) {
					descripcionPelicula.getDirector().add(str.trim());
				}
			} else if (nodoSpan.getTextContent().equals(REPARTO)) {
				String reparto = elementoDiv.getTextContent().trim();
				String[] repartos = reparto.split(",");
				for (String str : repartos) {
					descripcionPelicula.getReparto().add(str.trim());
				}
			} else if (nodoSpan.getTextContent().equals(GUION)) {
				String guion = elementoDiv.getTextContent().trim();
				String[] guionistas = guion.split(",");
				for (String str : guionistas) {
					descripcionPelicula.getGuion().add(str.trim());
				}
			} else if (nodoSpan.getTextContent().equals(MUSICA)) {
				String musica = elementoDiv.getTextContent().trim();
				String[] musicos = musica.split(",");
				for (String str : musicos) {
					descripcionPelicula.getMusica().add(str.trim());
				}
			} else if (nodoSpan.getTextContent().equals(FOTOGRAFIA)) {
				String fotografia = elementoDiv.getTextContent().trim();
				String[] fotografos = fotografia.split(",");
				for (String str : fotografos) {
					descripcionPelicula.getFotografia().add(str.trim());
				}
			} else if (nodoSpan.getTextContent().equals(PRODUCTORA)) {
				String productora = elementoDiv.getTextContent().trim();
				String[] productoras = productora.split("/");
				for (String str : productoras) {
					descripcionPelicula.getProductora().add(str.trim());
				}
			} else if (nodoSpan.getTextContent().equals(GENERO)) {
				String genero = elementoDiv.getTextContent().trim();
				String[] generos = genero.split("[.]");
				for (String str : generos) {
					descripcionPelicula.getGenero().add(str.trim());
				}
			} else if (nodoSpan.getTextContent().equals(PREMIOS))
				descripcionPelicula.setPremios(elementoDiv.getTextContent());
			else if (nodoSpan.getTextContent().equals(SINOPSIS))
				descripcionPelicula.setSinopsis(elementoDiv.getTextContent());
		}
	}
}
