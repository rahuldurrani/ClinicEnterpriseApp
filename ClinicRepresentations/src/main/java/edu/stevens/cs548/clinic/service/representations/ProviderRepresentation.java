package edu.stevens.cs548.clinic.service.representations;

import java.util.List;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.annotation.XmlRootElement;

import edu.stevens.cs548.clinic.service.dto.ProviderDto;
import edu.stevens.cs548.clinic.service.dto.util.ProviderDtoFactory;
import edu.stevens.cs548.clinic.service.web.rest.data.ProviderType;
import edu.stevens.cs548.clinic.service.web.rest.data.dap.LinkType;

@XmlRootElement
public class ProviderRepresentation extends ProviderType{

	private ProviderDtoFactory providerdtofactory;
	
	public ProviderRepresentation()
	{
		super();
		this.providerdtofactory = new ProviderDtoFactory();
	}
	public List<LinkType> getLinksTreatments() {
		return this.getTreatments();
	}
	public ProviderRepresentation(ProviderDto providerdto, UriInfo uriInfo)
	{
		this();
		this.id = getProviderLink(providerdto.getId(),uriInfo);
		this.providerId = providerdto.getProviderId();
		this.providerName = providerdto.getProviderName();
		this.specilization = providerdto.getSepcialization();
		List<LinkType> links = this.getTreatments();
		for (long t : providerdto.getTreatments()) {
			links.add(TreatmentRepresentation.getTreatmentLink(t, uriInfo));
		}
		this.getTreatments().addAll(links);
	}
	
	public static LinkType getProviderLink(long id, UriInfo uriInfo) {
		UriBuilder ub = uriInfo.getBaseUriBuilder();
		ub.path("provider").path("{id}");
		String providerURI = ub.build(Long.toString(id)).toString();

		LinkType link = new LinkType();
		link.setUrl(providerURI);
		link.setRelation(Representation.RELATION_PROVIDER);
		link.setMediaType(Representation.MEDIA_TYPE);
		return link;
	}
	
	public ProviderDto getProviderDto()
	{
		ProviderDto dto = providerdtofactory.createProviderDto();
		dto.setId(Representation.getId(this.id));
		dto.setProviderId(this.providerId);
		dto.setProviderName(this.providerName);
		dto.setSepcialization(this.specilization);
		return dto;
	}
}
