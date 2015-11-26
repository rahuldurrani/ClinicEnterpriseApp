package edu.stevens.cs548.clinic.domain;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class ProviderDAO implements IProviderDAO {
	
	private EntityManager em;
	private TreatmentDAO treatmentDAO;

	@SuppressWarnings("unused")
	private Logger logger = Logger.getLogger(PatientDAO.class.getCanonicalName());
	
	@Override
	public void addProvider(Provider prov) throws ProviderExn {
		long provid = prov.getProviderid();
		Query query = em.createNamedQuery("CountProviderByProviderID")
				.setParameter("pid", provid);
		Long numExisting = (Long) query.getSingleResult();
		if (numExisting < 1) {
			em.persist(prov);
			prov.setTreatmentDAO(this.treatmentDAO);
		} else {
			throw new ProviderExn("Insertion: Provider with provider id (" + provid + ") already exists.");
		}

	}

	@Override
	public Provider getProvider(long id) throws ProviderExn {
		Provider p = em.find(Provider.class, id);
		if(p==null)
		{
			throw new ProviderExn("Provider not found: primary key= "+id);
		}
		else
		{
			p.setTreatmentDAO(this.treatmentDAO);
			return p;
		}
	}

	@Override
	public Provider getProviderbyNPI(long id) throws ProviderExn {
		TypedQuery <Provider> query = em.createNamedQuery("SearchProviderByProviderID",Provider.class)
				.setParameter("pid", id);
		List <Provider> providers = query.getResultList();
		if(providers.size()>1)
		{
			throw new ProviderExn("Duplicate provider records found for provider id: "+id);
		}else if(providers.size()<1)
		{
			throw new ProviderExn("No records found for provider id:" + id);
		}
		else
		{
		 Provider p = providers.get(0);
		 p.setTreatmentDAO(this.treatmentDAO);
		 return p;
		}
	}

	@Override
	public void deleteProvider(Provider prov) throws ProviderExn {
		em.createQuery("delete from Treatment t where t.prov.id := id")
		.setParameter("id", prov.getId())
		.executeUpdate();
		em.remove(prov);
	}

	public ProviderDAO(EntityManager em) {
		this.em = em;
	}

	@Override
	public void deleteProviders() throws ProviderExn {
		Query update = em.createNamedQuery("RemoveAllProviders");
		update.executeUpdate();
		
	}

	@Override
	public void cascadeDelete() throws ProviderExn {
		em.createNativeQuery("TRUNCATE TABLE Provider CASCADE").executeUpdate();
		
	}

}
