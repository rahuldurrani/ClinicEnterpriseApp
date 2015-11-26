package edu.stevens.cs548.clinic.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;

import static javax.persistence.CascadeType.REMOVE;

/**
 * Entity implementation class for Entity: Provider
 *
 */

@NamedQueries({
	@NamedQuery(
		name="SearchProviderByProviderID",
		query="select p from Provider p where p.providerid = :pid"),
	@NamedQuery(
		name="CountProviderByProviderID",
		query="select count(p) from Provider p where p.providerid = :pid"),
	@NamedQuery(
		name = "RemoveAllProviders", 
		query = "delete from Provider p")
})
@Entity
@Table(name="Provider")
public class Provider implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long id;
	private String providername;
	private long providerid;
	private String specialization;
	@OneToMany(cascade = REMOVE, mappedBy="provider")
	@OrderBy
	private List<Treatment> treatments;
	
	@Transient
	private ITreatmentDAO treatmentDAO;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProvidername() {
		return providername;
	}

	public void setProvidername(String providername) {
		this.providername = providername;
	}

	public long getProviderid() {
		return providerid;
	}

	public void setProviderid(long providerid) {
		this.providerid = providerid;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public List<Treatment> getTreatments() {
		return treatments;
	}

	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}

	public void setTreatmentDAO (ITreatmentDAO tdao) {
		this.treatmentDAO = tdao;
	}
	public <T> T exportTreatment(long tid, ITreatmentExporter<T> visitor) throws TreatmentExn {
		// Export a treatment without violated Aggregate pattern
		// Check that the exported treatment is a treatment for this patient.
		Treatment t = treatmentDAO.getTreatment(tid);
		if (t.getProvider() != this) {
			throw new TreatmentExn("Inappropriate treatment access: provider = " + id + ", treatment = " + tid);
		}
		return t.export(visitor);
	}
	
	public <T> void visitTreatments(ITreatmentExporter<T> visitor)
	{
		for(Treatment t: this.getTreatments())
		{
			t.export(visitor);
		}
	}
	
	public long addTreatment(Treatment t)
	{
		this.getTreatments().add(t);
		if(t.getProvider()!= this)
		{
			t.setProvider(this);
		}
		
		return t.getId();
	}
	public long addDrugTreatment(String diagnosis, String drug, float dosage,Patient patient)
	{
		DrugTreatment treatment = new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDrug(drug);
		treatment.setDosage(dosage);
		treatment.setPatient(patient);
		this.addTreatment(treatment);
		return treatment.getId();
	}
	
	public long addSurgery(String diagnosis, Date surgeryDate,Patient patient)
	{
		Surgery treatment = new Surgery();
		treatment.setDiagnosis(diagnosis);
		treatment.setSurgeryDate(surgeryDate);
		treatment.setPatient(patient);
		this.addTreatment(treatment);
		return treatment.getId();
	}
	public long addRadiology(String diagnosis, List<Date> radiologyDates,Patient patient)
	{
		Radiology treatment = new Radiology();
		treatment.setDiagnosis(diagnosis);
		treatment.setRadiologyDates(radiologyDates);
		treatment.setPatient(patient);
		this.addTreatment(treatment);
		return treatment.getId();
	}
	
	public Provider() {
		super();
	}
   
}
