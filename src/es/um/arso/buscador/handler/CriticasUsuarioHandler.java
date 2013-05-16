package es.um.arso.buscador.handler;

import java.math.BigInteger;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import es.um.arso.pelicula.CriticaUsuario;
import es.um.arso.pelicula.CriticasUsuario;
import es.um.arso.pelicula.ObjectFactory;


public class CriticasUsuarioHandler extends DefaultHandler {
	
	// Elementos de HTML
	private final String DIV = "div";
	private final String CLASS = "class";
	private final String HREF = "href";
	private final String A = "a";
	private final String SPAN = "span";
	private final String SRC = "src";
	private final String IMG = "img";
	private final String LI = "li";
	
	// Elementos de la critica
	private final String CARD = "user-review-card";
	private final String TITLE = "title";
	private final String USER = "user";

	
	private final ObjectFactory of = new ObjectFactory(); 

	private CriticasUsuario criticasUsuario = of.createCriticasUsuario();
	private CriticaUsuario criticaActual;

	private boolean estoyEnCritica = false;
	private boolean estoyEnDiv = false;

	private boolean estoyLeyendoTitulo = false;
	private boolean estoyLeyendoUsuario = false;

	public CriticasUsuario getCriticasUsuario() {
		return criticasUsuario;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		if (localName.equals(DIV) && (attributes.getValue(CLASS) != null)
				&& (attributes.getValue(CLASS).equals(CARD))) {
			estoyEnCritica = true;
			criticaActual = of.createCriticaUsuario();
			// Problemas al leer
			criticaActual.setTitulo("");
		} else if (estoyEnCritica && localName.equals(A))
			criticaActual.setUrlCritica(attributes.getValue(HREF));
		else if (estoyEnCritica && localName.equals(LI)
				&& (attributes.getValue(CLASS) != null)
				&& (attributes.getValue(CLASS).equals(TITLE)))
			estoyLeyendoTitulo = true;
		else if (estoyEnCritica && localName.equals(SPAN)
				&& (attributes.getValue(CLASS) != null)
				&& (attributes.getValue(CLASS).equals(USER)))
			estoyLeyendoUsuario = true;
		else if (estoyEnCritica && localName.equals(DIV))
			estoyEnDiv = true;
		else if (estoyEnDiv && localName.equals(IMG)) {
			String puntuacion = attributes.getValue(SRC);
			String [] tokensPuntuacion = puntuacion.split("/");
			String archivoPuntuacion = tokensPuntuacion[tokensPuntuacion.length-1];
			criticaActual.setPuntuacion(new BigInteger(archivoPuntuacion.substring(0, archivoPuntuacion.length()-4)));
		}
			
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if (localName.equals(DIV) && estoyEnCritica) {
			estoyEnDiv = false;
			estoyEnCritica = false;
			criticasUsuario.getCriticaUsuario().add(criticaActual);
		} else if (estoyLeyendoTitulo)
			estoyLeyendoTitulo = false;
		else if (estoyLeyendoUsuario)
			estoyLeyendoUsuario = false;
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		if (estoyLeyendoTitulo) {
			String titulo = String.valueOf(ch, start, length);
			criticaActual.setTitulo(criticaActual.getTitulo() + titulo);
		} else if (estoyLeyendoUsuario) {
			String usuario = String.valueOf(ch, start, length);
			criticaActual.setUsuario(usuario);
		}
	}
}
