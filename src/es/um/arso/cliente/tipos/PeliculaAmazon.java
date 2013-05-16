
package es.um.arso.cliente.tipos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for peliculaAmazon complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="peliculaAmazon">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descripcionPeliculaAmazon" type="{http://server.arso.um.es/}descripcionPeliculaAmazon" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="ASIN" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="title" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "peliculaAmazon", propOrder = {
    "descripcionPeliculaAmazon"
})
public class PeliculaAmazon {

    protected DescripcionPeliculaAmazon descripcionPeliculaAmazon;
    @XmlAttribute(name = "ASIN")
    protected String asin;
    @XmlAttribute
    protected String title;

    /**
     * Gets the value of the descripcionPeliculaAmazon property.
     * 
     * @return
     *     possible object is
     *     {@link DescripcionPeliculaAmazon }
     *     
     */
    public DescripcionPeliculaAmazon getDescripcionPeliculaAmazon() {
        return descripcionPeliculaAmazon;
    }

    /**
     * Sets the value of the descripcionPeliculaAmazon property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescripcionPeliculaAmazon }
     *     
     */
    public void setDescripcionPeliculaAmazon(DescripcionPeliculaAmazon value) {
        this.descripcionPeliculaAmazon = value;
    }

    /**
     * Gets the value of the asin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getASIN() {
        return asin;
    }

    /**
     * Sets the value of the asin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setASIN(String value) {
        this.asin = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

}
