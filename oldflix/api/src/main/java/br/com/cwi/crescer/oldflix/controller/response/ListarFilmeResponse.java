package br.com.cwi.crescer.oldflix.controller.response;

import br.com.cwi.crescer.oldflix.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ListarFilmeResponse {
    private Long id;
    private String titulo;
    private String descricao;
    private boolean disponivel;
    private Categoria categoria;
}
