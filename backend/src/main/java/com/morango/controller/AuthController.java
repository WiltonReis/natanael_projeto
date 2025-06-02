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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


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
        System.out.println("Tentando login para: " + userAuthDTO.getUsername());
        var usernamePass = new UsernamePasswordAuthenticationToken(userAuthDTO.getUsername(), userAuthDTO.getPassword());
        var  authentication = authenticationManager.authenticate(usernamePass);
        System.out.println("Autenticado com sucesso!");
        User user = repository.findByUsername(((UserDetails) authentication.getPrincipal()).getUsername());
        var token = tokenService.generateToken(user);
        return ResponseEntity.ok(new TokenDTO(token));
        
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAuthDTO userAuthDTO) {
        System.out.println("Chegou no register");
        if(repository.findByUsername(userAuthDTO.getUsername()) != null) {
            System.out.println("Usuário já existe");
            return ResponseEntity.badRequest().build();
        }
        else {
            System.out.println("Criando novo usuário");
            User entity = new User(userAuthDTO.getUsername(), passwordEncoder.encode(userAuthDTO.getPassword()));
            repository.save(entity);
            return ResponseEntity.ok().build();
        }
    }
    
}
