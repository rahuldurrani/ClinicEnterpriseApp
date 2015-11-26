package edu.stevens.cs548.clinic.domain;

public interface iClinicGateway {
	
	public IPatientFactory getPatientFactory();
	
	public IPatientDAO getPatientDAO();
	
	public IProviderFactory getProviderFactory();
	
	public IProviderDAO getProviderDAO();
}
