
package edu.stevens.cs548.clinic.service.web.soap.provider;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.stevens.cs548.clinic.service.web.soap.provider package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetProvider_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "getProvider");
    private final static QName _GetProviderbyNPI_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "getProviderbyNPI");
    private final static QName _GetProviderResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "getProviderResponse");
    private final static QName _SiteInfo_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "siteInfo");
    private final static QName _ProvideraddTreatmentResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "provideraddTreatmentResponse");
    private final static QName _AddProvider_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "addProvider");
    private final static QName _GetProviderbyNPIResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "getProviderbyNPIResponse");
    private final static QName _ProviderServiceExn_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "ProviderServiceExn");
    private final static QName _SiteInfoResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "siteInfoResponse");
    private final static QName _AddProviderResponse_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "addProviderResponse");
    private final static QName _ProvideraddTreatment_QNAME = new QName("http://cs548.stevens.edu/clinic/service/web/soap", "provideraddTreatment");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.stevens.cs548.clinic.service.web.soap.provider
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProviderServiceExn }
     * 
     */
    public ProviderServiceExn createProviderServiceExn() {
        return new ProviderServiceExn();
    }

    /**
     * Create an instance of {@link AddProvider }
     * 
     */
    public AddProvider createAddProvider() {
        return new AddProvider();
    }

    /**
     * Create an instance of {@link GetProviderbyNPIResponse }
     * 
     */
    public GetProviderbyNPIResponse createGetProviderbyNPIResponse() {
        return new GetProviderbyNPIResponse();
    }

    /**
     * Create an instance of {@link AddProviderResponse }
     * 
     */
    public AddProviderResponse createAddProviderResponse() {
        return new AddProviderResponse();
    }

    /**
     * Create an instance of {@link SiteInfoResponse }
     * 
     */
    public SiteInfoResponse createSiteInfoResponse() {
        return new SiteInfoResponse();
    }

    /**
     * Create an instance of {@link ProvideraddTreatment }
     * 
     */
    public ProvideraddTreatment createProvideraddTreatment() {
        return new ProvideraddTreatment();
    }

    /**
     * Create an instance of {@link GetProvider }
     * 
     */
    public GetProvider createGetProvider() {
        return new GetProvider();
    }

    /**
     * Create an instance of {@link GetProviderbyNPI }
     * 
     */
    public GetProviderbyNPI createGetProviderbyNPI() {
        return new GetProviderbyNPI();
    }

    /**
     * Create an instance of {@link SiteInfo }
     * 
     */
    public SiteInfo createSiteInfo() {
        return new SiteInfo();
    }

    /**
     * Create an instance of {@link GetProviderResponse }
     * 
     */
    public GetProviderResponse createGetProviderResponse() {
        return new GetProviderResponse();
    }

    /**
     * Create an instance of {@link ProvideraddTreatmentResponse }
     * 
     */
    public ProvideraddTreatmentResponse createProvideraddTreatmentResponse() {
        return new ProvideraddTreatmentResponse();
    }

    /**
     * Create an instance of {@link ProviderDto }
     * 
     */
    public ProviderDto createProviderDto() {
        return new ProviderDto();
    }

    /**
     * Create an instance of {@link TreatmentDto }
     * 
     */
    public TreatmentDto createTreatmentDto() {
        return new TreatmentDto();
    }

    /**
     * Create an instance of {@link DrugTreatmentType }
     * 
     */
    public DrugTreatmentType createDrugTreatmentType() {
        return new DrugTreatmentType();
    }

    /**
     * Create an instance of {@link SurgeryType }
     * 
     */
    public SurgeryType createSurgeryType() {
        return new SurgeryType();
    }

    /**
     * Create an instance of {@link RadiologyType }
     * 
     */
    public RadiologyType createRadiologyType() {
        return new RadiologyType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProvider }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "getProvider")
    public JAXBElement<GetProvider> createGetProvider(GetProvider value) {
        return new JAXBElement<GetProvider>(_GetProvider_QNAME, GetProvider.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProviderbyNPI }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "getProviderbyNPI")
    public JAXBElement<GetProviderbyNPI> createGetProviderbyNPI(GetProviderbyNPI value) {
        return new JAXBElement<GetProviderbyNPI>(_GetProviderbyNPI_QNAME, GetProviderbyNPI.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProviderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "getProviderResponse")
    public JAXBElement<GetProviderResponse> createGetProviderResponse(GetProviderResponse value) {
        return new JAXBElement<GetProviderResponse>(_GetProviderResponse_QNAME, GetProviderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "siteInfo")
    public JAXBElement<SiteInfo> createSiteInfo(SiteInfo value) {
        return new JAXBElement<SiteInfo>(_SiteInfo_QNAME, SiteInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProvideraddTreatmentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "provideraddTreatmentResponse")
    public JAXBElement<ProvideraddTreatmentResponse> createProvideraddTreatmentResponse(ProvideraddTreatmentResponse value) {
        return new JAXBElement<ProvideraddTreatmentResponse>(_ProvideraddTreatmentResponse_QNAME, ProvideraddTreatmentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProvider }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "addProvider")
    public JAXBElement<AddProvider> createAddProvider(AddProvider value) {
        return new JAXBElement<AddProvider>(_AddProvider_QNAME, AddProvider.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetProviderbyNPIResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "getProviderbyNPIResponse")
    public JAXBElement<GetProviderbyNPIResponse> createGetProviderbyNPIResponse(GetProviderbyNPIResponse value) {
        return new JAXBElement<GetProviderbyNPIResponse>(_GetProviderbyNPIResponse_QNAME, GetProviderbyNPIResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProviderServiceExn }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "ProviderServiceExn")
    public JAXBElement<ProviderServiceExn> createProviderServiceExn(ProviderServiceExn value) {
        return new JAXBElement<ProviderServiceExn>(_ProviderServiceExn_QNAME, ProviderServiceExn.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SiteInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "siteInfoResponse")
    public JAXBElement<SiteInfoResponse> createSiteInfoResponse(SiteInfoResponse value) {
        return new JAXBElement<SiteInfoResponse>(_SiteInfoResponse_QNAME, SiteInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddProviderResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "addProviderResponse")
    public JAXBElement<AddProviderResponse> createAddProviderResponse(AddProviderResponse value) {
        return new JAXBElement<AddProviderResponse>(_AddProviderResponse_QNAME, AddProviderResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProvideraddTreatment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cs548.stevens.edu/clinic/service/web/soap", name = "provideraddTreatment")
    public JAXBElement<ProvideraddTreatment> createProvideraddTreatment(ProvideraddTreatment value) {
        return new JAXBElement<ProvideraddTreatment>(_ProvideraddTreatment_QNAME, ProvideraddTreatment.class, null, value);
    }

}
