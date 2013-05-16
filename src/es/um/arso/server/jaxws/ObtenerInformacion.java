
package es.um.arso.server.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "obtenerInformacion", namespace = "http://server.arso.um.es/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerInformacion", namespace = "http://server.arso.um.es/")
public class ObtenerInformacion {

    @XmlElement(name = "identificador", namespace = "")
    private long identificador;

    /**
     * 
     * @return
     *     returns long
     */
    public long getIdentificador() {
        return this.identificador;
    }

    /**
     * 
     * @param identificador
     *     the value for the identificador property
     */
    public void setIdentificador(long identificador) {
        this.identificador = identificador;
    }

}
