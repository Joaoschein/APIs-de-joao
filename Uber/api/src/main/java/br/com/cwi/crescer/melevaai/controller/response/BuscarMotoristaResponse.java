package br.com.cwi.crescer.melevaai.controller.response;

import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BuscarMotoristaResponse {

    private Long id;
    private String nome;
    private String CPF;
    private BigDecimal creditos;
    private BigDecimal avaliacao;
    private SituacaoUsuario situacao;

    @JsonInclude(Include.NON_NULL)
    private DetalharCorridaResponse corrida;
}
