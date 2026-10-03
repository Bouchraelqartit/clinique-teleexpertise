package ma.youcode.clinique.filter;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.Base64;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter("/*")
public class CsrfFilter implements Filter {

    private static final String CSRF_TOKEN =
            "csrfToken";

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
                httpRequest.getSession();

        String token =
                (String) session.getAttribute(CSRF_TOKEN);

        if (token == null) {

            byte[] randomBytes =
                    new byte[32];

            new SecureRandom()
                    .nextBytes(randomBytes);

            token = Base64.getUrlEncoder()
                    .withoutPadding()
                    .encodeToString(randomBytes);

            session.setAttribute(
                    CSRF_TOKEN,
                    token
            );
        }

       if ("POST".equalsIgnoreCase(
        httpRequest.getMethod())) {

    String requestToken =
            httpRequest.getParameter(CSRF_TOKEN);

            System.out.println("SESSION TOKEN = " + token);
System.out.println("REQUEST TOKEN = " + requestToken);

    if (requestToken == null
            || !token.equals(requestToken)) {

        httpResponse.sendError(
                HttpServletResponse.SC_FORBIDDEN,
                "CSRF token invalide"
        );

        return;
    }
}

request.setAttribute(CSRF_TOKEN, token);

chain.doFilter(
        request,
        response
);
    }
}