package edu.stevens.cs548.clinic.service.ejb;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IPatientFactory;
import edu.stevens.cs548.clinic.domain.ITreatmentDAO.TreatmentExn;
import edu.stevens.cs548.clinic.domain.ITreatmentExporter;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.PatientFactory;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;

/**
 * Session Bean implementation class PatientService
 */
@Stateless(name="PatientServiceBean")
public class PatientService implements IPatientServiceLocal,
		IPatientServiceRemote {
	
	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(PatientService.class.getCanonicalName());

	private IPatientFactory patientFactory;
	
	private PatientDtoFactory patientDtoFactory;

	private IPatientDAO patientDAO;
	/**
	 * Default constructor.
	 */
	public PatientService() {
		patientFactory = new PatientFactory();
		patientDtoFactory = new PatientDtoFactory();
	}
	
	@Inject @ClinicDomain
	private EntityManager em;
	
	@PostConstruct
	public void initialize()
	{
		patientDAO = new PatientDAO(em);
	}

	/**
	 * @see IPatientService#addPatient(String, Date, long)
	 */
	@Override
	public long addPatient(PatientDto dto) throws PatientServiceExn {
		// Use factory to create patient entity, and persist with DAO
		try {
			Patient patient = patientFactory.createPatient(dto.getPatientId(), dto.getName(), dto.getDob(), dto.getAge());
			patientDAO.addPatient(patient);
			return patient.getId();
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}

	/**
	 * @see IPatientService#getPatient(long)
	 */
	@Override
	public PatientDto getPatient(long id) throws PatientServiceExn {
		PatientDto patientdto;
		try {
			Patient patient = patientDAO.getPatient(id);
			patientdto = patientDtoFactory.createPatientDto(patient);
			patientdto.setAge(computeAge(patientdto.getDob()));
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
		return patientdto;
	}

	/**
	 * @see IPatientService#getPatientByPatId(long)
	 */
	@Override
	public PatientDto getPatientByPatId(long pid) throws PatientServiceExn {
		PatientDto patientdto;
		try {
			Patient patient = patientDAO.getPatientByPatientId(pid);
			patientdto = patientDtoFactory.createPatientDto(patient);
			patientdto.setAge(computeAge(patientdto.getDob()));
		} catch (PatientExn e) {
			throw new PatientServiceExn(e.toString());
		}
		
		return patientdto;
	}

	public class TreatmentExporter implements ITreatmentExporter<TreatmentDto> {
		
		private ObjectFactory factory = new ObjectFactory();
		
		@Override
		public TreatmentDto exportDrugTreatment(long tid, String diagnosis, String drug,
				float dosage, long patientid, long providerid) {
			TreatmentDto dto = factory.createTreatmentDto();
			dto.setDiagnosis(diagnosis);
			dto.setId(tid);
    		DrugTreatmentType drugInfo = factory.createDrugTreatmentType();
			drugInfo.setDosage(dosage);
			drugInfo.setDrugName(drug);
			dto.setDrugTreatment(drugInfo);
			dto.setPatient(patientid);
			dto.setProvider(providerid);
			return dto;
		}

		@Override
		public TreatmentDto exportRadiology(long tid, String diagnosis, List<Date> dates, long patientid, long providerid) {
			TreatmentDto dto = factory.createTreatmentDto();
			dto.setDiagnosis(diagnosis);
			dto.setId(tid);
			RadiologyType radiologyinfo = factory.createRadiologyType();
			radiologyinfo.getRadiologyDates().addAll(dates);
			dto.setRadiology(radiologyinfo);
			dto.setPatient(patientid);
			dto.setProvider(providerid);
			return dto;
		}

		@Override
		public TreatmentDto exportSurgery(long tid, String diagnosis, Date date, long patientid, long providerid) {
			TreatmentDto dto = factory.createTreatmentDto();
			dto.setDiagnosis(diagnosis);
			dto.setId(tid);
			SurgeryType surgeryinfo = new SurgeryType();
			surgeryinfo.setSurgeryDate(date);
			dto.setDiagnosis(diagnosis);
			dto.setSurgery(surgeryinfo);
			dto.setPatient(patientid);
			dto.setProvider(providerid);
			return dto;
		}
		
	}
	
	@Override
	public TreatmentDto getTreatment(long id, long tid)
			throws PatientNotFoundExn, TreatmentNotFoundExn, PatientServiceExn {
		// Export treatment DTO from patient aggregate
		try {
			Patient patient = patientDAO.getPatient(id);
			TreatmentExporter visitor = new TreatmentExporter();
			return patient.exportTreatment(tid, visitor);
		} catch (PatientExn e) {
			throw new PatientNotFoundExn(e.toString());
		} catch (TreatmentExn e) {
			throw new PatientServiceExn(e.toString());
		}
	}

	@Resource(name="SiteInfo")
	private String siteInformation;
	

	@Override
	public String siteInfo() {
		return siteInformation;
	}
	
	public static int computeAge(Date dateOfBirth) {
		Calendar dob = Calendar.getInstance();  
		dob.setTime(dateOfBirth);  
		Calendar today = Calendar.getInstance();  
		int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);  
		if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
		  age--;  
		} else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
		    && today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
		  age--;  
		}
		return age;
	}

}
