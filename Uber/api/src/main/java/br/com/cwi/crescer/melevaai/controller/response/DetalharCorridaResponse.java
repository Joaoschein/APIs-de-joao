package br.com.cwi.crescer.melevaai.controller.response;

import br.com.cwi.crescer.melevaai.model.SituacaoCorrida;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DetalharCorridaResponse {

    private Long id;
    private String nomeMotorista;
    private String nomePassageiro;
    private Double distancia;
    private SituacaoCorrida situacao;
}
