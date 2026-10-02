
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
        userService = new UserService(new JdbcUserDAO());
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            User user = userService.login(username, password);

            if (user != null) {

                HttpSession session = request.getSession();
                session.setAttribute("user", user);

                

                response.getWriter().println(
                        "Login réussi : " + user.getUsername()
                );

            } else {

                response.getWriter().println(
                        "Username ou password incorrect"
                );
            }

        } catch (Exception e) {

            response.getWriter().println(
                    "Erreur : " + e.getMessage()
            );
        }
    }
}

