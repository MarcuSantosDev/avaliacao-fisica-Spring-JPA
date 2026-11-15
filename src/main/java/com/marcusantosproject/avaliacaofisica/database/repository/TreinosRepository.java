package com.marcusantosproject.avaliacaofisica.database.repository;

import com.marcusantosproject.avaliacaofisica.database.model.TreinosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreinosRepository extends JpaRepository<TreinosEntity,Integer> {
    Optional<TreinosEntity> findByNomeAndAlunoId(String nome, Integer id);
}
