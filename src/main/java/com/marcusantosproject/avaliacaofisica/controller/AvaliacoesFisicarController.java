package com.marcusantosproject.avaliacaofisica.controller;

import com.marcusantosproject.avaliacaofisica.dto.AvaliacaoFisicaDto;
import com.marcusantosproject.avaliacaofisica.exceptions.BadRequestException;
import com.marcusantosproject.avaliacaofisica.exceptions.NotFoundException;
import com.marcusantosproject.avaliacaofisica.service.AvaliacaoFisicaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/avaliacoes")
@RequiredArgsConstructor
@Validated
public class AvaliacoesFisicarController {
    private final AvaliacaoFisicaService avaliacaoFisicaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAvaliacaoFisica(@Valid @RequestBody AvaliacaoFisicaDto avaliacaoFisicaDto) throws NotFoundException, BadRequestException {
        avaliacaoFisicaService.criarAvaliacaoFisica(avaliacaoFisicaDto);
    }




}
