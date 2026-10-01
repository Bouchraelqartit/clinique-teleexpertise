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

    try {
        JdbcPatientDAO patientDAO = new JdbcPatientDAO();

        Patient patientTrouve = patientDAO.findById(1);

        if (patientTrouve != null) {
            response.getWriter().println("Nom : " + patientTrouve.getNom());
            response.getWriter().println("Prénom : " + patientTrouve.getPrenom());
            response.getWriter().println("NSS : " + patientTrouve.getNumeroSecuriteSociale());
        } else {
            response.getWriter().println("Patient introuvable");
        }

    } catch (Exception e) {
        response.getWriter().println("Erreur : " + e.getMessage());
    }
}
}