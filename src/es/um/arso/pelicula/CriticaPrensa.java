//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.16 at 12:53:11 AM CEST 
//


package es.um.arso.pelicula;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for criticaPrensa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="criticaPrensa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sinopsis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="media" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="critico" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "criticaPrensa", propOrder = {
    "sinopsis",
    "media",
    "critico",
    "urlCritica"
})
public class CriticaPrensa {

    @XmlElement(required = true)
    protected String sinopsis;
    @XmlElement(required = true)
    protected String media;
    @XmlElement(required = true)
    protected String critico;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String urlCritica;

    /**
     * Gets the value of the sinopsis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSinopsis() {
        return sinopsis;
    }

    /**
     * Sets the value of the sinopsis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSinopsis(String value) {
        this.sinopsis = value;
    }

    /**
     * Gets the value of the media property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMedia() {
        return media;
    }

    /**
     * Sets the value of the media property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMedia(String value) {
        this.media = value;
    }

    /**
     * Gets the value of the critico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCritico() {
        return critico;
    }

    /**
     * Sets the value of the critico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCritico(String value) {
        this.critico = value;
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
