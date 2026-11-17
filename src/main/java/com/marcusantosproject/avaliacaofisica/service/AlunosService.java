package com.marcusantosproject.avaliacaofisica.service;

import com.marcusantosproject.avaliacaofisica.database.model.AlunosEntity;
import com.marcusantosproject.avaliacaofisica.database.model.AvaliacoesFisicasEntity;
import com.marcusantosproject.avaliacaofisica.database.model.TreinosEntity;
import com.marcusantosproject.avaliacaofisica.database.repository.AlunosRepository;
import com.marcusantosproject.avaliacaofisica.database.repository.AvaliacoesFisicasRepository;
import com.marcusantosproject.avaliacaofisica.database.repository.TreinosRepository;
import com.marcusantosproject.avaliacaofisica.dto.AlunoDto;
import com.marcusantosproject.avaliacaofisica.exceptions.BadRequestException;
import com.marcusantosproject.avaliacaofisica.exceptions.NotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    public void deletarAluno(Integer alunoId) throws Exception {
        AlunosEntity aluno = alunosRepository.findById(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        // 1. Deletar os treinos do aluno
        List<Integer> treinosAlunoIds = aluno.getTreinos().stream()
                .map(TreinosEntity::getId)
                .toList();

        treinosRepository.deleteAllById(treinosAlunoIds);

        // 2. Deletar a avaliação física, caso exista
        AvaliacoesFisicasEntity avaliacao = aluno.getAvaliacaoFisica();

        if (avaliacao != null) {
            avaliacoesFisicasRepository.deleteById(avaliacao.getId());
        }

        // 3. Deletar o aluno
        alunosRepository.delete(aluno);
    }
}
