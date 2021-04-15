package com.viniciusog.unidadesfederativas.util;

public class CidadeBuilder {

    private Long id = 1L;
    private String nome = "Ji-Paraná";
    private String prefeito = "Prefeito de Ji-Paraná";
    private Integer populacao = 120000;

    public CidadeBuilder() {

    }

    public CidadeBuilder(Long id, String nome, String prefeito, Integer populacao) {
        this.id = id;
        this.nome = nome;
        this.prefeito = prefeito;
        this.populacao = populacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPrefeito() {
        return prefeito;
    }

    public void setPrefeito(String prefeito) {
        this.prefeito = prefeito;
    }

    public Integer getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Integer populacao) {
        this.populacao = populacao;
    }
}
