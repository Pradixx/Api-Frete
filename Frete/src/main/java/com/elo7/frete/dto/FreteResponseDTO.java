package com.elo7.frete.dto;

public record FreteResponseDTO (
        String Transportadora,
        double custoTotal,
        Integer prazo
) {
}
