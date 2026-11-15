package com.marcusantosproject.avaliacaofisica.service;

import com.marcusantosproject.avaliacaofisica.database.model.ExerciciosEntity;
import com.marcusantosproject.avaliacaofisica.database.repository.ExerciciosRepository;
import com.marcusantosproject.avaliacaofisica.dto.ExerciciosDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciciosService {
    private final ExerciciosRepository exerciciosRepository;


    public List<ExerciciosEntity> findAll(){
        return exerciciosRepository.findAll();
    }

    public void save(ExerciciosDto exercicioDto) {
        ExerciciosEntity exercicio = ExerciciosEntity.builder()
                .nome(exercicioDto.getNome())
                .grupoMuscular(exercicioDto.getGrupoMuscular())
                .build();

        exerciciosRepository.save(exercicio);
    }

    public List<ExerciciosEntity> getExerciciosByGrupoMuscular(String grupoMuscular){
        return exerciciosRepository.findAllByGrupoMuscular(grupoMuscular);
    }
}
