package edu.stevens.cs548.clinic.domain;

import edu.stevens.cs548.clinic.domain.Treatment;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Radiology
 *
 */
@Entity
@DiscriminatorValue("R")
public class Radiology extends Treatment implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@ElementCollection
	@Temporal(TemporalType.DATE)
	@Column(name="radiologyDates")
	private List <Date> radiologyDates;
	public List<Date> getRadiologyDates() {
		return radiologyDates;
	}
	
	public void setRadiologyDates(List<Date> radiologyDates) {
		this.radiologyDates = radiologyDates;
	}
	
	public <T> T export(ITreatmentExporter<T> visitor) {
		return visitor.exportRadiology(this.getId(), 
								   		   this.getDiagnosis(),
								   		   this.radiologyDates, this.getPatient().getPatientId(), this.getProvider().getProviderid());
	}
	public Radiology() {
		super();
	}
   
}
