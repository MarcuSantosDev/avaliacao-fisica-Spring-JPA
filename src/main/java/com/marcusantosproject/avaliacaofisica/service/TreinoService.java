package com.marcusantosproject.avaliacaofisica.service;

import com.marcusantosproject.avaliacaofisica.database.model.AlunosEntity;
import com.marcusantosproject.avaliacaofisica.database.model.ExerciciosEntity;
import com.marcusantosproject.avaliacaofisica.database.model.TreinosEntity;
import com.marcusantosproject.avaliacaofisica.database.repository.AlunosRepository;
import com.marcusantosproject.avaliacaofisica.database.repository.ExerciciosRepository;
import com.marcusantosproject.avaliacaofisica.database.repository.TreinosRepository;
import com.marcusantosproject.avaliacaofisica.dto.TreinoDto;
import com.marcusantosproject.avaliacaofisica.exceptions.BadRequestException;
import com.marcusantosproject.avaliacaofisica.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TreinoService {
    private final AlunosRepository alunosRepository;
    private final ExerciciosRepository exerciciosRepository;
    private final TreinosRepository treinosRepository;

    public void criarTreino(TreinoDto treinoDto) {
        Set<ExerciciosEntity> exercicios = new HashSet<>();

        AlunosEntity aluno = alunosRepository.findById(treinoDto.getAlunoId())
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        TreinosEntity treino = treinosRepository.findByNomeAndAlunoId(treinoDto.getNome(), treinoDto.getAlunoId())
                .orElse(null);

        if (treino != null){
            throw new BadRequestException("Já existe um treino com esse nome para esse aluno");
        }

        for (Integer exercicioId : treinoDto.getExerciciosIds()) {
            ExerciciosEntity exercicio = exerciciosRepository.findById(exercicioId)
                    .orElseThrow(() -> new NotFoundException(String.format("Exercício %s não encontrado", exercicioId)));

            exercicios.add(exercicio);
        }

        treino = TreinosEntity.builder()
                .nome(treinoDto.getNome())
                .aluno(aluno)
                .exercicios(exercicios)
                .build();

        treinosRepository.save(treino);
    }
}
