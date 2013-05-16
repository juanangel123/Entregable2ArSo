
package es.um.arso.cliente.tipos;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "BuscadorPeliculas", targetNamespace = "http://server.arso.um.es/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface BuscadorPeliculas {


    /**
     * 
     * @param titulo
     * @return
     *     returns java.util.List<es.um.arso.cliente.tipos.Pelicula>
     * @throws PeliculaException_Exception
     */
    @WebMethod
    @WebResult(name = "pelicula", targetNamespace = "http://server.arso.um.es/")
    @RequestWrapper(localName = "buscarPorTitulo", targetNamespace = "http://server.arso.um.es/", className = "es.um.arso.cliente.tipos.BuscarPorTitulo")
    @ResponseWrapper(localName = "buscarPorTituloResponse", targetNamespace = "http://server.arso.um.es/", className = "es.um.arso.cliente.tipos.BuscarPorTituloResponse")
    public List<Pelicula> buscarPorTitulo(
        @WebParam(name = "titulo", targetNamespace = "")
        String titulo)
        throws PeliculaException_Exception
    ;

    /**
     * 
     * @param identificador
     * @return
     *     returns es.um.arso.cliente.tipos.Pelicula
     * @throws PeliculaException_Exception
     */
    @WebMethod
    @WebResult(name = "pelicula", targetNamespace = "http://server.arso.um.es/")
    @RequestWrapper(localName = "obtenerInformacion", targetNamespace = "http://server.arso.um.es/", className = "es.um.arso.cliente.tipos.ObtenerInformacion")
    @ResponseWrapper(localName = "obtenerInformacionResponse", targetNamespace = "http://server.arso.um.es/", className = "es.um.arso.cliente.tipos.ObtenerInformacionResponse")
    public Pelicula obtenerInformacion(
        @WebParam(name = "identificador", targetNamespace = "")
        long identificador)
        throws PeliculaException_Exception
    ;

}
