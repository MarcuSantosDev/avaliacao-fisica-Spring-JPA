package com.marcusantosproject.avaliacaofisica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TreinoDto {
    @NotNull(message = "O ID do aluno é obrigatório")
    private Integer alunoId;
    @NotBlank(message = "O nome do treino é obrigatório")
    private String nome;
    @NotEmpty(message = "O treino deve possuir pelo menos um exercício")
    private List<Integer> exerciciosIds;
}
