package com.morango.controller;

import com.morango.model.dto.UserAuthDTO;
import com.morango.model.entities.User;
import com.morango.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuthDTO userAuthDTO) {
        var usernamePass = new UsernamePasswordAuthenticationToken(userAuthDTO.getUserName(), userAuthDTO.getPassword());
        var authentication = authenticationManager.authenticate(usernamePass);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAuthDTO userAuthDTO) {
        if(repository.findByUsername(userAuthDTO.getUserName()) != null) {
            return ResponseEntity.badRequest().build();
        }
        else {
            User entity = new User(userAuthDTO.getUserName(), new BCryptPasswordEncoder().encode(userAuthDTO.getPassword()));
            repository.save(entity);
            return ResponseEntity.ok().build();
        }
    }
    
}
