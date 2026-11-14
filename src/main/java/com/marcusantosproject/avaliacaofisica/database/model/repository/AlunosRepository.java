package com.marcusantosproject.avaliacaofisica.database.model.repository;

import com.marcusantosproject.avaliacaofisica.database.model.entity.AlunosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunosRepository extends JpaRepository<AlunosEntity,Integer> {
}
