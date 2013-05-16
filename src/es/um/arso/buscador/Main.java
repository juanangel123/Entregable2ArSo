package es.um.arso.buscador;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import es.um.arso.buscador.controlador.ControladorPeliculas;
import es.um.arso.pelicula.Pelicula;
import es.um.arso.server.PeliculaException;

public class Main {
	private static ControladorPeliculas controladorPeliculas = ControladorPeliculas
			.getInstance();

	private static List<Pelicula> resultadosBusqueda = new LinkedList<Pelicula>();

	public static void main(String[] args) {
		// Establecemos TagSoup como analizador SAX por defecto
		// Si lo hago asi no puedo transformar a HTML con XLT, razon?
		// System.setProperty("javax.xml.parsers.SAXParserFactory",
		// "org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl");

		System.out.println("Buscador de peliculas de Filmaffinity y Amazon");
		System.out.println("Introduce un comando:");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String linea = null;

		try {
			linea = br.readLine();
		} catch (IOException e) {
			System.out.println("No he podido leer el comando");
			// No se puede hacer nada si no se consigue obtener la entrada
			return;
		}

		// Leemos la entrada
		String[] parametros = linea.split(" ");

		while (!parametros[0].equals("exit")) {
			if (parametros[0].equals("buscar")) {
				String tipo = parametros[1];
				String campoBusqueda = "";
				for (int i = 2; i < parametros.length; i++) {
					campoBusqueda += parametros[i];
					if (i < parametros.length - 1)
						campoBusqueda += "+";
				}
				if (!tipo.equals("titulo") && !tipo.equals("reparto")
						&& !tipo.equals("director"))
					System.out.println("Tipo de busqueda no existente");
				else {
					try {
						if (tipo.equals("titulo"))
							resultadosBusqueda = controladorPeliculas.buscar(
									campoBusqueda,
									ControladorPeliculas.TIPO_TITULO);
						else if (tipo.equals("reparto"))
							resultadosBusqueda = controladorPeliculas.buscar(
									campoBusqueda,
									ControladorPeliculas.TIPO_REPARTO);
						else if (tipo.equals("director"))
							resultadosBusqueda = controladorPeliculas.buscar(
									campoBusqueda,
									ControladorPeliculas.TIPO_DIRECTOR);
						else {
							System.out.println("Tipo de busqueda no existente");
							return;
						}
						System.out.println("Busqueda completada");
					} catch (PeliculaException p) {
						p.printStackTrace();
					}

				}
			} else if (parametros[0].equals("mostrarResultados")) {
				if (resultadosBusqueda == null)
					System.out.println("Busca antes.");
				else if (resultadosBusqueda.isEmpty())
					System.out.println("No se han encontrado resultados");
				else {
					for (int i = 0; i < resultadosBusqueda.size(); i++)
						System.out.println("Resultado numero " + (i + 1) + ": "
								+ resultadosBusqueda.get(i).getTitulo());
				}
			}
			// Muestra un resultado de la lista
			else if (parametros[0].equals("mostrarResultado")) {
				if (resultadosBusqueda == null)
					System.out.println("Busca antes.");
				else if (resultadosBusqueda.isEmpty())
					System.out.println("No se han encontrado resultados");
				else {
					try {
						Integer posicion = Integer.parseInt(parametros[1]) - 1;
						if ((posicion < 0)
								|| posicion >= resultadosBusqueda.size())
							System.out.println("Posicion incorrecta");
						else {
							// Mostramos la pelicula
							Pelicula pelicula = resultadosBusqueda
									.get(posicion);
							try {
								controladorPeliculas
										.transformar(
												pelicula.getId().longValue(),
												"WebContent/pelicula.html",
												"WebContent/xsl/pelicula.xsl");
								Desktop.getDesktop().open(
										new File("WebContent/pelicula.html"));
							} catch (PeliculaException p) {
								// TODO Auto-generated catch block
								p.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} catch (NumberFormatException ex) {
						System.out
								.println("Debe ser una posicion de los resultados");
					}
				}
			} else if (parametros[0].equals("almacenarResultados")) {
				if (resultadosBusqueda == null)
					System.out.println("Busca antes.");
				else if (resultadosBusqueda.isEmpty())
					System.out.println("No se han encontrado resultados");
				else {
					String urlAlmacenar;
					if (parametros.length == 3) {
						urlAlmacenar = parametros[2];
						if (urlAlmacenar.substring(urlAlmacenar.length() - 1,
								urlAlmacenar.length()) != "/")
							urlAlmacenar += "/";
					} else
						urlAlmacenar = "peliculas/";
					// System.out.println(urlAlmacenar);
					for (int i = 0; i < resultadosBusqueda.size(); i++) {
						// Se almacena a partir del codigo de la pelicula
						String urlPelicula = urlAlmacenar
								+ resultadosBusqueda.get(i).getId() + ".xml";
						try {
							Pelicula pelicula = resultadosBusqueda.get(i);
							controladorPeliculas.exportar(pelicula.getId()
									.longValue(), urlPelicula);
							System.out.println("Almacenada pelicula "
									+ pelicula.getTitulo());
						} catch (PeliculaException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			} else if (parametros[0].equals("almacenarResultado")) {
				if (resultadosBusqueda == null)
					System.out.println("Busca antes.");
				else if (resultadosBusqueda.isEmpty())
					System.out.println("No se han encontrado resultados");
				else {
					try {
						Integer posicion = Integer.parseInt(parametros[1]) - 1;
						if ((posicion < 0)
								|| posicion >= resultadosBusqueda.size())
							System.out.println("Posicion incorrecta");
						else {
							String urlAlmacenar;
							if (parametros.length == 3) {
								urlAlmacenar = parametros[2];
								if (urlAlmacenar.substring(
										urlAlmacenar.length() - 1,
										urlAlmacenar.length()) != "/")
									urlAlmacenar += "/";
							} else
								urlAlmacenar = "peliculas/";
							String urlPelicula = urlAlmacenar
									+ resultadosBusqueda.get(posicion).getId()
									+ ".xml";
							try {
								Pelicula pelicula = resultadosBusqueda
										.get(posicion);
								controladorPeliculas.exportar(pelicula.getId()
										.longValue(), urlPelicula);
								System.out
										.println("Pelicula almacenada correctamente");
							} catch (PeliculaException e) {
								System.out
										.println("No se ha podido almacenar la pelicula");
							}
						}
					} catch (NumberFormatException ex) {
						System.out
								.println("Debe ser una posicion de los resultados");
					}
				}
			}

			else
				System.out.println("Comando no reconocido");

			// Leemos siguiente comando
			System.out.println("Introduce un comando:");
			try {
				linea = br.readLine();
			} catch (IOException e) {
				System.out.println("No he podido leer el comando");
			}
			parametros = linea.split(" ");
		}
	}
}
