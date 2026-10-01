package ma.youcode.clinique.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;

import ma.youcode.clinique.entity.Patient;
import ma.youcode.clinique.repository.JdbcPatientDAO;

@WebServlet("/test-patient")
public class PatientTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Patient patient = new Patient();

        patient.setNom("Test");
        patient.setPrenom("Patient");
        patient.setDateNaissance(LocalDate.of(2000, 5, 10));
        patient.setNumeroSecuriteSociale("TEST123");
        patient.setTensionArterielle("120/80");
        patient.setFrequenceCardiaque(75);
        patient.setTemperature(new BigDecimal("36.5"));
        patient.setFrequenceRespiratoire(18);

        try {
            JdbcPatientDAO patientDAO = new JdbcPatientDAO();
            patientDAO.save(patient);

            response.getWriter().println("Patient enregistré avec succès !");

        } catch (Exception e) {
            response.getWriter().println("Erreur : " + e.getMessage());
        }
    }
}