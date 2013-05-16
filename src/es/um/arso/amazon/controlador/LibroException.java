package es.um.arso.amazon.controlador;

// Esta excepción luego se encapsula en PeliculaException, que es mas general
public class LibroException extends Exception {

	private static final long serialVersionUID = -931248900258874713L;

	public LibroException(String mensaje) {
		super(mensaje);
	}

	public LibroException() {
		super();
	}

}
