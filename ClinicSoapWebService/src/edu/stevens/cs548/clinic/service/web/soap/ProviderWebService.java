package edu.stevens.cs548.clinic.service.web.soap;

import javax.inject.Inject;
import javax.jws.WebService;

import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceLocal;

@WebService(
		endpointInterface = "edu.stevens.cs548.clinic.service.web.soap.IProviderWebService", 
		targetNamespace = "http://cs548.stevens.edu/clinic/service/web/soap", 
		serviceName = "ProviderWebService", portName = "ProviderWebPort")

public class ProviderWebService implements IProviderWebService {
	
	@Inject IProviderServiceLocal service;

	@Override
	public ProviderDto getProvider(long pid) throws ProviderServiceExn {
		return service.getProvider(pid);
	}

	@Override
	public long addProvider(ProviderDto dto) throws ProviderServiceExn {
		return service.addProvider(dto);
	}

	@Override
	public ProviderDto getProviderbyNPI(long pid) throws ProviderServiceExn {
		return service.getProviderbyNPI(pid);
	}

	@Override
	public long addTreatment(TreatmentDto dto) throws ProviderServiceExn {
		return service.addTreatment(dto);
	}

	@Override
	public String siteInfo() {
		return service.siteInfo();
	}

}
