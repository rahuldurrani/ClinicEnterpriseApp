package edu.stevens.cs548.clinic.service.dto.util;

import edu.stevens.cs548.clinic.domain.Provider;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;

public class ProviderDtoFactory {

	ObjectFactory factory;

	public ProviderDtoFactory() {
		factory = new ObjectFactory();
	}
	
	public ProviderDto createProviderDto()
	{
		return factory.createProviderDto();
	}
	
	public ProviderDto createProviderDto(Provider p)
	{
		ProviderDto dto = factory.createProviderDto();
		dto.setId(p.getId());
		dto.setProviderId(p.getProviderid());
		dto.setProviderName(p.getProvidername());
		dto.setSepcialization(p.getSpecialization());
		return dto;
	}
}
