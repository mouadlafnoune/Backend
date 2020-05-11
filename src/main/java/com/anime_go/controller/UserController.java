 package com.anime_go.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.anime_go.entities.User;
import com.anime_go.repositories.IUser;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/v1/users")

public class UserController {

    @Autowired
    private IUser userRepository;

    @GetMapping("/")
    public ResponseEntity findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{idUser}")
    public ResponseEntity findUserBysId(@PathVariable(name = "idUser") Long idUser) {
        if (idUser == null) {
            return ResponseEntity.badRequest().body("Cannot retrieve user with null ID");
        }
        User user = userRepository.getOne(idUser);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody User user) {
        if (user == null) {
            return ResponseEntity.badRequest().body("Cannot create user with empty fields");
        }
        User createdUser = userRepository.save(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam(name = "mail") String mail, @RequestParam(name = "password") String password) {
        if (StringUtils.isEmpty(mail) || StringUtils.isEmpty(password)) {
            return ResponseEntity.badRequest().body("Cannot login with empty user mail or password");
        }
        User authenticatedUser = userRepository.findByMailAndPassword(mail, password);
        if (authenticatedUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authenticatedUser);
    }
}