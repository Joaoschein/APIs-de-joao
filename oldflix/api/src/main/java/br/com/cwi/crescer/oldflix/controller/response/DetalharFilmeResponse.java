package br.com.cwi.crescer.oldflix.controller.response;

import br.com.cwi.crescer.oldflix.Categoria;
import br.com.cwi.crescer.oldflix.Situacao;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class DetalharFilmeResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private boolean disponivel;
    private Categoria categoria;
    private String responsavel;
    private LocalDate dataDeRetirada;
    private Situacao situcao;
    private LocalDate dataDeEntrega;
}
