package org.thoughtworks.lean.identity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.thoughtworks.lean.identity.domain.Authentication;
import org.thoughtworks.lean.identity.domain.CurrentUser;
import org.thoughtworks.lean.identity.domain.User;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrentUserDetailsService.class);
    private final UserService userService;

    @Autowired
    public CurrentUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.debug("Authenticating user with email={}", email.replaceFirst("@.*", "@***"));
        try {
            Authentication authentication = userService.getAuthenticationByEmail(email);
            return new CurrentUser(authentication);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException(String.format("User with email=%s was not found", email));
        }
    }

}