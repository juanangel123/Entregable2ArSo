package es.um.arso.server;

//import javax.xml.ws.WebFault;

//@WebFault (name="PeliculaException")
public class PeliculaException extends Exception {

	private static final long serialVersionUID = 2591923922684555983L;

	public PeliculaException(String mensaje) {
		super(mensaje);
	}

	public PeliculaException() {
		super();
	}

}
