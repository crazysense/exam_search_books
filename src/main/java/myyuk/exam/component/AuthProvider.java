package myyuk.exam.component;

import myyuk.exam.model.User;
import myyuk.exam.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(AuthProvider.class);

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName();
        String password = authentication.getCredentials().toString();
        return authenticate(userId, password);
    }

    private Authentication authenticate(String userId, String password) throws AuthenticationException {
        User user = userService.findByUserId(userId);
        if (user == null || !userService.passwordEncoder().matches(password, user.getUserPassword())) {
            logger.error("Unregistered user or invalid password: {}", userId);
            throw new BadCredentialsException("Unregistered user or invalid password: " + userId);
        }

        return new UsernamePasswordAuthenticationToken(userId, password,
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(clazz);
    }
}
