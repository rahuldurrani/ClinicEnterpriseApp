package edu.stevens.cs548.clinic.service.ejb;

import edu.stevens.cs548.clinic.domain.IPatientDAO;
import edu.stevens.cs548.clinic.domain.IPatientDAO.PatientExn;
import edu.stevens.cs548.clinic.domain.IProviderDAO;
import edu.stevens.cs548.clinic.domain.IProviderDAO.ProviderExn;
import edu.stevens.cs548.clinic.domain.IProviderFactory;
import edu.stevens.cs548.clinic.domain.Patient;
import edu.stevens.cs548.clinic.domain.PatientDAO;
import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.domain.ProviderDAO;
import edu.stevens.cs548.clinic.domain.ProviderFactory;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.test.InitBean;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * Session Bean implementation class ProviderService
 */
@Stateless(name="ProviderServiceBean")
@Local(IProviderServiceLocal.class)
@Remote(IProviderServiceRemote.class)
public class ProviderService implements IProviderServiceLocal, IProviderServiceRemote {

	private IProviderFactory providerFactory;
	
	private ProviderDtoFactory providerDtoFactory;
	
	private IProviderDAO providerdao;
	
	private IPatientDAO patientdao;
	
	private static Logger logger = Logger.getLogger(InitBean.class.getCanonicalName());
    /**
     * Default constructor. 
     */
    public ProviderService() {
    	providerFactory= new ProviderFactory();
    	providerDtoFactory = new ProviderDtoFactory();
    }

    @Inject @ClinicDomain private EntityManager em;
    
    @PostConstruct
    public void initialize(){
    	providerdao = new ProviderDAO(em);
    	patientdao = new PatientDAO(em);
    }
	/**
     * @throws ProviderServiceExn 
	 * @see IProviderService#addProvider(ProviderDto)
     */
    public long addProvider(ProviderDto dto) throws ProviderServiceExn {
    	Provider provider = providerFactory.createProvider(dto.getProviderId(), dto.getProviderName(), dto.getSepcialization());
		try {
			providerdao.addProvider(provider);
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
		return provider.getId();
    }

	/**
     * @see IProviderService#addTreatment(TreatmentDto)
     */
    public long addTreatment(TreatmentDto dto) {
    	long tid = 0;
    	try {
			Provider provider = providerdao.getProvider(dto.getProvider());
			logger.info("Found provider "+provider.getProvidername());
			Patient patient = patientdao.getPatient(dto.getPatient());
			logger.info("Found patient "+patient.getName());
			if(dto.getDrugTreatment()!=null)
			{
				logger.info("Adding drugtreatment");
				tid = provider.addDrugTreatment(dto.getDiagnosis(), dto.getDrugTreatment().getDrugName(), (float)dto.getDrugTreatment().getDosage(), patient);
			}
			if(dto.getRadiology()!=null)
			{
				logger.info("Adding Radiology");
				 tid = provider.addRadiology(dto.getDiagnosis(), dto.getRadiology().getRadiologyDates(), patient);
			}
			if(dto.getSurgery()!=null)
			{
				logger.info("Adding Surgery");
				 tid = provider.addSurgery(dto.getDiagnosis(), dto.getSurgery().getSurgeryDate(), patient);
			}
		} catch (ProviderExn e) {
			e.printStackTrace();
		} catch (PatientExn e) {
			e.printStackTrace();
		}
    	return tid;
    }

	/**
     * @throws ProviderServiceExn 
	 * @see IProviderService#getProviderbyNPI(long)
     */
    public ProviderDto getProviderbyNPI(long pid) throws ProviderServiceExn {
    	ProviderDto providerdto;
		Provider provider;
		try {
			provider = providerdao.getProviderbyNPI(pid);
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
		providerdto = providerDtoFactory.createProviderDto(provider);
		return providerdto;
    }

	/**
     * @throws ProviderServiceExn 
	 * @see IProviderService#getProvider(long)
     */
    public ProviderDto getProvider(long pid) throws ProviderServiceExn {
    	ProviderDto providerdto;
		Provider provider;
		try {
			provider = providerdao.getProvider(pid);
		} catch (ProviderExn e) {
			throw new ProviderServiceExn(e.toString());
		}
		providerdto = providerDtoFactory.createProviderDto(provider);
		return providerdto;
    }
    @Resource(name="SiteInfo")
	private String siteInformation;
    
	@Override
	public String siteInfo() {
		return siteInformation;
	}

}
