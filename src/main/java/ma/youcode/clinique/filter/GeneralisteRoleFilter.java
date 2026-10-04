package ma.youcode.clinique.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ma.youcode.clinique.entity.User;

@WebFilter("/generaliste/*")
public class GeneralisteRoleFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest =
                (HttpServletRequest) request;

        HttpServletResponse httpResponse =
                (HttpServletResponse) response;

        HttpSession session =
                httpRequest.getSession(false);

        if (session == null) {
            httpResponse.sendRedirect(
                    httpRequest.getContextPath() + "/login.jsp"
            );
            return;
        }

        User user =
                (User) session.getAttribute("user");

        if (user == null) {
            httpResponse.sendRedirect(
                    httpRequest.getContextPath() + "/login.jsp"
            );
            return;
        }
    System.out.println("USER = " + user.getUsername());
System.out.println("ROLE = " + user.getRole());
        if (!"GENERALISTE".equals(user.getRole())) {
            httpResponse.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Accès refusé"
            );
            return;
        }

        chain.doFilter(request, response);
    }
}