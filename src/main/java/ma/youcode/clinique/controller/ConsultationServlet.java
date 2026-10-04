package ma.youcode.clinique.controller;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.youcode.clinique.entity.Consultation;
import ma.youcode.clinique.entity.Patient;
import ma.youcode.clinique.entity.User;
import ma.youcode.clinique.repository.JdbcConsultationDAO;
import ma.youcode.clinique.repository.JdbcPatientDAO;
import ma.youcode.clinique.service.ConsultationService;
import ma.youcode.clinique.service.ConsultationServiceImpl;
import ma.youcode.clinique.service.PatientServiceImpl;

@WebServlet({
        "/generaliste/patients",
        "/generaliste/consultation"
})
public class ConsultationServlet extends HttpServlet {

    private ConsultationService consultationService;
    private JdbcPatientDAO patientDAO;

    @Override
    public void init() {
        patientDAO = new JdbcPatientDAO();

        consultationService =
                new ConsultationServiceImpl(
                        new JdbcConsultationDAO(),
                        patientDAO
                );
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // Liste des patients en attente
        if ("/generaliste/patients".equals(path)) {

            try {
                request.setAttribute(
                        "patients",
                        new PatientServiceImpl(
                                new JdbcPatientDAO()
                        ).patientsEnAttente()
                );

                request.getRequestDispatcher(
                        "/WEB-INF/views/attente.jsp"
                ).forward(request, response);

            } catch (SQLException e) {
                throw new ServletException(
                        "Erreur lors de la récupération des patients en attente.",
                        e
                );
            }

            return;
        }

        // Ouverture du dossier de consultation
        String patientIdParam =
                request.getParameter("patientId");

        if (patientIdParam == null) {
            response.sendRedirect(
                    request.getContextPath()
                            + "/generaliste/patients"
            );
            return;
        }

        try {

            int patientId =
                    Integer.parseInt(patientIdParam);

            Patient patient =
                    patientDAO.findById(patientId);

            if (patient == null) {
                response.sendRedirect(
                        request.getContextPath()
                                + "/generaliste/patients"
                );
                return;
            }

            request.setAttribute(
                    "patient",
                    patient
            );

            request.getRequestDispatcher(
                    "/WEB-INF/views/consultation-form.jsp"
            ).forward(request, response);

        } catch (NumberFormatException e) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/generaliste/patients"
            );

        } catch (SQLException e) {

            throw new ServletException(
                    "Erreur lors de la récupération du patient.",
                    e
            );
        }
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {

            int patientId =
                    Integer.parseInt(
                            request.getParameter("patientId")
                    );

            Consultation consultation =
                    new Consultation();

            consultation.setMotif(
                    request.getParameter("motif")
            );

            consultation.setObservations(
                    request.getParameter("observations")
            );

            consultation.setDiagnostic(
                    request.getParameter("diagnostic")
            );

            consultation.setTraitement(
                    request.getParameter("traitement")
            );

            User user =
                    (User) request.getSession()
                            .getAttribute("user");

            if (user == null) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/login.jsp"
                );

                return;
            }

            int medecinId = user.getId();

            consultationService.cloturer(
                    patientId,
                    consultation,
                    medecinId
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/generaliste/patients"
            );

        } catch (IllegalArgumentException e) {

            request.setAttribute(
                    "error",
                    e.getMessage()
            );

            try {

                int patientId =
                        Integer.parseInt(
                                request.getParameter("patientId")
                        );

                Patient patient =
                        patientDAO.findById(patientId);

                request.setAttribute(
                        "patient",
                        patient
                );

            } catch (Exception ignored) {
            }

            request.getRequestDispatcher(
                    "/WEB-INF/views/consultation-form.jsp"
            ).forward(request, response);

        } catch (SQLException e) {

            throw new ServletException(
                    "Erreur lors de la clôture de la consultation.",
                    e
            );
        }
    }
}
