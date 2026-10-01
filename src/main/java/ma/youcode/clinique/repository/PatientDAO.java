package ma.youcode.clinique.repository;

import java.sql.SQLException;
import ma.youcode.clinique.entity.Patient;

public interface PatientDAO {

    void save(Patient patient) throws SQLException;
    Patient findById(int id) throws SQLException;

}