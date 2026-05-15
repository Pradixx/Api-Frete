package com.elo7.frete.dto;

public record SalvarProdutoDTO(
        String cepDestino,
        double pesoKG,
        double alturaCM,
        double larguraCM,
        double comprimentoCM,
        double valorDeclarado
) {
}
