package myyuk.exam.service.impl;

import myyuk.exam.model.User;
import myyuk.exam.repo.UserRepo;
import myyuk.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = this.findByUserId(userId);
        if (user == null) {
            throw new UsernameNotFoundException("Unregistered user: " + userId);
        }
        return org.springframework.security.core.userdetails.User.builder().username(user.getUserId())
                .password(user.getUserPassword())
                .authorities("ROLE_USER")
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    @Transactional
    public User save(User user) {
        if (findByUserId(user.getUserId()) != null) {
            throw HttpServerErrorException
                    .create(HttpStatus.INTERNAL_SERVER_ERROR, "Already Exist User.",
                            new HttpHeaders(), null, null);
        }
        User registration = new User();
        registration.setUserId(user.getUserId());
        registration.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userRepository.save(registration);
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }
}
