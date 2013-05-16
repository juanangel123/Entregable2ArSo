
package es.um.arso.server.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "buscarPorTituloResponse", namespace = "http://server.arso.um.es/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buscarPorTituloResponse", namespace = "http://server.arso.um.es/")
public class BuscarPorTituloResponse {

    @XmlElement(name = "pelicula", namespace = "http://server.arso.um.es/")
    private List<es.um.arso.pelicula.Pelicula> pelicula;

    /**
     * 
     * @return
     *     returns List<Pelicula>
     */
    public List<es.um.arso.pelicula.Pelicula> getPelicula() {
        return this.pelicula;
    }

    /**
     * 
     * @param pelicula
     *     the value for the pelicula property
     */
    public void setPelicula(List<es.um.arso.pelicula.Pelicula> pelicula) {
        this.pelicula = pelicula;
    }

}
