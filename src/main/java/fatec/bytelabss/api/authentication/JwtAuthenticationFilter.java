package fatec.bytelabss.api.authentication;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends GenericFilterBean {
    @Autowired
    private JwtService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            HttpServletRequest servletRequest = (HttpServletRequest) request;
            String authorization = servletRequest.getHeader(HttpHeaders.AUTHORIZATION);

            if (authorization != null && authorization.startsWith("Bearer ")) {
                String token = authorization.replace("Bearer ", "");
                Authentication credentials = jwtService.parseToken(token);
                SecurityContextHolder.getContext().setAuthentication(credentials);
            }

            chain.doFilter(request, response);
        } catch (JwtException | AuthenticationServiceException e) {
            System.err.println("Erro de autenticação JWT: " + e.getMessage());

            HttpServletResponse servletResponse = (HttpServletResponse) response;
            servletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Erro de autenticação: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Erro inesperado durante a autenticação JWT: " + e.getMessage());
            HttpServletResponse servletResponse = (HttpServletResponse) response;
            servletResponse.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro interno no servidor: " + e.getMessage());
        }
    }
}
