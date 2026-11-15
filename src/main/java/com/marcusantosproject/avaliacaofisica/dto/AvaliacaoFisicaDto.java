package com.marcusantosproject.avaliacaofisica.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AvaliacaoFisicaDto {

    @NotNull(message = "O ID do aluno é obrigatório")
    private Integer alunoId;
    @NotNull(message = "O peso é obrigatório")
    private BigDecimal peso;
    @NotNull(message = "A altura é obrigatória")
    private BigDecimal altura;
    @NotNull(message = "A porcentagem de gordura corporal é obrigatória")
    private BigDecimal porcentagemGorduraCorporal;
}
