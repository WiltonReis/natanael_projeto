package com.morango.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.morango.model.entities.Produtor;

import java.util.List;

public interface ProdutorRepository extends JpaRepository<Produtor, Long> {
    List<Produtor> findByCidadeContainingIgnoreCase(String cidade);
    List<Produtor> findByExperienciaTrue();
}
