package com.marcusantosproject.avaliacaofisica.database.repository;

import com.marcusantosproject.avaliacaofisica.database.model.AlunosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunosRepository extends JpaRepository<AlunosEntity,Integer> {
    Optional<AlunosEntity> findByEmail(String email);
}
