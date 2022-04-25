package br.com.cwi.crescer.melevaai.controller.response;

import br.com.cwi.crescer.melevaai.model.Categoria;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VeiculoSolicitarResponse {

    private String placa;
    private Categoria categoria;
    private String modelo;
    private String cor;
    private String foto;
}
