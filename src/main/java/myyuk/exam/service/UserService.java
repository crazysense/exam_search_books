package myyuk.exam.service;

import myyuk.exam.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService extends UserDetailsService {
    User findByUserId(String userId);

    User save(User user);

    PasswordEncoder passwordEncoder();
}
