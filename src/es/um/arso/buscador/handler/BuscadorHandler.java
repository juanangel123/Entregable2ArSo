package es.um.arso.buscador.handler;


import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import es.um.arso.pelicula.ObjectFactory;
import es.um.arso.pelicula.Pelicula;


public class BuscadorHandler extends DefaultHandler {
	
	private final ObjectFactory of = new ObjectFactory(); 

	private List<Pelicula> resultadosBusqueda = new LinkedList<Pelicula>();
	private Pelicula peliculaActual;

	private boolean estoyProcesandoPelicula = false;
	private boolean estoyProcesandoTitulo = false;

	// Evitar aliasing
	public List<Pelicula> getResultadosBusqueda() {
		return new LinkedList<Pelicula>(resultadosBusqueda);
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		// Compruebo que es un elemento div, que tiene un
		// atributo que se llame class y que ese class se llame "card-content"
		if (localName.equals("div") && (attributes.getValue("class") != null)
				&& (attributes.getValue("class").equals("card-content"))) {
			peliculaActual = of.createPelicula();
			peliculaActual.setTitulo("");
			estoyProcesandoPelicula = true;
		} else if (localName.equals("a") && estoyProcesandoPelicula) {
			String id = attributes.getValue("href");
			String tokensId[] = id.split("=");
			peliculaActual.setId(new BigInteger(tokensId[1]));
		}
		// Busco el li con class title que está dentro de card-content
		else if (localName.equals("li") && estoyProcesandoPelicula
				&& (attributes.getValue("class") != null)
				&& (attributes.getValue("class").equals("title")))
			estoyProcesandoTitulo = true;
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (localName.equals("div") && estoyProcesandoPelicula) {
			estoyProcesandoPelicula = false;
			resultadosBusqueda.add(peliculaActual);
		} else if (estoyProcesandoTitulo)
			estoyProcesandoTitulo = false;
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		if (estoyProcesandoTitulo) {
			String titulo = String.valueOf(ch, start, length);
			peliculaActual.setTitulo(peliculaActual.getTitulo() + titulo);
		}
	}
}
