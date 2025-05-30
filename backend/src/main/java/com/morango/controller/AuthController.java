package com.morango.controller;

import com.morango.model.dto.TokenDTO;
import com.morango.model.dto.UserAuthDTO;
import com.morango.model.entities.User;
import com.morango.repository.UserRepository;
import com.morango.security.TokenService;


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

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuthDTO userAuthDTO) {
        var usernamePass = new UsernamePasswordAuthenticationToken(userAuthDTO.getUsername(), userAuthDTO.getPassword());
        var  authentication = authenticationManager.authenticate(usernamePass);
        var token = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenDTO(token));
        
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAuthDTO userAuthDTO) {
        if(repository.findByUsername(userAuthDTO.getUsername()) != null) {
            return ResponseEntity.badRequest().build();
        }
        else {
            User entity = new User(userAuthDTO.getUsername(), passwordEncoder.encode(userAuthDTO.getPassword()));
            repository.save(entity);
            return ResponseEntity.ok().build();
        }
    }
    
}
