package edu.stevens.cs548.clinic.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ClinicGateway implements iClinicGateway {

	@Override
	public IPatientFactory getPatientFactory() {
		return new PatientFactory();
	}

	private EntityManagerFactory emf;
	private EntityManager em;
	@Override
	public IPatientDAO getPatientDAO() {
		return new PatientDAO(em);
	}

	public ClinicGateway()
	{
		emf= Persistence.createEntityManagerFactory("ClnincDomain");
		em = emf.createEntityManager();
	}

	@Override
	public IProviderFactory getProviderFactory() {
		return new ProviderFactory();
	}

	@Override
	public IProviderDAO getProviderDAO() {
		return new ProviderDAO(em);
	}
}
