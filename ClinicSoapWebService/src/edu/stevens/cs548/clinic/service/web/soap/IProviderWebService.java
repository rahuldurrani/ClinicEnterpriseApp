package edu.stevens.cs548.clinic.service.web.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;

@WebService(
		name="IProviderWebPort",
		targetNamespace="http://cs548.stevens.edu/clinic/service/web/soap")

@SOAPBinding(
		style=SOAPBinding.Style.DOCUMENT,
		use=SOAPBinding.Use.LITERAL,
		parameterStyle=SOAPBinding.ParameterStyle.WRAPPED)


public interface IProviderWebService {
	
	@WebMethod
	@WebResult(name="provider-dto",
	   targetNamespace="http://cs548.stevens.edu/clinic/dto")
	public ProviderDto getProvider(long pid) throws ProviderServiceExn;
	
	@WebMethod
	public long addProvider(@WebParam(name="provider-dto",
	          targetNamespace="http://cs548.stevens.edu/clinic/dto")ProviderDto dto
			) throws ProviderServiceExn;

	@WebMethod
	@WebResult(name="provider-dto",
	   targetNamespace="http://cs548.stevens.edu/clinic/dto")
	public ProviderDto getProviderbyNPI(long pid) throws ProviderServiceExn;

	@WebMethod(operationName="provideraddTreatment")
	public long addTreatment(@WebParam(name="treatment-dto",
	           targetNamespace="http://cs548.stevens.edu/clinic/dto") TreatmentDto dto) throws ProviderServiceExn;
	
	@WebMethod
	public String siteInfo();

}
