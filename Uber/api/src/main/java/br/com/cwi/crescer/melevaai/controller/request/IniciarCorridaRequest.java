package br.com.cwi.crescer.melevaai.controller.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IniciarCorridaRequest {

    private Long corridaId;
}
