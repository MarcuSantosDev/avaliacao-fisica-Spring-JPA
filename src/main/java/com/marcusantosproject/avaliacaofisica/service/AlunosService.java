package com.marcusantosproject.avaliacaofisica.service;

import com.marcusantosproject.avaliacaofisica.database.model.AlunosEntity;
import com.marcusantosproject.avaliacaofisica.database.model.AvaliacoesFisicasEntity;
import com.marcusantosproject.avaliacaofisica.database.repository.AlunosRepository;
import com.marcusantosproject.avaliacaofisica.database.repository.AvaliacoesFisicasRepository;
import com.marcusantosproject.avaliacaofisica.database.repository.TreinosRepository;
import com.marcusantosproject.avaliacaofisica.dto.AlunoDto;
import com.marcusantosproject.avaliacaofisica.exceptions.BadRequestException;
import com.marcusantosproject.avaliacaofisica.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunosService {

    private final AvaliacoesFisicasRepository avaliacoesFisicasRepository;
    private final TreinosRepository treinosRepository;
    private final AlunosRepository alunosRepository;

    public List<AlunosEntity> findAll(){
        return alunosRepository.findAll();
    }

    public void criarAluno(AlunoDto alunoDto) {
        AlunosEntity aluno = alunosRepository.findByEmail(alunoDto.getEmail())
                .orElse(null);

        if (aluno != null) {
            throw new BadRequestException("Aluno já cadastrado com este email");
        }

        alunosRepository.save(AlunosEntity.builder()
                .nome(alunoDto.getNome())
                .email(alunoDto.getEmail())
                .build());
    }

    public AvaliacoesFisicasEntity getAlunoAvalicacao(Integer alunoId) {
        AlunosEntity aluno = alunosRepository.findById(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        AvaliacoesFisicasEntity avaliacao = aluno.getAvaliacaoFisica();
        if (avaliacao == null) {
            throw new NotFoundException("Avaliação física não encontrada para este aluno");
        }

        return avaliacao;
    }
}
