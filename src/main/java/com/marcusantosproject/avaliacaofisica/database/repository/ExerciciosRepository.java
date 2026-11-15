package com.marcusantosproject.avaliacaofisica.database.model.repository;

import com.marcusantosproject.avaliacaofisica.database.model.entity.ExerciciosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciciosRepository extends JpaRepository<ExerciciosEntity,Integer> {
}
