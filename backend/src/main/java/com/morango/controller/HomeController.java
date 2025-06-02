package com.morango.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.morango.model.dto.UserAuthDTO;
import com.morango.model.entities.User;
import com.morango.repository.UserRepository;

@Controller
public class HomeController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping("/")
    public String home() {
        return "Home";
    }
    
    @GetMapping("/api/auth/login")
    public String login() {
        return "Login";
    }

    @GetMapping("/register")
    public String register() {
        return "Register";
    }
    
    @PostMapping("/register")
    public String register(@ModelAttribute UserAuthDTO userAuthDTO) {
        if(repository.findByUsername(userAuthDTO.getUsername()) != null) {
            System.out.println("Usuário já existe");
            return "redirect:/register"; // ou exibir uma mensagem de erro depois
        } else {
            User entity = new User(userAuthDTO.getUsername(), passwordEncoder.encode(userAuthDTO.getPassword()));
            repository.save(entity);
            return "redirect:/login"; // ou /, como preferir
        }
    }

    

}
