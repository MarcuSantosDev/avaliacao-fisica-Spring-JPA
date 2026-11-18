package com.marcusantosproject.avaliacaofisica.controller;

import com.marcusantosproject.avaliacaofisica.database.model.AlunosEntity;
import com.marcusantosproject.avaliacaofisica.database.model.AvaliacoesFisicasEntity;
import com.marcusantosproject.avaliacaofisica.dto.AlunoDto;
import com.marcusantosproject.avaliacaofisica.service.AlunosService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/alunos")
@RequiredArgsConstructor
@Validated
public class AlunoController {
    private final AlunosService alunosService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AlunosEntity> findAll(){
        return alunosService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAluno(@Valid @RequestBody AlunoDto alunoDto) {
        alunosService.criarAluno(alunoDto);
    }

    @PreAuthorize("#alunoId == authentication.principal.id or hasRole('ADMIN')")
    @GetMapping("/{alunoId}/avaliacao")
    public AvaliacoesFisicasEntity getAvaliacaoFisica(@PathVariable Integer alunoId) {
        return alunosService.getAlunoAvalicacao(alunoId);
    }

    @DeleteMapping("/{alunoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerAluno(@PathVariable Integer alunoId) throws Exception {
        alunosService.deletarAluno(alunoId);
    }

}



