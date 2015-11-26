package edu.stevens.cs548.clinic.domain;

public interface IProviderFactory {

	public Provider createProvider(long providerid, String providerName, String specialization);
}	
