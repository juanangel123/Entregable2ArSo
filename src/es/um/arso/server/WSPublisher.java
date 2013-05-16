package es.um.arso.server;

import javax.xml.ws.Endpoint;

//Endpoint publisher (test)
public class WSPublisher {

	public static void main(String[] args) {
	   Endpoint.publish("http://localhost:8080/Entregable2/", new BuscadorPeliculasImpl());

	   System.out.println("Service is published!");
  }

}