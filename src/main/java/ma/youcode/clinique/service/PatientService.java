package ma.youcode.clinique.service;

import java.sql.SQLException;

import ma.youcode.clinique.entity.Patient;

public interface PatientService {

    void enregistrer(Patient patient) throws SQLException;
    
}