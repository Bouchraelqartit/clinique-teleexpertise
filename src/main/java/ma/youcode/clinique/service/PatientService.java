package ma.youcode.clinique.service;

import java.sql.SQLException;
import java.util.List;

import ma.youcode.clinique.entity.Patient;

public interface PatientService {

    void enregistrer(Patient patient) throws SQLException;
    

    List<Patient> patientsDuJour() throws SQLException;
    List<Patient> patientsEnAttente() throws SQLException;
}