package org.thoughtworks.lean.identity;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisabledAnonymousBasicAuthenticationFilter extends BasicAuthenticationFilter {

    private AuthenticationEntryPoint authenticationEntryPoint;

    public DisabledAnonymousBasicAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
        this.authenticationEntryPoint = authenticationEntryPoint;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Basic ")) {
            final AuthenticationCredentialsNotFoundException err = new AuthenticationCredentialsNotFoundException("Authentication request for failed with no  authorization token!");
            authenticationEntryPoint.commence(request, response, err);
            return;
        }

        super.doFilterInternal(request, response, chain);
    }
}
