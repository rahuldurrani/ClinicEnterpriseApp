package edu.stevens.cs548.clinic.domain;

public class ProviderFactory implements IProviderFactory {

	@Override
	public Provider createProvider(long providerid, String providerName, String specialization) {
		Provider prov = new Provider();
		prov.setProviderid(providerid);
		prov.setSpecialization(specialization);
		prov.setProvidername(providerName);
		return prov;
	}

}
