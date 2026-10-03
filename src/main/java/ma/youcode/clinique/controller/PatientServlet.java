package ma.youcode.clinique.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.math.BigDecimal;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.youcode.clinique.entity.Patient;
import ma.youcode.clinique.repository.JdbcPatientDAO;
import ma.youcode.clinique.service.PatientService;
import ma.youcode.clinique.service.PatientServiceImpl;

@WebServlet("/infirmier/patients/nouveau")
public class PatientServlet extends HttpServlet {

    private PatientService patientService;

    @Override
    public void init() {
        patientService = new PatientServiceImpl(
                new JdbcPatientDAO()
        );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/WEB-INF/views/patient-form.jsp"
        ).forward(request, response);
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            Patient patient = new Patient();

            patient.setNom(request.getParameter("nom"));
            patient.setPrenom(request.getParameter("prenom"));

            patient.setDateNaissance(
                    LocalDate.parse(
                            request.getParameter("dateNaissance")
                    )
            );

            patient.setNumeroSecuriteSociale(
                    request.getParameter("nss")
            );

            patient.setTensionArterielle(
                    request.getParameter("tension")
            );

            patient.setFrequenceCardiaque(
                    Integer.parseInt(
                            request.getParameter("frequenceCardiaque")
                    )
            );

            patient.setTemperature(
                    new BigDecimal(
                            request.getParameter("temperature")
                    )
            );

            patient.setFrequenceRespiratoire(
                    Integer.parseInt(
                            request.getParameter("frequenceRespiratoire")
                    )
            );

            patientService.enregistrer(patient);

            response.sendRedirect(
                    request.getContextPath()
                    + "/infirmier/patients/nouveau?success=true"
            );

        } catch (IllegalArgumentException e) {

            request.setAttribute("error", e.getMessage());

            request.getRequestDispatcher(
                    "/WEB-INF/views/patient-form.jsp"
            ).forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                    "Erreur lors de l'enregistrement du patient.",
                    e
            );
        }
    }
}