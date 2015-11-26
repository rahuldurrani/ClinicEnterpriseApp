
package edu.stevens.cs548.clinic.service.web.soap.patient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for RadiologyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RadiologyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Radiology-Dates" type="{http://www.w3.org/2001/XMLSchema}date" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RadiologyType", namespace = "http://cs548.stevens.edu/clinic/dto", propOrder = {
    "radiologyDates"
})
public class RadiologyType {

    @XmlElement(name = "Radiology-Dates")
    @XmlSchemaType(name = "date")
    protected List<XMLGregorianCalendar> radiologyDates;

    /**
     * Gets the value of the radiologyDates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the radiologyDates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRadiologyDates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XMLGregorianCalendar }
     * 
     * 
     */
    public List<XMLGregorianCalendar> getRadiologyDates() {
        if (radiologyDates == null) {
            radiologyDates = new ArrayList<XMLGregorianCalendar>();
        }
        return this.radiologyDates;
    }

}
