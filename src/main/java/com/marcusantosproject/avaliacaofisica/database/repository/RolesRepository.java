package com.marcusantosproject.avaliacaofisica.database.repository;

import com.marcusantosproject.avaliacaofisica.database.model.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity,Integer> {
    Optional<RolesEntity> findByNome(String role);
}
