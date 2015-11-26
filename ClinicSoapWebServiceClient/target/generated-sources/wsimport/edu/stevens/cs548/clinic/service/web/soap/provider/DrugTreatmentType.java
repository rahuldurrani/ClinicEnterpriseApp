
package edu.stevens.cs548.clinic.service.web.soap.provider;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DrugTreatmentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DrugTreatmentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DrugName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Dosage" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DrugTreatmentType", namespace = "http://cs548.stevens.edu/clinic/dto", propOrder = {
    "drugName",
    "dosage"
})
public class DrugTreatmentType {

    @XmlElement(name = "DrugName", required = true)
    protected String drugName;
    @XmlElement(name = "Dosage")
    protected double dosage;

    /**
     * Gets the value of the drugName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrugName() {
        return drugName;
    }

    /**
     * Sets the value of the drugName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrugName(String value) {
        this.drugName = value;
    }

    /**
     * Gets the value of the dosage property.
     * 
     */
    public double getDosage() {
        return dosage;
    }

    /**
     * Sets the value of the dosage property.
     * 
     */
    public void setDosage(double value) {
        this.dosage = value;
    }

}
