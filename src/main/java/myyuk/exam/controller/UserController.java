package myyuk.exam.controller;

import myyuk.exam.model.User;
import myyuk.exam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/user")
    public User test(@RequestParam String id) {
        return userService.findByUserId(id);
    }

    @PostMapping(value = "/registration")
    public User save(@RequestBody User user) {
        return userService.save(user);
    }
}
