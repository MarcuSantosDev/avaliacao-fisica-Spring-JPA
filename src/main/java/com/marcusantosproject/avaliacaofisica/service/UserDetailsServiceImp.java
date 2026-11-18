package com.marcusantosproject.avaliacaofisica.service;

import com.marcusantosproject.avaliacaofisica.database.model.AlunosEntity;
import com.marcusantosproject.avaliacaofisica.database.repository.AlunosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {
    private final AlunosRepository alunosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return alunosRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
