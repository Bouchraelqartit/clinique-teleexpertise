package ma.youcode.clinique.service;

import java.sql.SQLException;

import ma.youcode.clinique.entity.Consultation;

public interface ConsultationService {

    void cloturer(
            int patientId,
            Consultation consultation,
            int medecinId
    ) throws SQLException;
}