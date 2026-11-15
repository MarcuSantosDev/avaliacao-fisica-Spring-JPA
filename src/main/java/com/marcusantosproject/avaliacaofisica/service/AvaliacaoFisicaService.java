package com.marcusantosproject.avaliacaofisica.service;


import com.marcusantosproject.avaliacaofisica.database.model.AlunosEntity;
import com.marcusantosproject.avaliacaofisica.database.model.AvaliacoesFisicasEntity;
import com.marcusantosproject.avaliacaofisica.database.repository.AlunosRepository;
import com.marcusantosproject.avaliacaofisica.database.repository.AvaliacoesFisicasRepository;
import com.marcusantosproject.avaliacaofisica.dto.AvaliacaoFisicaDto;
import com.marcusantosproject.avaliacaofisica.exceptions.BadRequestException;
import com.marcusantosproject.avaliacaofisica.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
