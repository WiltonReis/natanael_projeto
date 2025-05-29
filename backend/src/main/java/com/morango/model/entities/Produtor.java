package com.morango.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_produtor")
public class Produtor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String cidade;
    private double latitude;
    private double longitude;
    private String tipoProducao;
    private boolean experiencia;
    private String descricao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public String getTipoProducao() { return tipoProducao; }
    public void setTipoProducao(String tipoProducao) { this.tipoProducao = tipoProducao; }

    public boolean isExperiencia() { return experiencia; }
    public void setExperiencia(boolean experiencia) { this.experiencia = experiencia; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}