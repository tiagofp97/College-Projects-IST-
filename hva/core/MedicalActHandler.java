package hva.core;

import java.util.List;

import hva.core.vaccine.VaccineApplication;

public interface MedicalActHandler {
    public abstract void addMedicalAct(VaccineApplication vApplication);
    public abstract List<VaccineApplication> getMedicalActs();
}