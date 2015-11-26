package edu.stevens.cs548.clinic.domain;

import edu.stevens.cs548.clinic.domain.Treatment;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Surgery
 *
 */
@Entity
@DiscriminatorValue("S")
public class Surgery extends Treatment implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Temporal(TemporalType.DATE)
	private Date surgeryDate;
	public Surgery() {
		super();
		this.setTreatmentType("S");
	}
	public Date getSurgeryDate() {
		return surgeryDate;
	}
	public void setSurgeryDate(Date surgeryDate) {
		this.surgeryDate = surgeryDate;
	}
	
	public <T> T export(ITreatmentExporter<T> visitor) {
		return visitor.exportSurgery(this.getId(), 
								   		   this.getDiagnosis(),
								   		   this.surgeryDate, this.getPatient().getPatientId(), this.getProvider().getProviderid());
	}
   
}
