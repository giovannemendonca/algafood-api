package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.service.VendaQueryService;
import com.algaworks.algafood.domain.filter.VendaDiariaFilter;
import com.algaworks.algafood.domain.model.dto.VendaDiaria;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/estatisticas")
public class EstatisticasController {

    private final VendaQueryService vendaQueryService;
    EstatisticasController(VendaQueryService vendaQueryService) {
        this.vendaQueryService = vendaQueryService;
    }

    @GetMapping("/vendas-diarias")
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
        return vendaQueryService.consultarVendasDiarias(filtro);
    }
}