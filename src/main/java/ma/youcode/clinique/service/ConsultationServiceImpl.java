package ma.youcode.clinique.service;

import java.sql.SQLException;
import java.time.LocalDateTime;

import ma.youcode.clinique.entity.Consultation;
import ma.youcode.clinique.entity.Patient;
import ma.youcode.clinique.repository.JdbcConsultationDAO;
import ma.youcode.clinique.repository.PatientDAO;

public class ConsultationServiceImpl implements ConsultationService {

    private final JdbcConsultationDAO consultationDAO;
    private final PatientDAO patientDAO;

    public ConsultationServiceImpl(
            JdbcConsultationDAO consultationDAO,
            PatientDAO patientDAO) {

        this.consultationDAO = consultationDAO;
        this.patientDAO = patientDAO;
    }

    @Override
    public void cloturer(
            int patientId,
            Consultation consultation,
            int medecinId) throws SQLException {

        if (consultation == null) {
            throw new IllegalArgumentException(
                    "Les données de consultation sont obligatoires."
            );
        }

      Patient patient = patientDAO.findById(patientId);

if (patient == null) {
    throw new IllegalArgumentException(
            "Patient introuvable."
    );
}

        if (consultationDAO.findByPatientId(patientId).isPresent()) {
            throw new IllegalArgumentException(
                    "Ce patient a déjà été consulté."
            );
        }

        if (consultation.getMotif() == null
                || consultation.getMotif().isBlank()) {

            throw new IllegalArgumentException(
                    "Le motif est obligatoire."
            );
        }

        if (consultation.getDiagnostic() == null
                || consultation.getDiagnostic().isBlank()) {

            throw new IllegalArgumentException(
                    "Le diagnostic est obligatoire."
            );
        }

        consultation.setPatientId(patientId);
        consultation.setMedecinId(medecinId);

        consultation.setCout(
                new java.math.BigDecimal("150.00")
        );

        consultation.setStatut("TERMINEE");

        consultation.setDateConsultation(
                LocalDateTime.now()
        );

        consultationDAO.save(consultation);
    }
}