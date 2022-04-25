package br.com.cwi.crescer.oldflix.controller.request;

import br.com.cwi.crescer.oldflix.Categoria;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class IncluirFilmeRequest {

    private String titulo;
    private String descricao;
    private Categoria categoria;


}
