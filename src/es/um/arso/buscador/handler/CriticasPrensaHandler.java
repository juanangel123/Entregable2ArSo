package es.um.arso.buscador.handler;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import es.um.arso.pelicula.CriticaPrensa;
import es.um.arso.pelicula.CriticasPrensa;
import es.um.arso.pelicula.ObjectFactory;


public class CriticasPrensaHandler extends DefaultHandler {
	
	// Elementos de HTML
	private final String LI = "li";
	private final String DIV = "div";
	private final String CLASS = "class";
	private final String HREF = "href";
	private final String A = "a";

	// Elementos de la critica
	private final String SYNOPSIS = "synopsis";
	private final String MEDIA = "media";
	
	private final ObjectFactory of = new ObjectFactory(); 

	private CriticasPrensa criticasPrensa = of.createCriticasPrensa();
	private CriticaPrensa criticaActual;

	private boolean estoyEnCritica = false;

	private boolean estoyLeyendoSinopsis = false;
	private boolean estoyLeyendoNombre = false;
	private boolean estoyLeyendoMedia = false;

	public CriticasPrensa getCriticasPrensa() {
		return criticasPrensa;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		if (localName.equals(LI)) {
			estoyEnCritica = true;
			criticaActual = of.createCriticaPrensa();
			criticaActual.setCritico("");
			criticaActual.setMedia("");
			criticaActual.setSinopsis("");
		}
		// Si es un enlace entonces href es obligatorio
		else if (localName.equals(A) && estoyEnCritica)
			criticaActual.setUrlCritica(attributes.getValue(HREF));
		else if (estoyEnCritica
				&& (localName.equals(DIV)
						&& (attributes.getValue(CLASS) != null) && (attributes
							.getValue(CLASS).equals(SYNOPSIS))))
			estoyLeyendoSinopsis = true;
		// Tengo que leer el nombre (estoy en un div sin atributos)
		else if (estoyEnCritica && localName.equals(DIV)
				&& (attributes.getLength() == 0))
			estoyLeyendoNombre = true;
		else if (estoyEnCritica && localName.equals(DIV)
				&& (attributes.getValue(CLASS) != null)
				&& (attributes.getValue(CLASS).equals(MEDIA)))
			estoyLeyendoMedia = true;
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (localName.equals(LI) && estoyEnCritica) {
			estoyEnCritica = false;
			criticasPrensa.getCriticaPrensa().add(criticaActual);
		} else if (estoyLeyendoSinopsis)
			estoyLeyendoSinopsis = false;
		else if (estoyLeyendoMedia)
			estoyLeyendoMedia = false;
		else if (estoyLeyendoNombre)
			estoyLeyendoNombre = false;
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		if (estoyLeyendoSinopsis) {
			String sinopsis = String.valueOf(ch, start, length);
			criticaActual.setSinopsis(criticaActual.getSinopsis() + sinopsis);
		} else if (estoyLeyendoNombre) {
			String nombre = String.valueOf(ch, start, length);
			criticaActual.setCritico(criticaActual.getCritico() + nombre);
		} else if (estoyLeyendoMedia) {
			String media = String.valueOf(ch, start, length);
			// Hay espacios inutiles
			criticaActual.setMedia(criticaActual.getMedia() + media.trim());
		}
	}
}
