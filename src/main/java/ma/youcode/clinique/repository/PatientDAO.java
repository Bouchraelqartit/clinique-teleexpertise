package ma.youcode.clinique.repository;

import java.sql.SQLException;
import java.util.List;

import ma.youcode.clinique.entity.Patient;

public interface PatientDAO {

    void save(Patient patient) throws SQLException;

    Patient findById(int id) throws SQLException;

    List<Patient> findAll() throws SQLException;

    Patient findByNss(String nss) throws SQLException;
}