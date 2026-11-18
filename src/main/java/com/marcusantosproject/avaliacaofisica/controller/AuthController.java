package com.marcusantosproject.avaliacaofisica.controller;

import com.marcusantosproject.avaliacaofisica.dto.LoginRequestDto;
import com.marcusantosproject.avaliacaofisica.dto.RegisterRequestDto;
import com.marcusantosproject.avaliacaofisica.dto.TokenResponseDto;
import com.marcusantosproject.avaliacaofisica.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequestDto registerRequestDto) {
        authenticationService.resgister(registerRequestDto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenResponseDto login(@RequestBody @Valid LoginRequestDto loginRequestDto) throws Exception{
        return authenticationService.login(loginRequestDto);
    }
}
