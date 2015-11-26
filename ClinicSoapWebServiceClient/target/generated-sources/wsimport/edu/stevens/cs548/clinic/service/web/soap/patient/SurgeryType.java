
package edu.stevens.cs548.clinic.service.web.soap.patient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SurgeryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SurgeryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Surgery-date" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SurgeryType", namespace = "http://cs548.stevens.edu/clinic/dto", propOrder = {
    "surgeryDate"
})
public class SurgeryType {

    @XmlElement(name = "Surgery-date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar surgeryDate;

    /**
     * Gets the value of the surgeryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSurgeryDate() {
        return surgeryDate;
    }

    /**
     * Sets the value of the surgeryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSurgeryDate(XMLGregorianCalendar value) {
        this.surgeryDate = value;
    }

}
