package br.com.cwi.crescer.oldflix.controller.request;

import br.com.cwi.crescer.oldflix.Categoria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EditarFilmeRequest {

    private String titulo;
    private String desc;
    private Categoria categoria;

}
