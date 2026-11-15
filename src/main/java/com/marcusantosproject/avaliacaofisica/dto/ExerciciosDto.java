package com.marcusantosproject.avaliacaofisica.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ExerciciosDto {

    @NotBlank(message = "O nome do exercício é obrigatório")
    private String nome;
    @NotNull(message = "O grupo muscular é obrigatório")
    private String grupoMuscular;
}

