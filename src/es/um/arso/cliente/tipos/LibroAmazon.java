
package es.um.arso.cliente.tipos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for libroAmazon complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="libroAmazon">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descripcionLibroAmazon" type="{http://server.arso.um.es/}descripcionLibroAmazon" minOccurs="0"/>
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
@XmlType(name = "libroAmazon", propOrder = {
    "descripcionLibroAmazon"
})
public class LibroAmazon {

    protected DescripcionLibroAmazon descripcionLibroAmazon;
    @XmlAttribute(name = "ASIN")
    protected String asin;
    @XmlAttribute
    protected String title;

    /**
     * Gets the value of the descripcionLibroAmazon property.
     * 
     * @return
     *     possible object is
     *     {@link DescripcionLibroAmazon }
     *     
     */
    public DescripcionLibroAmazon getDescripcionLibroAmazon() {
        return descripcionLibroAmazon;
    }

    /**
     * Sets the value of the descripcionLibroAmazon property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescripcionLibroAmazon }
     *     
     */
    public void setDescripcionLibroAmazon(DescripcionLibroAmazon value) {
        this.descripcionLibroAmazon = value;
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
