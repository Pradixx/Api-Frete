package com.elo7.frete.controller.DTO;

public record FreteResponseDTO (
        String Transportadora,
        double custoTotal,
        Integer prazo
) {
}
