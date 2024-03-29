package edu.stevens.cs548.clinic.service.web.rest.resources;

import java.net.URI;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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

import edu.stevens.cs548.clinic.service.dto.PatientDto;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.PatientDtoFactory;
import edu.stevens.cs548.clinic.service.ejb.IPatientService.PatientServiceExn;
import edu.stevens.cs548.clinic.service.ejb.IPatientServiceLocal;
import edu.stevens.cs548.clinic.service.representations.PatientRepresentation;
import edu.stevens.cs548.clinic.service.representations.TreatmentRepresentation;

@Path("/patient")
@RequestScoped
public class PatientResource {
	final static Logger logger = Logger.getLogger(PatientResource.class.getCanonicalName());
	public class ResourceNotFoundException extends WebApplicationException {
		private static final long serialVersionUID = 1L;

		public ResourceNotFoundException(String message) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity(message).type(MediaType.TEXT_PLAIN).build());
	     }
	}
	/*
	 * TODO inject using HK2 (not CDI)
	 */
	@Context
    private UriInfo uriInfo;
    
    private PatientDtoFactory patientDtoFactory;

    /**
     * Default constructor. 
     */
    public PatientResource() {
		/*
		 * TODO finish this
		 */
    	patientDtoFactory= new PatientDtoFactory();
    }
    
	/*
	 * TODO inject using CDI
	 */
    @Inject
    private IPatientServiceLocal patientService;
    
    @GET
    @Path("site")
    @Produces("text/plain")
    public String getSiteInfo() {
    	return patientService.siteInfo();
    }

    @POST
    @Consumes("application/xml")
    public Response addPatient(PatientRepresentation patientRep) {
    	try {
    		PatientDto dto = patientDtoFactory.createPatientDto();
    		dto.setPatientId(patientRep.getPatientId());
    		dto.setName(patientRep.getName());
    		dto.setDob(patientRep.getDob());
    		dto.setAge(patientRep.getAge());
    		long id = patientService.addPatient(dto);
    		UriBuilder ub = uriInfo.getAbsolutePathBuilder().path("{id}");
    		URI url = ub.build(Long.toString(id));
    		return Response.created(url).build();
    	} catch (PatientServiceExn e) {
    		throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity("Unable to add patient")
					.type(MediaType.TEXT_PLAIN).build());
    	}
    }
    
	/**
	 * Query methods for patient resources.
	 */
	/*
	 * TODO output should be application/xml
	 */
    @GET
    @Path("{id}")
    @Produces("application/xml")
	public PatientRepresentation getPatient(@PathParam("id") String id) {
		try {
			long key = Long.parseLong(id);
			PatientDto patientDTO = patientService.getPatient(key);
			PatientRepresentation patientRep = new PatientRepresentation(patientDTO, uriInfo);
			return patientRep;
		} catch (PatientServiceExn e) {
			throw new ResourceNotFoundException("Patient resource not found for the given id");
		}
	}
    
	/*
	 * TODO output should be application/xml
	 */
    @GET
    @Path("byPatientId")
    @Produces("application/xml")
	public PatientRepresentation getPatientByPatientId(@QueryParam("id") String patientId) {
		/*
		 * TODO finish this
		 */
    	long patientid = Long.parseLong(patientId);
    	PatientDto patientDTO;
		try {
			patientDTO = patientService.getPatientByPatId(patientid);
			PatientRepresentation patientRep = new PatientRepresentation(patientDTO, uriInfo);
		return patientRep;
		} catch (PatientServiceExn e) {
			throw new ResourceNotFoundException("Patient resource not found for the given patientid");
		}
	}
    
	/*
	 * TODO output should be application/xml
	 */
    @GET
    @Path("/{id}/treatments/{tid}")
    @Produces("application/xml")
    public TreatmentRepresentation getPatientTreatment(@PathParam("id") String id, @PathParam("tid") String tid) {
    	try {
    		logger.info("Fetching TreatmentDTO");
    		TreatmentDto treatment = patientService.getTreatment(Long.parseLong(id), Long.parseLong(tid));
    		logger.info("Fetched TreatmentDTO");
    		logger.info("DTO details; "+treatment.getId()+"  "+treatment.getDiagnosis());
    		TreatmentRepresentation treatmentRep = new TreatmentRepresentation(treatment, uriInfo);
    		return treatmentRep;
    	} catch (PatientServiceExn e) {
    		throw new ResourceNotFoundException("Treatment resource not found for the given patientid and treatmentid");
    	}
    }

}