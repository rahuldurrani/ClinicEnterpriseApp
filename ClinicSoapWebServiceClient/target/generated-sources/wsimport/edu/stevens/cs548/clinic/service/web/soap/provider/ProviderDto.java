
package edu.stevens.cs548.clinic.service.web.soap.provider;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="provider-id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="provider-name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sepcialization" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="treatments" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "providerId",
    "providerName",
    "sepcialization",
    "treatments"
})
@XmlRootElement(name = "provider-dto", namespace = "http://cs548.stevens.edu/clinic/dto")
public class ProviderDto {

    protected long id;
    @XmlElement(name = "provider-id")
    protected long providerId;
    @XmlElement(name = "provider-name", required = true)
    protected String providerName;
    @XmlElement(required = true)
    protected String sepcialization;
    @XmlElement(nillable = true)
    protected List<Long> treatments;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the providerId property.
     * 
     */
    public long getProviderId() {
        return providerId;
    }

    /**
     * Sets the value of the providerId property.
     * 
     */
    public void setProviderId(long value) {
        this.providerId = value;
    }

    /**
     * Gets the value of the providerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * Sets the value of the providerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderName(String value) {
        this.providerName = value;
    }

    /**
     * Gets the value of the sepcialization property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSepcialization() {
        return sepcialization;
    }

    /**
     * Sets the value of the sepcialization property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSepcialization(String value) {
        this.sepcialization = value;
    }

    /**
     * Gets the value of the treatments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the treatments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTreatments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getTreatments() {
        if (treatments == null) {
            treatments = new ArrayList<Long>();
        }
        return this.treatments;
    }

}
