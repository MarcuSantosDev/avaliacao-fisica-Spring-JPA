package com.marcusantosproject.avaliacaofisica.service;


import com.marcusantosproject.avaliacaofisica.database.model.AlunosEntity;
import com.marcusantosproject.avaliacaofisica.database.model.AvaliacoesFisicasEntity;
import com.marcusantosproject.avaliacaofisica.database.repository.AlunosRepository;
import com.marcusantosproject.avaliacaofisica.database.repository.AvaliacoesFisicasRepository;
import com.marcusantosproject.avaliacaofisica.dto.AvaliacaoFisicaDto;
import com.marcusantosproject.avaliacaofisica.dto.AvaliacoesFisicasProjection;
import com.marcusantosproject.avaliacaofisica.exceptions.BadRequestException;
import com.marcusantosproject.avaliacaofisica.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class AvaliacaoFisicaService {
    private final AvaliacoesFisicasRepository avaliacoesFisicasRepository;
    private final AlunosRepository alunosRepository;

    public void criarAvaliacaoFisica(AvaliacaoFisicaDto avaliacaoFisicaDto){
        AlunosEntity aluno = alunosRepository.findById(avaliacaoFisicaDto.getAlunoId()).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        AvaliacoesFisicasEntity avaliacaoFisica = aluno.getAvaliacaoFisica();
        if (avaliacaoFisica != null){
            throw new BadRequestException("Avaliação física já cadastrada para este aluno");
        }
        avaliacaoFisica = AvaliacoesFisicasEntity.builder().peso(avaliacaoFisicaDto.getPeso()).altura(avaliacaoFisicaDto.getAltura()).porcentagemGorduraCorporal(avaliacaoFisicaDto.getPorcentagemGorduraCorporal()).build();

        aluno.setAvaliacaoFisica(avaliacaoFisica);
        alunosRepository.save(aluno);
    }

    public List<AvaliacoesFisicasProjection> getAllAvaliacoes(){
        return avaliacoesFisicasRepository.getAllAvaliacoes();
    }

    public Page<AvaliacoesFisicasProjection> getAllAvaliacoesPageable(Integer page, Integer size){
        return avaliacoesFisicasRepository.getAllAvaliacoesPageable(PageRequest.of(page, size));
    }
}
