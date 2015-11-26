package edu.stevens.cs548.clinic.service.dto.util;

import edu.stevens.cs548.clinic.domain.DrugTreatment;
import edu.stevens.cs548.clinic.domain.Radiology;
import edu.stevens.cs548.clinic.domain.Surgery;
import edu.stevens.cs548.clinic.service.dto.DrugTreatmentType;
import edu.stevens.cs548.clinic.service.dto.ObjectFactory;
import edu.stevens.cs548.clinic.service.dto.RadiologyType;
import edu.stevens.cs548.clinic.service.dto.SurgeryType;
import edu.stevens.cs548.clinic.service.dto.TreatmentDto;

public class TreatmentDtoFactory {
	
	ObjectFactory factory;
	
	public TreatmentDtoFactory() {
		factory = new ObjectFactory();
	}
	
	public TreatmentDto createTreatmentDTO()
	{
		return factory.createTreatmentDto();
	}
	
	public DrugTreatmentType createDrugTreatmentDto () {
		return factory.createDrugTreatmentType();
	}
	
	public TreatmentDto createTreatmentsDto (DrugTreatment t) {
		TreatmentDto drugdto = factory.createTreatmentDto();
		DrugTreatmentType dt= new DrugTreatmentType();
		dt.setDosage(t.getDosage());
		dt.setDrugName(t.getDrug());
		drugdto.setDiagnosis(t.getDiagnosis());
		drugdto.setId(t.getId());
		drugdto.setDrugTreatment(dt);
		return drugdto;
	}
	
	public SurgeryType createSurgeryTreatmentDto(){
		return factory.createSurgeryType();	
	}

	public TreatmentDto createTreatmentsDto(Surgery t){
		TreatmentDto drugdto = factory.createTreatmentDto();
		SurgeryType st = new SurgeryType();
		st.setSurgeryDate(t.getSurgeryDate());
		drugdto.setDiagnosis(t.getDiagnosis());
		drugdto.setId(t.getId());
		drugdto.setSurgery(st);
		return drugdto;	
	}
	
	public RadiologyType createRadiologyDto(){
		return factory.createRadiologyType();
	}
	
	public TreatmentDto createTreatmentsDto(Radiology t){
		TreatmentDto drugdto = factory.createTreatmentDto();
		RadiologyType rt = new RadiologyType();
		rt.getRadiologyDates().addAll(t.getRadiologyDates());
		drugdto.setDiagnosis(t.getDiagnosis());
		drugdto.setId(t.getId());
		drugdto.setRadiology(rt);
		return drugdto;	
	}
}
