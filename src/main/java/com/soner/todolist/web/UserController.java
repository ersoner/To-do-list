package com.soner.todolist.web;

import com.soner.todolist.entity.User;
import com.soner.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping({""})
    public @ResponseBody
    Iterable<User> index() {
        return userRepository.findAll();
    }

    @PostMapping({"/register"})
    public ResponseEntity<User> store(@Valid @RequestParam("fullName") String fullName, @RequestParam("email") String email, @RequestParam("password") String password) {
        User user = new User();
        user.setFull_name(fullName);
        user.setEmail(email);
        user.setPassword(password);
        userRepository.save(user);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping({"/login"})
    @ResponseBody
    public ResponseEntity<User> login(@RequestParam("email") String email, @RequestParam("password") String password)
    {
        User userNew = userRepository.findByEmailAndPassword(email,password);

        return new ResponseEntity<User>(userNew, HttpStatus.OK);
    }

}
