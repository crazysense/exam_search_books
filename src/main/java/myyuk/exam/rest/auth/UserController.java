package myyuk.exam.rest.auth;

import myyuk.exam.component.JwtTokenProvider;
import myyuk.exam.entity.User;
import myyuk.exam.response.AuthenticationToken;
import myyuk.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Collections;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value = "/login")
    public AuthenticationToken login(@RequestBody User user) {
        String userId = user.getUserId();
        String userPassword = user.getUserPassword();

        User registeredUser = userService.findByUserId(userId);
        if (registeredUser == null || !userService.passwordEncoder().matches(userPassword, registeredUser.getUserPassword())) {
            throw HttpServerErrorException
                    .create(HttpStatus.INTERNAL_SERVER_ERROR, "Unregistered user or invalid password.",
                            new HttpHeaders(), null, null);
        }

        String token = jwtTokenProvider.createToken(userId, Collections.singletonList("USER"));
        return new AuthenticationToken(userId, token);
    }

    @PostMapping(value = "/registration")
    @Transactional
    public User register(@RequestBody User user) {
        if (userService.findByUserId(user.getUserId()) != null) {
            throw HttpServerErrorException
                    .create(HttpStatus.INTERNAL_SERVER_ERROR, "Already Exist User.",
                            new HttpHeaders(), null, null);
        }

        User registration = new User();
        registration.setUserId(user.getUserId());
        registration.setUserPassword(userService.passwordEncoder().encode(user.getUserPassword()));
        return userService.save(registration);
    }

}
