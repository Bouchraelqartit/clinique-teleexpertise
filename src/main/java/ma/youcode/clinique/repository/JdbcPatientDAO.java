package ma.youcode.clinique.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public Patient findById(int id) throws SQLException {

        String sql = """ 
        SELECT * FROM patient WHERE id = ? """;

    try (Connection connection = DBConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, id);

        try (var resultSet = statement.executeQuery()) {

            if (resultSet.next()) {

                Patient patient = new Patient();

                patient.setId(resultSet.getInt("id"));
                patient.setNom(resultSet.getString("nom"));
                patient.setPrenom(resultSet.getString("prenom"));
                patient.setDateNaissance(
                        resultSet.getDate("date_naissance").toLocalDate()
                );
                patient.setNumeroSecuriteSociale(
                        resultSet.getString("numero_securite_sociale")
                );
                patient.setTensionArterielle(
                        resultSet.getString("tension_arterielle")
                );
                patient.setFrequenceCardiaque(
                        resultSet.getInt("frequence_cardiaque")
                );
                patient.setTemperature(
                        resultSet.getBigDecimal("temperature")
                );
                patient.setFrequenceRespiratoire(
                        resultSet.getInt("frequence_respiratoire")
                );
                patient.setHeureArrivee(
                        resultSet.getTimestamp("heure_arrivee").toLocalDateTime()
                );

                return patient;
            }

            return null;
        }
    }
}
    @Override
    public List<Patient> findAll() throws SQLException {

    String sql = """
            SELECT *
            FROM patient
            ORDER BY heure_arrivee ASC
            """;

    List<Patient> patients = new ArrayList<>();

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         var resultSet = statement.executeQuery()) {

        while (resultSet.next()) {

            Patient patient = new Patient();

            patient.setId(resultSet.getInt("id"));
            patient.setNom(resultSet.getString("nom"));
            patient.setPrenom(resultSet.getString("prenom"));
            patient.setDateNaissance(
                    resultSet.getDate("date_naissance").toLocalDate()
            );
            patient.setNumeroSecuriteSociale(
                    resultSet.getString("numero_securite_sociale")
            );
            patient.setTensionArterielle(
                    resultSet.getString("tension_arterielle")
            );
            patient.setFrequenceCardiaque(
                    resultSet.getInt("frequence_cardiaque")
            );
            patient.setTemperature(
                    resultSet.getBigDecimal("temperature")
            );
            patient.setFrequenceRespiratoire(
                    resultSet.getInt("frequence_respiratoire")
            );
            patient.setHeureArrivee(
                    resultSet.getTimestamp("heure_arrivee").toLocalDateTime()
            );

            patients.add(patient);
        }
    }

    return patients;
}
}