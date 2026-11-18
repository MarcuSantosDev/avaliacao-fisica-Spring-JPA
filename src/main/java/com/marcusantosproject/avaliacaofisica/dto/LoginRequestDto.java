package com.marcusantosproject.avaliacaofisica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LoginRequestDto {

    @NotBlank
    private String email;
    @NotBlank
    private String senha;
}
