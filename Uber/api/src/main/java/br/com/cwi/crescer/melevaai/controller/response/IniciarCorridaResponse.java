package br.com.cwi.crescer.melevaai.controller.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class IniciarCorridaResponse {
    private Long tempoEstimado;
    private BigDecimal valorEstimado;
}
