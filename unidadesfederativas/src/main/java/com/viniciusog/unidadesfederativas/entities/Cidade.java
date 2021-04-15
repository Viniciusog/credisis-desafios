package com.viniciusog.unidadesfederativas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "cidade")
public class Cidade implements Serializable  {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String prefeito;
    private Integer populacao;

    //Não iremos mostrar o Estado de cada cidade, para não gerar loops.


    //Várias cidades estão se relacionando com o mesmo Estado
    @ManyToOne
    //Nome da coluna referente ao ID de Estado na tabela Cidade, no banco de dados.
    @JoinColumn(name = "estado_id")
    private Estado estado;

    public Cidade() {

    }

    public Cidade(Long id, String nome, String prefeito, Integer populacao, Estado estado) {
        this.id = id;
        this.nome = nome;
        this.prefeito = prefeito;
        this.populacao = populacao;
        this.estado = estado;
    }

    public Cidade(String nome, String prefeito, Integer populacao, Estado estado) {
        this.nome = nome;
        this.prefeito = prefeito;
        this.populacao = populacao;
        this.estado = estado;
    }
}