package br.com.cwi.crescer.oldflix.controller.response;

import br.com.cwi.crescer.oldflix.Categoria;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class IncluirFilmeResponse {

    private Long id;
    private String titulo;
    private String descricao;
    private boolean disponivel = true;
    private Categoria categoria;
}
