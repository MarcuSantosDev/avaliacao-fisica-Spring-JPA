package com.marcusantosproject.avaliacaofisica.service;

import com.marcusantosproject.avaliacaofisica.config.TokenProvider;
import com.marcusantosproject.avaliacaofisica.database.model.AlunosEntity;
import com.marcusantosproject.avaliacaofisica.database.model.RolesEntity;
import com.marcusantosproject.avaliacaofisica.database.repository.AlunosRepository;
import com.marcusantosproject.avaliacaofisica.database.repository.RolesRepository;
import com.marcusantosproject.avaliacaofisica.dto.LoginRequestDto;
import com.marcusantosproject.avaliacaofisica.dto.RegisterRequestDto;
import com.marcusantosproject.avaliacaofisica.dto.TokenResponseDto;
import com.marcusantosproject.avaliacaofisica.enums.RoleTypeEnum;
import com.marcusantosproject.avaliacaofisica.exceptions.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService {
private final AlunosRepository alunosRepository;
private final RolesRepository rolesRepository;
private final PasswordEncoder passwordEncoder;
private final AuthenticationManager authenticationManager;
private final TokenProvider tokenProvider;

@Value("${security.jwt.expiration}")
private Long expirationTime;


    public void resgister(RegisterRequestDto dto) {
        AlunosEntity aluno = alunosRepository.findByEmail(dto.getEmail())
                .orElse(null);

        if (aluno != null) {
            throw new BadRequestException("Aluno já cadastrado com este email");
        }

        RolesEntity role = rolesRepository.findByNome(RoleTypeEnum.ROLE_ALUNO.name()).orElseGet(() -> rolesRepository.save(RolesEntity.builder().nome(RoleTypeEnum.ROLE_ALUNO.name()).build()));

        alunosRepository.save(AlunosEntity.builder()
                .nome(dto.getNome())
                .email(dto.getEmail()).roles(Set.of(role))
                .senha(passwordEncoder.encode(dto.getSenha()))
                .build());
    }

    public TokenResponseDto login(LoginRequestDto dto)   {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha()));
            String token = tokenProvider.gerarToken(authentication);
            return new TokenResponseDto(token,expirationTime);

        }
        catch (BadCredentialsException e){
            throw new BadRequestException("Credenciais inválidas");
        }
        catch (Exception e){
            throw e;
        }
    }
}
