package com.elo7.frete.controller;

import com.elo7.frete.dto.FreteResponseDTO;
import com.elo7.frete.dto.SalvarProdutoDTO;
import com.elo7.frete.service.FreteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/frete")
@RequiredArgsConstructor
public class FreteController {

    private final FreteService freteService;

    @PostMapping
    public List<FreteResponseDTO> criar (@RequestBody SalvarProdutoDTO dto) {
        return freteService.salvarProduto(dto);
    }
}
