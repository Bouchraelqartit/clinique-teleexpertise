package ma.youcode.clinique.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import ma.youcode.clinique.config.DBConnection;
import ma.youcode.clinique.entity.Consultation;

public class JdbcConsultationDAO implements ConsultationDAO {

    @Override
    public void save(Consultation consultation) throws SQLException {

        String sql = """
                INSERT INTO consultation (
                    motif,
                    observations,
                    diagnostic,
                    traitement,
                    cout,
                    statut,
                    date_consultation,
                    patient_id,
                    medecin_id
                )
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, consultation.getMotif());
            statement.setString(2, consultation.getObservations());
            statement.setString(3, consultation.getDiagnostic());
            statement.setString(4, consultation.getTraitement());
            statement.setBigDecimal(5, consultation.getCout());
            statement.setString(6, consultation.getStatut());
            statement.setObject(7, consultation.getDateConsultation());
            statement.setInt(8, consultation.getPatientId());
            statement.setInt(9, consultation.getMedecinId());

            statement.executeUpdate();
        }
    }

    @Override
    public Optional<Consultation> findByPatientId(
            int patientId) throws SQLException {

        String sql = """
                SELECT *
                FROM consultation
                WHERE patient_id = ?
                """;

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, patientId);

            try (ResultSet resultSet = statement.executeQuery()) {

                if (resultSet.next()) {

                    Consultation consultation =
                            new Consultation();

                    consultation.setId(
                            resultSet.getInt("id")
                    );

                    consultation.setMotif(
                            resultSet.getString("motif")
                    );

                    consultation.setObservations(
                            resultSet.getString("observations")
                    );

                    consultation.setDiagnostic(
                            resultSet.getString("diagnostic")
                    );

                    consultation.setTraitement(
                            resultSet.getString("traitement")
                    );

                    consultation.setCout(
                            resultSet.getBigDecimal("cout")
                    );

                    consultation.setStatut(
                            resultSet.getString("statut")
                    );

                    consultation.setDateConsultation(
                            resultSet.getTimestamp(
                                    "date_consultation"
                            ).toLocalDateTime()
                    );

                    consultation.setPatientId(
                            resultSet.getInt("patient_id")
                    );

                    consultation.setMedecinId(
                            resultSet.getInt("medecin_id")
                    );

                    return Optional.of(consultation);
                }
            }
        }

        return Optional.empty();
    }
}
