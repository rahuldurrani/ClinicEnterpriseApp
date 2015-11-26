package edu.stevens.cs548.clinic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import static javax.persistence.CascadeType.REMOVE;
import javax.persistence.OrderBy;

/**
 * Entity implementation class for Entity: Patient
 *
 */

@NamedQueries({
	@NamedQuery(
		name="SearchPatientByPatientID",
		query="select p from Patient p where p.patientId = :pid"),
	@NamedQuery(
		name="CountPatientByPatientID",
		query="select count(p) from Patient p where p.patientId = :pid"),
	@NamedQuery(
		name = "RemoveAllPatients", 
		query = "delete from Patient p")
})


@Entity
@Table(name="Patient")

public class Patient implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue
	private long id;
	
	private long patientId;
	
	private String name;
	
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@OneToMany(cascade = REMOVE, mappedBy = "patient")
	@OrderBy	
	private List<Treatment> treatments;

	protected List<Treatment> getTreatments() {
		return treatments;
	}

	protected void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}
	
	@Transient
	private ITreatmentDAO treatmentDAO;
	
	public void setTreatmentDAO (ITreatmentDAO tdao) {
		this.treatmentDAO = tdao;
	}
	
	public long addTreatment (Treatment t) {
		// Persist treatment and set forward and backward links
		this.treatmentDAO.addTreatment(t);
		this.getTreatments().add(t);
		if (t.getPatient() != this) {
			t.setPatient(this);
		}
		return t.getId();
	}
	
	public void getTreatmentIds(List<Long> treatmentIds) {
		for (Treatment t : this.getTreatments()) {
			treatmentIds.add(t.getId());
		}
	}
	
	public long addDrugTreatment(String diagnosis, String drug, float dosage)
	{
		DrugTreatment treatment = new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDrug(drug);
		treatment.setDosage(dosage);
		this.addTreatment(treatment);
		return treatment.getId();
	}
	
	public long addSurgery(Date date, String diagnosis)
	{
		Surgery treatment = new Surgery();
		treatment.setDiagnosis(diagnosis);
		treatment.setSurgeryDate(date);
		this.addTreatment(treatment);
		return treatment.getId();
	}
	
	public long addRadiology(List<Date> dates, String diagnosis)
	{
		Radiology treatment = new Radiology();
		treatment.setDiagnosis(diagnosis);
		treatment.setRadiologyDates(dates);
		this.addTreatment(treatment);
		return treatment.getId();
	}
	
	public <T> T exportTreatment(long tid, ITreatmentExporter<T> visitor) throws TreatmentExn {
		// Export a treatment without violated Aggregate pattern
		// Check that the exported treatment is a treatment for this patient.
		Treatment t = treatmentDAO.getTreatment(tid);
		if (t.getPatient() != this) {
			throw new TreatmentExn("Inappropriate treatment access: patient = " + id + ", treatment = " + tid);
		}
		return t.export(visitor);
	}
	
	
	public void deleteTreatments(long tid) throws TreatmentExn
	{
		Treatment t = treatmentDAO.getTreatment(tid);
		if (t.getPatient() != this) {
			throw new TreatmentExn("Inappropriate treatment access: patient = " + id + ", treatment = " + tid);
		}
		treatmentDAO.deleteTreatment(t);
		
	}
	public Patient() {
		super();
		treatments= new ArrayList<Treatment>();
	}
   
}
