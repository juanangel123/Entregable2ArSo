
package es.um.arso.server.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "obtenerInformacionResponse", namespace = "http://server.arso.um.es/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerInformacionResponse", namespace = "http://server.arso.um.es/")
public class ObtenerInformacionResponse {

    @XmlElement(name = "pelicula", namespace = "http://server.arso.um.es/")
    private es.um.arso.pelicula.Pelicula pelicula;

    /**
     * 
     * @return
     *     returns Pelicula
     */
    public es.um.arso.pelicula.Pelicula getPelicula() {
        return this.pelicula;
    }

    /**
     * 
     * @param pelicula
     *     the value for the pelicula property
     */
    public void setPelicula(es.um.arso.pelicula.Pelicula pelicula) {
        this.pelicula = pelicula;
    }

}
