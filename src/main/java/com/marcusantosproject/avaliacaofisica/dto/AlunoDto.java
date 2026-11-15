package com.marcusantosproject.avaliacaofisica.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AlunoDto {

    @NotBlank
    private String nome;
    @NotBlank
    private String email;
}
