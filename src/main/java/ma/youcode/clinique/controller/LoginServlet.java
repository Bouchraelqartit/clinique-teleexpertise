package ma.youcode.clinique.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.youcode.clinique.entity.User;
import ma.youcode.clinique.repository.JdbcUserDAO;
import ma.youcode.clinique.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() {
        userService =
                new UserService(new JdbcUserDAO());
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username =
                request.getParameter("username");

        String password =
                request.getParameter("password");

        try {

            User user =
                    userService.login(username, password);

            if (user != null) {

                HttpSession session =
                        request.getSession();

                request.changeSessionId();
                session.setAttribute("user", user);

                if ("INFIRMIER".equals(user.getRole())) {

                    response.sendRedirect(
                            request.getContextPath()
                            + "/infirmier/patients"
                    );

                } else if ("GENERALISTE".equals(user.getRole())) {

                    response.sendRedirect(
                            request.getContextPath()
                            + "/generaliste/patients"
                    );

                } else {

                    response.sendError(
                            HttpServletResponse.SC_FORBIDDEN,
                            "Role inconnu"
                    );
                }

            } else {

                response.sendRedirect(
                        request.getContextPath()
                        + "/login.jsp?error=1"
                );
            }

        } catch (Exception e) {
            log("Erreur pendant l'authentification", e);
            request.setAttribute(
                    "loginError",
                    "Le service de connexion est indisponible. Vérifiez la base de données.");
            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        }
    }
}
