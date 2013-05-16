
package es.um.arso.cliente.tipos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="descripcionPelicula" type="{http://server.arso.um.es/}descripcionPelicula" minOccurs="0"/>
 *         &lt;element name="criticasPrensa" type="{http://server.arso.um.es/}criticasPrensa" minOccurs="0"/>
 *         &lt;element name="criticasUsuario" type="{http://server.arso.um.es/}criticasUsuario" minOccurs="0"/>
 *         &lt;element name="peliculaAmazon" type="{http://server.arso.um.es/}peliculaAmazon" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="libroAmazon" type="{http://server.arso.um.es/}libroAmazon" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="titulo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "descripcionPelicula",
    "criticasPrensa",
    "criticasUsuario",
    "peliculaAmazon",
    "libroAmazon"
})
@XmlRootElement(name = "pelicula")
public class Pelicula {

    protected DescripcionPelicula descripcionPelicula;
    protected CriticasPrensa criticasPrensa;
    protected CriticasUsuario criticasUsuario;
    @XmlElement(nillable = true)
    protected List<PeliculaAmazon> peliculaAmazon;
    @XmlElement(nillable = true)
    protected List<LibroAmazon> libroAmazon;
    @XmlAttribute
    protected BigInteger id;
    @XmlAttribute
    protected String titulo;

    /**
     * Gets the value of the descripcionPelicula property.
     * 
     * @return
     *     possible object is
     *     {@link DescripcionPelicula }
     *     
     */
    public DescripcionPelicula getDescripcionPelicula() {
        return descripcionPelicula;
    }

    /**
     * Sets the value of the descripcionPelicula property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescripcionPelicula }
     *     
     */
    public void setDescripcionPelicula(DescripcionPelicula value) {
        this.descripcionPelicula = value;
    }

    /**
     * Gets the value of the criticasPrensa property.
     * 
     * @return
     *     possible object is
     *     {@link CriticasPrensa }
     *     
     */
    public CriticasPrensa getCriticasPrensa() {
        return criticasPrensa;
    }

    /**
     * Sets the value of the criticasPrensa property.
     * 
     * @param value
     *     allowed object is
     *     {@link CriticasPrensa }
     *     
     */
    public void setCriticasPrensa(CriticasPrensa value) {
        this.criticasPrensa = value;
    }

    /**
     * Gets the value of the criticasUsuario property.
     * 
     * @return
     *     possible object is
     *     {@link CriticasUsuario }
     *     
     */
    public CriticasUsuario getCriticasUsuario() {
        return criticasUsuario;
    }

    /**
     * Sets the value of the criticasUsuario property.
     * 
     * @param value
     *     allowed object is
     *     {@link CriticasUsuario }
     *     
     */
    public void setCriticasUsuario(CriticasUsuario value) {
        this.criticasUsuario = value;
    }

    /**
     * Gets the value of the peliculaAmazon property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the peliculaAmazon property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPeliculaAmazon().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PeliculaAmazon }
     * 
     * 
     */
    public List<PeliculaAmazon> getPeliculaAmazon() {
        if (peliculaAmazon == null) {
            peliculaAmazon = new ArrayList<PeliculaAmazon>();
        }
        return this.peliculaAmazon;
    }

    /**
     * Gets the value of the libroAmazon property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the libroAmazon property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLibroAmazon().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LibroAmazon }
     * 
     * 
     */
    public List<LibroAmazon> getLibroAmazon() {
        if (libroAmazon == null) {
            libroAmazon = new ArrayList<LibroAmazon>();
        }
        return this.libroAmazon;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

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

}
