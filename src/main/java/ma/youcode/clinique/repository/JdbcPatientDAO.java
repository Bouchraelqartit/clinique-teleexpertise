package ma.youcode.clinique.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ma.youcode.clinique.config.DBConnection;
import ma.youcode.clinique.entity.Consultation;
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
            SELECT
                p.*,
                c.id AS consultation_id,
                c.motif AS consultation_motif,
                c.observations AS consultation_observations,
                c.diagnostic AS consultation_diagnostic,
                c.traitement AS consultation_traitement,
                c.cout AS consultation_cout,
                c.statut AS consultation_statut,
                c.date_consultation AS consultation_date,
                c.medecin_id AS consultation_medecin_id
            FROM patient p
            LEFT JOIN consultation c
                ON c.patient_id = p.id
            ORDER BY p.heure_arrivee ASC
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
                    resultSet.getTimestamp("heure_arrivee")
                            .toLocalDateTime()
            );

            // Si une consultation existe
            if (resultSet.getObject("consultation_id") != null) {

                Consultation consultation = new Consultation();

                consultation.setId(
                        resultSet.getInt("consultation_id")
                );

                consultation.setMotif(
                        resultSet.getString("consultation_motif")
                );

                consultation.setObservations(
                        resultSet.getString("consultation_observations")
                );

                consultation.setDiagnostic(
                        resultSet.getString("consultation_diagnostic")
                );

                consultation.setTraitement(
                        resultSet.getString("consultation_traitement")
                );

                consultation.setCout(
                        resultSet.getBigDecimal("consultation_cout")
                );

                consultation.setStatut(
                        resultSet.getString("consultation_statut")
                );

                if (resultSet.getTimestamp("consultation_date") != null) {
                    consultation.setDateConsultation(
                            resultSet.getTimestamp("consultation_date")
                                    .toLocalDateTime()
                    );
                }

                consultation.setPatientId(
                        patient.getId()
                );

                consultation.setMedecinId(
                        resultSet.getInt("consultation_medecin_id")
                );

                patient.setConsultation(consultation);
            }

            patients.add(patient);
        }
    }

    return patients;
}

















@Override
public Patient findByNss(String nss) throws SQLException {

    String sql = """
            SELECT *
            FROM patient
            WHERE numero_securite_sociale = ?
            """;

    try (Connection connection = DBConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setString(1, nss);

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
                        resultSet.getTimestamp("heure_arrivee")
                                .toLocalDateTime()
                );

                return patient;
            }
        }
    }

    return null;
}
}