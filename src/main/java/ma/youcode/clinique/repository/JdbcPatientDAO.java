package ma.youcode.clinique.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ma.youcode.clinique.config.DBConnection;
import ma.youcode.clinique.entity.Patient;

public class JdbcPatientDAO implements PatientDAO {

    @Override
    public void save(Patient patient) throws SQLException {

        String sql = """
                INSERT INTO patient (
                    nom,
                    prenom,
                    date_naissance,
                    numero_securite_sociale,
                    tension_arterielle,
                    frequence_cardiaque,
                    temperature,
                    frequence_respiratoire
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, patient.getNom());
            statement.setString(2, patient.getPrenom());
            statement.setObject(3, patient.getDateNaissance());
            statement.setString(4, patient.getNumeroSecuriteSociale());
            statement.setString(5, patient.getTensionArterielle());
            statement.setInt(6, patient.getFrequenceCardiaque());
            statement.setBigDecimal(7, patient.getTemperature());
            statement.setInt(8, patient.getFrequenceRespiratoire());

            statement.executeUpdate();
        }
    }
}