package com.marcusantosproject.avaliacaofisica.database.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RolesEntity implements GrantedAuthority {
    @Id
    private Integer id;
    private String nome;


    @Override
    public @Nullable String getAuthority() {
        return name;
    }
}
