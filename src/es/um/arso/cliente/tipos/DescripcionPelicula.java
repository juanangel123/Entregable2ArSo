
package es.um.arso.cliente.tipos;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for descripcionPelicula complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="descripcionPelicula">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="titulo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="director" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="reparto" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="guion" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="musica" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="fotografia" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="productora" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="genero" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="premios" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sinopsis" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imagen" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="puntuacion" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="votos" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="tituloOriginal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ano" type="{http://www.w3.org/2001/XMLSchema}gYear"/>
 *         &lt;element name="pais" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="estreno" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="duracion" type="{http://www.w3.org/2001/XMLSchema}positiveInteger"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "descripcionPelicula", propOrder = {
    "titulo",
    "director",
    "reparto",
    "guion",
    "musica",
    "fotografia",
    "productora",
    "genero",
    "premios",
    "sinopsis",
    "imagen",
    "puntuacion",
    "votos",
    "tituloOriginal",
    "ano",
    "pais",
    "estreno",
    "duracion"
})
public class DescripcionPelicula {

    @XmlElement(required = true)
    protected String titulo;
    @XmlElement(required = true)
    protected List<String> director;
    @XmlElement(required = true)
    protected List<String> reparto;
    @XmlElement(required = true)
    protected List<String> guion;
    @XmlElement(required = true)
    protected List<String> musica;
    @XmlElement(nillable = true)
    protected List<String> fotografia;
    @XmlElement(required = true)
    protected List<String> productora;
    @XmlElement(required = true)
    protected List<String> genero;
    @XmlElement(required = true)
    protected String premios;
    @XmlElement(required = true)
    protected String sinopsis;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String imagen;
    protected double puntuacion;
    @XmlElement(required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger votos;
    @XmlElement(required = true)
    protected String tituloOriginal;
    @XmlElement(required = true)
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar ano;
    @XmlElement(required = true)
    protected String pais;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar estreno;
    @XmlElement(required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger duracion;

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
     * Gets the value of the director property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the director property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDirector().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDirector() {
        if (director == null) {
            director = new ArrayList<String>();
        }
        return this.director;
    }

    /**
     * Gets the value of the reparto property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reparto property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReparto().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getReparto() {
        if (reparto == null) {
            reparto = new ArrayList<String>();
        }
        return this.reparto;
    }

    /**
     * Gets the value of the guion property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the guion property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGuion().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGuion() {
        if (guion == null) {
            guion = new ArrayList<String>();
        }
        return this.guion;
    }

    /**
     * Gets the value of the musica property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the musica property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMusica().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMusica() {
        if (musica == null) {
            musica = new ArrayList<String>();
        }
        return this.musica;
    }

    /**
     * Gets the value of the fotografia property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fotografia property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFotografia().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFotografia() {
        if (fotografia == null) {
            fotografia = new ArrayList<String>();
        }
        return this.fotografia;
    }

    /**
     * Gets the value of the productora property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productora property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductora().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getProductora() {
        if (productora == null) {
            productora = new ArrayList<String>();
        }
        return this.productora;
    }

    /**
     * Gets the value of the genero property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the genero property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGenero().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getGenero() {
        if (genero == null) {
            genero = new ArrayList<String>();
        }
        return this.genero;
    }

    /**
     * Gets the value of the premios property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPremios() {
        return premios;
    }

    /**
     * Sets the value of the premios property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPremios(String value) {
        this.premios = value;
    }

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
     * Gets the value of the imagen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Sets the value of the imagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

    /**
     * Gets the value of the puntuacion property.
     * 
     */
    public double getPuntuacion() {
        return puntuacion;
    }

    /**
     * Sets the value of the puntuacion property.
     * 
     */
    public void setPuntuacion(double value) {
        this.puntuacion = value;
    }

    /**
     * Gets the value of the votos property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getVotos() {
        return votos;
    }

    /**
     * Sets the value of the votos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setVotos(BigInteger value) {
        this.votos = value;
    }

    /**
     * Gets the value of the tituloOriginal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTituloOriginal() {
        return tituloOriginal;
    }

    /**
     * Sets the value of the tituloOriginal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTituloOriginal(String value) {
        this.tituloOriginal = value;
    }

    /**
     * Gets the value of the ano property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAno() {
        return ano;
    }

    /**
     * Sets the value of the ano property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAno(XMLGregorianCalendar value) {
        this.ano = value;
    }

    /**
     * Gets the value of the pais property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPais() {
        return pais;
    }

    /**
     * Sets the value of the pais property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPais(String value) {
        this.pais = value;
    }

    /**
     * Gets the value of the estreno property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEstreno() {
        return estreno;
    }

    /**
     * Sets the value of the estreno property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEstreno(XMLGregorianCalendar value) {
        this.estreno = value;
    }

    /**
     * Gets the value of the duracion property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDuracion() {
        return duracion;
    }

    /**
     * Sets the value of the duracion property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDuracion(BigInteger value) {
        this.duracion = value;
    }

}
