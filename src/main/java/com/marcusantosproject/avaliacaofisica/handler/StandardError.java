package com.marcusantosproject.avaliacaofisica.handler;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandardError implements Serializable {
    private static final Long serialVersionUID = 1L;
    private Long timestamp;
    private Integer status;
    private String error;
    private String mensage;
    private String path;
}
