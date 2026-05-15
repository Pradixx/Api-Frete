package com.elo7.frete.model;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "cepDestino", nullable = false)
    private String cepDestino;

    @Column(name = "pesoKG", nullable = false)
    private double pesoKG;

    @Column(name = "alturaCM", nullable = false)
    private double alturaCM;

    @Column(name = "larguraCM", nullable = false)
    private double larguraCM;

    @Column(name = "comprimentoCM", nullable = false)
    private double comprimentoCM;

    @Column(name = "valorDeclarado", nullable = false)
    private double valorDeclarado;
}
