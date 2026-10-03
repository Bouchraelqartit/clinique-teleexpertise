package ma.youcode.clinique.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import ma.youcode.clinique.config.DBConnection;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/test-db")
public class DBTestServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection connection = DBConnection.getConnection()) {

            response.getWriter().println(
                "Connexion DataSource réussie !"
            );

        } catch (Exception e) {

            response.getWriter().println(
                "Erreur : " + e.getMessage()
            );
        }
    }
}