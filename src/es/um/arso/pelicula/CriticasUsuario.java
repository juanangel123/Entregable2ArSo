//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.16 at 12:53:11 AM CEST 
//


package es.um.arso.pelicula;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for criticasUsuario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="criticasUsuario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="criticaUsuario" type="{http://server.arso.um.es/}criticaUsuario" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "criticasUsuario", propOrder = {
    "criticaUsuario"
})
public class CriticasUsuario {

    protected List<CriticaUsuario> criticaUsuario;

    /**
     * Gets the value of the criticaUsuario property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the criticaUsuario property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCriticaUsuario().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CriticaUsuario }
     * 
     * 
     */
    public List<CriticaUsuario> getCriticaUsuario() {
        if (criticaUsuario == null) {
            criticaUsuario = new ArrayList<CriticaUsuario>();
        }
        return this.criticaUsuario;
    }

}
