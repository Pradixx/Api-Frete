package com.elo7.frete.service;

import com.elo7.frete.controller.DTO.FreteResponseDTO;
import com.elo7.frete.controller.DTO.SalvarProdutoDTO;
import com.elo7.frete.infrastructure.entity.Produto;
import com.elo7.frete.infrastructure.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FreteService {

    private final ProdutoRepository produtoRepository;

    private FreteResponseDTO calculoTransportadoraA (SalvarProdutoDTO dto) {
        String[] cepIndices = dto.cepDestino().split("");

        double volume = (dto.alturaCM() / 100) *
                (dto.larguraCM() / 100) *
                (dto.comprimentoCM() / 100);
        double freteBase = 10 + (volume * 50);

        if (cepIndices[0] == "0" || cepIndices[0] == "1") {
            freteBase = freteBase * 0.9;
        }

        //Seguro
        double totalFrete = freteBase + (dto.valorDeclarado() * 0.01);

        return new FreteResponseDTO("Enjoei Express", totalFrete, 3);
    }

    private FreteResponseDTO calculoTransportadoraB (SalvarProdutoDTO dto) {
        double freteBase = 5 + (dto.pesoKG() * 3.50);

        if (dto.pesoKG() > 10) {
            freteBase = freteBase + 20;
        }

        //Seguro
        double totalFrete = freteBase + (dto.valorDeclarado() * 0.01);

        return new FreteResponseDTO("Peso Pesado Log", totalFrete, 5);
    }

    //POST
    public List<FreteResponseDTO> salvarProduto (SalvarProdutoDTO dto) {

        Produto novoProduto = Produto.builder()
                .cepDestino(dto.cepDestino())
                .pesoKG(dto.pesoKG())
                .alturaCM(dto.alturaCM())
                .larguraCM(dto.larguraCM())
                .comprimentoCM(dto.comprimentoCM())
                .valorDeclarado(dto.valorDeclarado())
                .build();


        Produto produtoSalvo = produtoRepository.saveAndFlush(novoProduto);

        List<FreteResponseDTO> opcoes = new ArrayList<>();
        opcoes.add(calculoTransportadoraA(dto));
        opcoes.add(calculoTransportadoraB(dto));

        return opcoes.stream().sorted(Comparator.comparing(FreteResponseDTO::custoTotal)).toList();
    }


}
