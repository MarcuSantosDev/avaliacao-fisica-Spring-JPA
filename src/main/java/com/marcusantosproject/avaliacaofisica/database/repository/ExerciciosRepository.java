package com.marcusantosproject.avaliacaofisica.database.repository;

import com.marcusantosproject.avaliacaofisica.database.model.ExerciciosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciciosRepository extends JpaRepository<ExerciciosEntity,Integer> {
    List<ExerciciosEntity> findAllByGrupoMuscular(String grupoMuscular);

    @Query(value = """
        SELECT e
        FROM ExerciciosEntity e
        WHERE UPPER(e.grupoMuscular) = UPPER(:grupoMusular)
        """)
    List<ExerciciosEntity> findAllByGrupoMuscularJpql(@Param("grupoMuscular") String grupoMuscular);

//    @NativeQuery(value = """
//        SELECT e
//        FROM exercicios e
//        WHERE UPPER(e.grupo_muscular) = UPPER(:grupoMuscular)
//    """)
//    List<ExerciciosEntity> findAllByGrupoMuscularNative(@Param("grupoMuscular") String grupoMuscular);
}
