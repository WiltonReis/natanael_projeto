package com.morango.service;

import com.morango.model.entities.Produtor;
import com.morango.repository.ProdutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutorService {

    @Autowired
    private ProdutorRepository repo;

    public List<Produtor> listarTodos() {
        return repo.findAll();
    }

    public List<Produtor> buscarPorCidade(String cidade) {
        return repo.findByCidadeContainingIgnoreCase(cidade);
    }

    public List<Produtor> listarComExperiencias() {
        return repo.findByExperienciaTrue();
    }

    public Produtor salvar(Produtor produtor) {
        return repo.save(produtor);
    }
}