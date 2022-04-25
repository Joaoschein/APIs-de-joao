package br.com.cwi.crescer.melevaai.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SolicitarCorridaRequest {

    private Long id;
    private Double pontoInicialX;
    private Double pontoInicialY;
    private Double pontoFinalX;
    private Double pontoFinalY;
}
