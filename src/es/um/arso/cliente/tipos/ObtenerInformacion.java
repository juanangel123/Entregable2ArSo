
package es.um.arso.cliente.tipos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerInformacion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerInformacion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificador" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerInformacion", propOrder = {
    "identificador"
})
public class ObtenerInformacion {

    protected long identificador;

    /**
     * Gets the value of the identificador property.
     * 
     */
    public long getIdentificador() {
        return identificador;
    }

    /**
     * Sets the value of the identificador property.
     * 
     */
    public void setIdentificador(long value) {
        this.identificador = value;
    }

}
