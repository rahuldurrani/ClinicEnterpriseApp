package edu.stevens.cs548.clinic.domain;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class PatientDAO implements IPatientDAO {

	private EntityManager em;
	private TreatmentDAO treatmentDAO;
	
	public PatientDAO(EntityManager em) {
		this.em = em;
		this.treatmentDAO = new TreatmentDAO(em);
	}

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(PatientDAO.class.getCanonicalName());

	@Override
	public void addPatient(Patient patient) throws PatientExn {
		long pid = patient.getPatientId();
		Query query = em.createNamedQuery("CountPatientByPatientID").setParameter("pid", pid);
		Long numExisting = (Long) query.getSingleResult();
		if (numExisting < 1) {
			em.persist(patient);
			patient.setTreatmentDAO(this.treatmentDAO);
		} else {
			throw new PatientExn("Insertion: Patient with patient id (" + pid + ") already exists.");
		}
	}

	@Override
	public Patient getPatient(long id) throws PatientExn {
		Patient p = em.find(Patient.class, id);
		if (p==null)
		{
			throw new PatientExn("Patient not found: primary key = "+ id);
		}else{
			p.setTreatmentDAO(this.treatmentDAO);
		return p;
		}
	}

	@Override
	public Patient getPatientByPatientId(long pid) throws PatientExn {
		TypedQuery <Patient> query = em.createNamedQuery("SearchPatientByPatientID", Patient.class)
				.setParameter("pid", pid);
		List <Patient> patients = query.getResultList();
		if(patients.size()>1)
		{
			throw new PatientExn("Duplicate patient records for Patientid: "+ pid);
		}
		else if (patients.size()<1)
		{
			throw new PatientExn("No records found for patient id: "+pid);
		}
		else
		{
			Patient p= patients.get(0);
			p.setTreatmentDAO(this.treatmentDAO);
			return p;
		}
		
	}
	
	@Override
	public void deletePatients() {
		Query update = em.createNamedQuery("RemoveAllPatients");
		update.executeUpdate();
	}

	@Override
	public void deletePatient(Patient patient) {
		//em.createQuery("delete from Treatment t where t.patient.id := id")
		//.setParameter("id", patient.getId())
		//.executeUpdate();
		em.remove(patient);
		
	}

	@Override
	public void cascadeDelete() {
		em.createNativeQuery("TRUNCATE TABLE Patient CASCADE").executeUpdate();
		
	}

}
