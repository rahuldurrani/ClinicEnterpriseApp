package edu.stevens.cs548.clinic.service.web.rest.resources;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import javax.enterprise.context.RequestScoped;

import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;
import edu.stevens.cs548.clinic.service.ejb.IProviderService.ProviderServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IProviderServiceLocal;
import edu.stevens.cs548.clinic.service.representations.ProviderRepresentation;
import edu.stevens.cs548.clinic.service.representations.Representation;
import edu.stevens.cs548.clinic.service.representations.TreatmentRepresentation;
import edu.stevens.cs548.clinic.service.web.rest.data.dap.LinkType;

@Path("/provider")
@RequestScoped
public class ProviderResource {
	public class ResourceNotFoundException extends WebApplicationException {
		private static final long serialVersionUID = 1L;

		public ResourceNotFoundException(String message) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity(message).type(MediaType.TEXT_PLAIN).build());
	     }
	}
	@Context
	private UriInfo context;

	/**
	 * Default constructor.
	 */

	private ProviderDtoFactory providerDTOFactory;
	private TreatmentDtoFactory treatmentDTOFactory;

	public ProviderResource() {
		// TODO Auto-generated constructor stub
		providerDTOFactory = new ProviderDtoFactory();
		treatmentDTOFactory = new TreatmentDtoFactory();
	}

	@Inject
	private IProviderServiceLocal providerService;

	/**
	 * Retrieves representation of an instance of ProviderResource
	 * 
	 * @return an instance of String
	 */
	
	@POST
	@Path("{id}/treatments")
    @Consumes("application/xml")
    public Response addTreatment(TreatmentRepresentation treatrep,@PathParam("id")String provid, @HeaderParam("X-­Patient") String patientURI){
    	try {
    		long pid = Long.parseLong(provid);
    		//treatrep.setPatient(link);
    		treatrep.setProvider(ProviderRepresentation.getProviderLink(pid, context));
    		TreatmentDto dto = treatmentDTOFactory.createTreatmentDTO();
    		dto.setDiagnosis(treatrep.getDiagnosis());
    		dto.setProvider(pid);
    		dto.setPatient(Representation.getId(treatrep.getLinkPatient()));
    		if(treatrep.getDrugTreatment()!=null)
    		{
    			DrugTreatmentType drugdto = treatmentDTOFactory.createDrugTreatmentDto();
    			drugdto.setDosage(treatrep.getDrugTreatment().getDosage());
    			drugdto.setDrugName(treatrep.getDrugTreatment().getDrugName());
    			dto.setDrugTreatment(drugdto);
    		}else if(treatrep.getRadiology()!= null)
    		{
    			RadiologyType radiodto = treatmentDTOFactory.createRadiologyDto();
    			radiodto.getRadiologyDates().addAll(treatrep.getRadiology().getRadiologyDates());
    			dto.setRadiology(radiodto);
    		}else if(treatrep.getSurgery()!=null)
    		{
    			SurgeryType surgdto = treatmentDTOFactory.createSurgeryTreatmentDto();
    			surgdto.setSurgeryDate(treatrep.getSurgery().getSurgeryDate());
    			dto.setSurgery(surgdto);
    		}
			long id = providerService.addTreatment(dto);
			UriBuilder ub = context.getAbsolutePathBuilder().path("{id}");
    		URI url = ub.build(Long.toString(id));
    		return Response.created(url).build();
		} catch (ProviderServiceExn e) {
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unable to add treatment")
					.type(MediaType.TEXT_PLAIN).build());
		}
    }

	@GET
	@Path("site")
	@Produces("text/plain")
	public String getSiteInformation() {
		return providerService.siteInfo();
	}

	@GET
	@Path("{id}")
	@Produces("application/xml")
	public ProviderRepresentation getProvider(@PathParam("id") String id) {
		try {
			long key = Long.parseLong(id);
			ProviderDto providerdto = providerService.getProvider(key);
			ProviderRepresentation providerrep = new ProviderRepresentation(providerdto, context);
			return providerrep;
		} catch (ProviderServiceExn e) {
			throw new ResourceNotFoundException("Provider resource not found for given providerid.");
		}
	}

	@GET
	@Path("byNPI")
	@Produces("application/xml")
	public ProviderRepresentation getProviderbyProviderid(@QueryParam("id") String pid) {
		try {
			long providerid = Long.parseLong(pid);
			ProviderDto providerdto = providerService.getProviderbyNPI(providerid);
			ProviderRepresentation providerrep = new ProviderRepresentation(providerdto, context);
			return providerrep;
		} catch (ProviderServiceExn e) {
			throw new ResourceNotFoundException("Provider resource not found for given NPI.");
		}
	}

	@POST
	@Consumes("application/xml")
	public Response addProvider(ProviderRepresentation providerrep) {
		try {
			ProviderDto dto = providerDTOFactory.createProviderDto();
			dto.setProviderId(providerrep.getProviderId());
			dto.setProviderName(providerrep.getProviderName());
			dto.setSepcialization(providerrep.getSpecilization());
			long id = providerService.addProvider(dto);
			UriBuilder ub = context.getAbsolutePathBuilder().path("{id}");
			URI url = ub.build(Long.toString(id));
			return Response.created(url).build();

		} catch (ProviderServiceExn e) {
			throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unable to add provider")
					.type(MediaType.TEXT_PLAIN).build());
		}
	}
	
}