package br.com.cwi.crescer.melevaai.controller.response;

import br.com.cwi.crescer.melevaai.model.SituacaoUsuario;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BuscarPassageiroResponse {

    private Long id;
    private String nome;
    private String CPF;
    private BigDecimal creditos;
    private BigDecimal avaliacao;
    private SituacaoUsuario situacao;
    private LocalDate dataNascimento;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DetalharCorridaResponse corrida;
}
