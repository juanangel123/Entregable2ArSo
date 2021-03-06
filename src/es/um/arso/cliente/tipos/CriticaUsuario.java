
package es.um.arso.cliente.tipos;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for criticaUsuario complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="criticaUsuario">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="puntuacion" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="urlCritica" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "criticaUsuario", propOrder = {
    "titulo",
    "usuario",
    "puntuacion",
    "urlCritica"
})
public class CriticaUsuario {

    @XmlElement(required = true)
    protected String titulo;
    @XmlElement(required = true)
    protected String usuario;
    @XmlElement(required = true)
    protected BigInteger puntuacion;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String urlCritica;

    /**
     * Gets the value of the titulo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Sets the value of the titulo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitulo(String value) {
        this.titulo = value;
    }

    /**
     * Gets the value of the usuario property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Sets the value of the usuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Gets the value of the puntuacion property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPuntuacion() {
        return puntuacion;
    }

    /**
     * Sets the value of the puntuacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPuntuacion(BigInteger value) {
        this.puntuacion = value;
    }

    /**
     * Gets the value of the urlCritica property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlCritica() {
        return urlCritica;
    }

    /**
     * Sets the value of the urlCritica property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlCritica(String value) {
        this.urlCritica = value;
    }

}
