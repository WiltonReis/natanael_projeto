package com.morango.controller;

import com.morango.model.entities.Produtor;
import com.morango.service.ProdutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtores")
@CrossOrigin
public class ProdutorController {

    @Autowired
    private ProdutorService service;

    @GetMapping
    public List<Produtor> listar() {
        return service.listarTodos();
    }

    @GetMapping("/cidade")
    public List<Produtor> porCidade(@RequestParam String cidade) {
        return service.buscarPorCidade(cidade);
    }

    @GetMapping("/experiencias")
    public List<Produtor> comExperiencias() {
        return service.listarComExperiencias();
    }

    @PostMapping
    public Produtor cadastrar(@RequestBody Produtor produtor) {
        return service.salvar(produtor);
    }
}