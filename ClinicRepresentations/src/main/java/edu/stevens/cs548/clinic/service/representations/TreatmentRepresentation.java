package edu.stevens.cs548.clinic.service.representations;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

import edu.stevens.cs548.clinic.service.dto.TreatmentDto;
import edu.stevens.cs548.clinic.service.dto.util.TreatmentDtoFactory;
import edu.stevens.cs548.clinic.service.web.rest.data.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.web.rest.data.ObjectFactory;
import edu.stevens.cs548.clinic.service.web.rest.data.RadiologyType;
import edu.stevens.cs548.clinic.service.web.rest.data.SurgeryType;
import edu.stevens.cs548.clinic.service.web.rest.data.TreatmentType;
import edu.stevens.cs548.clinic.service.web.rest.data.dap.LinkType;

@XmlRootElement
public class TreatmentRepresentation extends TreatmentType {
	
	private ObjectFactory repFactory = new ObjectFactory();
	
	public LinkType getLinkPatient() {
		return this.getPatient();
	}
	
	public LinkType getLinkProvider() {
		return this.getProvider();
	}
	
	public static LinkType getTreatmentLink(long tid, UriInfo uriInfo) {
		UriBuilder ub = uriInfo.getBaseUriBuilder();
		ub.path("treatment");
		UriBuilder ubTreatment = ub.clone().path("{tid}");
		String treatmentURI = ubTreatment.build(Long.toString(tid)).toString();
	
		LinkType link = new LinkType();
		link.setUrl(treatmentURI);
		link.setRelation(Representation.RELATION_TREATMENT);
		link.setMediaType(Representation.MEDIA_TYPE);
		return link;
	}
	
	private TreatmentDtoFactory treatmentDtoFactory;
	
	public TreatmentRepresentation() {
		super();
		treatmentDtoFactory = new TreatmentDtoFactory();
	}
	
	public TreatmentRepresentation (TreatmentDto dto, UriInfo uriInfo) {
		this();
		this.id = getTreatmentLink(dto.getId(), uriInfo);
		this.patient =  PatientRepresentation.getPatientLink(dto.getPatient(), uriInfo);
		this.provider = ProviderRepresentation.getProviderLink(dto.getProvider(), uriInfo);
		/*
		 * TODO: Need to fill in provider information.
		 */
		
		this.diagnosis = dto.getDiagnosis();
		
		if (dto.getDrugTreatment() != null) {
			/*
			 * TODO finish this
			 */
			DrugTreatmentType dt = repFactory.createDrugTreatmentType();
			dt.setDosage(dto.getDrugTreatment().getDosage());
			dt.setDrugName(dto.getDrugTreatment().getDrugName());
			this.setDrugTreatment(dt);
		} else if (dto.getSurgery() != null) {
			/*
			 * TODO finish this
			 */
			SurgeryType st = repFactory.createSurgeryType();
			st.setSurgeryDate(dto.getSurgery().getSurgeryDate());
			this.setSurgery(st);
		} else if (dto.getRadiology() != null) {
			/*
			 * TODO finish this
			 */
			RadiologyType rt = repFactory.createRadiologyType();
			rt.getRadiologyDates().addAll(dto.getRadiology().getRadiologyDates());
			this.setRadiology(rt);
		}
	}

	public TreatmentDto getTreatment() {
		TreatmentDto m = null;
		if (this.getDrugTreatment() != null) {
			m = treatmentDtoFactory.createTreatmentDTO();
			m.setId(Representation.getId(id));
			m.setPatient(Representation.getId(patient));
			m.setProvider(Representation.getId(provider));
			m.setDiagnosis(diagnosis);
			edu.stevens.cs548.clinic.service.dto.DrugTreatmentType dt = new edu.stevens.cs548.clinic.service.dto.DrugTreatmentType();
			dt.setDosage(drugTreatment.getDosage());
			dt.setDrugName(drugTreatment.getDrugName());
			m.setDrugTreatment(dt);
			/*
			 * TODO finish this
			 */
		} else if (this.getSurgery() != null) {
			/*
			 * TODO finish this
			 */
			m = treatmentDtoFactory.createTreatmentDTO();
			m.setId(Representation.getId(id));
			m.setPatient(Representation.getId(patient));
			m.setProvider(Representation.getId(provider));
			m.setDiagnosis(diagnosis);
			edu.stevens.cs548.clinic.service.dto.SurgeryType st = new edu.stevens.cs548.clinic.service.dto.SurgeryType();
			st.setSurgeryDate(surgery.getSurgeryDate());
			m.setSurgery(st);
		} else if (this.getRadiology() != null) {
			/*
			 * TODO finish this
			 */
			m = treatmentDtoFactory.createTreatmentDTO();
			m.setId(Representation.getId(id));
			m.setPatient(Representation.getId(patient));
			m.setProvider(Representation.getId(provider));
			m.setDiagnosis(diagnosis);
			edu.stevens.cs548.clinic.service.dto.RadiologyType rt = new edu.stevens.cs548.clinic.service.dto.RadiologyType();
			rt.getRadiologyDates().addAll(radiology.getRadiologyDates());
		}
		return m;
	}

	
}
