//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.11.05 at 03:34:01 PM EST 
//


package edu.stevens.cs548.clinic.service.dto;

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
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="diagnosis" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="patient" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="provider" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="DrugTreatment" type="{http://cs548.stevens.edu/clinic/dto}DrugTreatmentType"/&gt;
 *           &lt;element name="Surgery" type="{http://cs548.stevens.edu/clinic/dto}SurgeryType"/&gt;
 *           &lt;element name="Radiology" type="{http://cs548.stevens.edu/clinic/dto}RadiologyType"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "id",
    "diagnosis",
    "patient",
    "provider",
    "drugTreatment",
    "surgery",
    "radiology"
})
@XmlRootElement(name = "treatment-dto")
public class TreatmentDto {

    protected long id;
    @XmlElement(required = true)
    protected String diagnosis;
    protected long patient;
    protected long provider;
    @XmlElement(name = "DrugTreatment")
    protected DrugTreatmentType drugTreatment;
    @XmlElement(name = "Surgery")
    protected SurgeryType surgery;
    @XmlElement(name = "Radiology")
    protected RadiologyType radiology;

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
     * Gets the value of the diagnosis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the value of the diagnosis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiagnosis(String value) {
        this.diagnosis = value;
    }

    /**
     * Gets the value of the patient property.
     * 
     */
    public long getPatient() {
        return patient;
    }

    /**
     * Sets the value of the patient property.
     * 
     */
    public void setPatient(long value) {
        this.patient = value;
    }

    /**
     * Gets the value of the provider property.
     * 
     */
    public long getProvider() {
        return provider;
    }

    /**
     * Sets the value of the provider property.
     * 
     */
    public void setProvider(long value) {
        this.provider = value;
    }

    /**
     * Gets the value of the drugTreatment property.
     * 
     * @return
     *     possible object is
     *     {@link DrugTreatmentType }
     *     
     */
    public DrugTreatmentType getDrugTreatment() {
        return drugTreatment;
    }

    /**
     * Sets the value of the drugTreatment property.
     * 
     * @param value
     *     allowed object is
     *     {@link DrugTreatmentType }
     *     
     */
    public void setDrugTreatment(DrugTreatmentType value) {
        this.drugTreatment = value;
    }

    /**
     * Gets the value of the surgery property.
     * 
     * @return
     *     possible object is
     *     {@link SurgeryType }
     *     
     */
    public SurgeryType getSurgery() {
        return surgery;
    }

    /**
     * Sets the value of the surgery property.
     * 
     * @param value
     *     allowed object is
     *     {@link SurgeryType }
     *     
     */
    public void setSurgery(SurgeryType value) {
        this.surgery = value;
    }

    /**
     * Gets the value of the radiology property.
     * 
     * @return
     *     possible object is
     *     {@link RadiologyType }
     *     
     */
    public RadiologyType getRadiology() {
        return radiology;
    }

    /**
     * Sets the value of the radiology property.
     * 
     * @param value
     *     allowed object is
     *     {@link RadiologyType }
     *     
     */
    public void setRadiology(RadiologyType value) {
        this.radiology = value;
    }

}
