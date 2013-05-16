package es.um.arso.amazon;

import es.um.arso.amazon.controlador.ControladorAmazon;

public class TestControlador {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ControladorAmazon ca = ControladorAmazon.getInstance();
//		ca.buscarPelicula("django");
		ca.buscarLibro("Los Juegos del Hambre");
		System.out.println("Fin");
	}

}
