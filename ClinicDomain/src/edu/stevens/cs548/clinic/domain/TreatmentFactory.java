package edu.stevens.cs548.clinic.domain;

import java.util.Date;
import java.util.List;

public class TreatmentFactory implements ITreatmentFactory {

	@Override
	public Treatment createDrugTreatment(String diagnosis, String drug, float dosage) {
		DrugTreatment treatment = new DrugTreatment();
		treatment.setDiagnosis(diagnosis);
		treatment.setDrug(drug);
		treatment.setDosage(dosage);
		treatment.setTreatmentType(TreatmentType.DRUG_TREATMENT.getTag());
		return null;
	}

	@Override
	public Treatment createSurgery(Date surgeryDate, String diagnosis) {
		Surgery treatment = new Surgery();
		treatment.setDiagnosis(diagnosis);
		treatment.setSurgeryDate(surgeryDate);
		treatment.setTreatmentType(TreatmentType.SURGERY.getTag());
		return null;
	}

	@Override
	public Treatment createRadiology(List<Date> radiologyDates, String diagnosis) {
		Radiology treatment = new Radiology();
		treatment.setDiagnosis(diagnosis);
		treatment.setRadiologyDates(radiologyDates);
		treatment.setTreatmentType(TreatmentType.RADIOLOGY.getTag());
		return null;
	}

}
