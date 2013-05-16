
package es.um.arso.cliente.tipos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerInformacionResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerInformacionResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://server.arso.um.es/}pelicula" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerInformacionResponse", propOrder = {
    "pelicula"
})
public class ObtenerInformacionResponse {

    @XmlElement(namespace = "http://server.arso.um.es/")
    protected Pelicula pelicula;

    /**
     * Gets the value of the pelicula property.
     * 
     * @return
     *     possible object is
     *     {@link Pelicula }
     *     
     */
    public Pelicula getPelicula() {
        return pelicula;
    }

    /**
     * Sets the value of the pelicula property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pelicula }
     *     
     */
    public void setPelicula(Pelicula value) {
        this.pelicula = value;
    }

}
