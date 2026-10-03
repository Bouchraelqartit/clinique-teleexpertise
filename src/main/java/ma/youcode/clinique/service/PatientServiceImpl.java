package ma.youcode.clinique.service;

import java.sql.SQLException;
import java.time.LocalDateTime;

import ma.youcode.clinique.entity.Patient;
import ma.youcode.clinique.repository.PatientDAO;

public class PatientServiceImpl implements PatientService {

    private final PatientDAO patientDAO;

    public PatientServiceImpl(PatientDAO patientDAO) {
        this.patientDAO = patientDAO;
    }

    @Override
    public void enregistrer(Patient patient) throws SQLException {

        if (patient == null) {
            throw new IllegalArgumentException("Patient obligatoire.");
        }

        if (patient.getNom() == null || patient.getNom().isBlank()) {
            throw new IllegalArgumentException("Le nom est obligatoire.");
        }

        if (patient.getPrenom() == null || patient.getPrenom().isBlank()) {
            throw new IllegalArgumentException("Le prénom est obligatoire.");
        }

        if (patient.getDateNaissance() == null) {
            throw new IllegalArgumentException("La date de naissance est obligatoire.");
        }

        if (patient.getNumeroSecuriteSociale() == null
                || patient.getNumeroSecuriteSociale().isBlank()) {
            throw new IllegalArgumentException("Le NSS est obligatoire.");
        }

        if (patient.getTensionArterielle() == null
                || patient.getTensionArterielle().isBlank()) {
            throw new IllegalArgumentException("La tension est obligatoire.");
        }

        if (patient.getFrequenceCardiaque() <= 0) {
            throw new IllegalArgumentException("La fréquence cardiaque est invalide.");
        }

        if (patient.getTemperature() == null) {
            throw new IllegalArgumentException("La température est obligatoire.");
        }

        if (patient.getFrequenceRespiratoire() <= 0) {
            throw new IllegalArgumentException("La fréquence respiratoire est invalide.");
        }

        patient.setHeureArrivee(LocalDateTime.now());

        patientDAO.save(patient);
    }
}