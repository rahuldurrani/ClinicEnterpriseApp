package edu.stevens.cs548.clinic.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import edu.stevens.cs548.clinic.domain.DrugTreatment;
import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IProviderDAO;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.PatientFactory;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.ProviderDAO;
import edu.stevens.cs548.clinic.domain.ProviderFactory;
import edu.stevens.cs548.clinic.domain.Radiology;
import edu.stevens.cs548.clinic.domain.Surgery;
import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;
import edu.stevens.cs548.clinic.service.ejb.ClinicDomain;
import edu.stevens.cs548.clinic.service.ejb.IPatientServiceLocal;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceLocal;

/**
 * Session Bean implementation class TestBean
 */
@Singleton
@LocalBean
@Startup
public class InitBean {

	private static Logger logger = Logger.getLogger(InitBean.class.getCanonicalName());

	/**
	 * Default constructor.
	 */
	public InitBean() {
	}
	
	@Inject @ClinicDomain
	EntityManager em;
	@Inject IPatientServiceLocal patientService;
	@Inject IProviderServiceLocal providerService;
	
	@PostConstruct
	private void init() {
		/*
		 * Put your testing logic here. Use the logger to display testing output in the server logs.
		 */
		logger.info("Rahul Durrani ");
		try {

			Calendar calendar = Calendar.getInstance();
			calendar.set(1984, 9, 4);
			long tid,patid,provid;

			IProviderDAO providerDAO = new ProviderDAO(em);
			IPatientDAO patientDAO = new PatientDAO(em);
			PatientFactory patientFactory = new PatientFactory();
			ProviderFactory providerFactory = new ProviderFactory();
			PatientDtoFactory patientdtofactory = new PatientDtoFactory();
			ProviderDtoFactory providerdtofactory = new ProviderDtoFactory();
			TreatmentDtoFactory treatmentdtofactory = new TreatmentDtoFactory();
			DrugTreatment dt = new DrugTreatment();
			Radiology rt = new Radiology();
			Surgery st = new Surgery();
			
			patientDAO.cascadeDelete();
			providerDAO.cascadeDelete();
			
			Patient john = patientFactory.createPatient(12345678L, "John Doe", calendar.getTime(),31);
			PatientDto johndto = patientdtofactory.createPatientDto(john);
			patid = patientService.addPatient(johndto);
			johndto = patientService.getPatient(patid);
			logger.info("Added Patient "+johndto.getName()+"with dbid "+patid);
			
			Provider hgh = providerFactory.createProvider(2345677L, "Hoboken General Hospital", "Surgery");
			ProviderDto hghdto = providerdtofactory.createProviderDto(hgh);
			provid = providerService.addProvider(hghdto);
			hghdto = providerService.getProvider(provid);
			logger.info("Added Provider "+hghdto.getProviderName()+" with dbid "+provid);
			
			dt.setDiagnosis("Cold");
			dt.setDosage(3.2F);
			dt.setDrug("Crocin");
			TreatmentDto tdto = treatmentdtofactory.createTreatmentsDto(dt);
			tdto.setPatient(patid);
			tdto.setProvider(provid);
			tid = providerService.addTreatment(tdto);
			logger.info("Added treatment for provider "+hghdto.getProviderName()+"and patient "
			+johndto.getName()+" with treatmentid "+tid);
			
			st.setDiagnosis("Kidney Stones");
			st.setSurgeryDate(new Date());
			tdto = treatmentdtofactory.createTreatmentsDto(st);
			tdto.setPatient(patid);
			tdto.setProvider(provid);
			tid = providerService.addTreatment(tdto);
			logger.info("Added treatment for provider "+hghdto.getProviderName()+"and patient "
					+johndto.getName()+" with treatmentid "+tid);
			
			Patient jane = patientFactory.createPatient(12345679L, "Jane Doe", calendar.getTime(),31);
			PatientDto janedto = patientdtofactory.createPatientDto(jane);
			patid = patientService.addPatient(janedto);
			janedto= patientService.getPatientByPatId(12345679L);
			logger.info("Added Patient "+janedto.getName()+" with dbid "+patid);
			
			Provider hch = providerFactory.createProvider(2345678L, "Hoboken Cancer Hospital", "CancerTreatment");
			ProviderDto hchdto = providerdtofactory.createProviderDto(hch);
			provid = providerService.addProvider(hchdto);
			hchdto = providerService.getProviderbyNPI(2345678L);
			logger.info("Added Provider "+hchdto.getProviderName()+" with dbid "+provid);
			
			List <Date> radiologyDates = new ArrayList<Date>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date1 = dateFormat.parse("17/11/2015");
			Date date2 = dateFormat.parse("17/12/2015");
			radiologyDates.add(date1);
			radiologyDates.add(date2);
			rt.setDiagnosis("Cancer");
			rt.setRadiologyDates(radiologyDates);
			tdto = treatmentdtofactory.createTreatmentsDto(rt);
			tdto.setPatient(patid);
			tdto.setProvider(provid);
			tid = providerService.addTreatment(tdto);
			logger.info("Added treatment for provider "+hchdto.getProviderName()+"and patient "
					+janedto.getName()+" with treatmentid "+tid);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
			
	}

}
