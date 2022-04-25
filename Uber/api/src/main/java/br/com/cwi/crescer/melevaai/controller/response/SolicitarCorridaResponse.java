package br.com.cwi.crescer.melevaai.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SolicitarCorridaResponse {

    private Long id;
    private String nomeMotorista;
    private int tempoEstimadoChegada;
    private VeiculoSolicitarResponse veiculo;
}
