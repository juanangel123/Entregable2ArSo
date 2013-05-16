
package es.um.arso.cliente.tipos;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for criticasPrensa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="criticasPrensa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="criticaPrensa" type="{http://server.arso.um.es/}criticaPrensa" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "criticasPrensa", propOrder = {
    "criticaPrensa"
})
public class CriticasPrensa {

    @XmlElement(nillable = true)
    protected List<CriticaPrensa> criticaPrensa;

    /**
     * Gets the value of the criticaPrensa property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the criticaPrensa property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCriticaPrensa().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CriticaPrensa }
     * 
     * 
     */
    public List<CriticaPrensa> getCriticaPrensa() {
        if (criticaPrensa == null) {
            criticaPrensa = new ArrayList<CriticaPrensa>();
        }
        return this.criticaPrensa;
    }

}
