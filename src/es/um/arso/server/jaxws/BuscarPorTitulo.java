
package es.um.arso.server.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "buscarPorTitulo", namespace = "http://server.arso.um.es/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "buscarPorTitulo", namespace = "http://server.arso.um.es/")
public class BuscarPorTitulo {

    @XmlElement(name = "titulo", namespace = "")
    private String titulo;

    /**
     * 
     * @return
     *     returns String
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * 
     * @param titulo
     *     the value for the titulo property
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}
