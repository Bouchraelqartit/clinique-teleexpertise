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

@WebFilter({"/generaliste/*", "/infirmier/*", "/protected/*"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        User user = session == null
                ? null
                : (User) session.getAttribute("user");

        if (user == null) {
            httpResponse.sendRedirect(
                    httpRequest.getContextPath() + "/login.jsp");
            return;
        }

        String path = httpRequest.getServletPath();
        String requiredRole = null;
        if (path.startsWith("/generaliste/")
                || path.startsWith("/protected/generaliste/")) {
            requiredRole = "GENERALISTE";
        } else if (path.startsWith("/infirmier/")) {
            requiredRole = "INFIRMIER";
        }

        if (requiredRole != null && !requiredRole.equals(user.getRole())) {
            httpResponse.sendError(
                    HttpServletResponse.SC_FORBIDDEN,
                    "Accès réservé au rôle " + requiredRole);
            return;
        }

        chain.doFilter(request, response);
    }
}
