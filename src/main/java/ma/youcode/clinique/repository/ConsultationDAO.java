package ma.youcode.clinique.repository;

import java.sql.SQLException;
import java.util.Optional;

import ma.youcode.clinique.entity.Consultation;

public interface ConsultationDAO {

    void save(Consultation consultation) throws SQLException;

    Optional<Consultation> findByPatientId(int patientId) throws SQLException;
}