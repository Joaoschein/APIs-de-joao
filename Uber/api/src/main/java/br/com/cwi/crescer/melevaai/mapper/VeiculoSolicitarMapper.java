package br.com.cwi.crescer.melevaai.mapper;

import br.com.cwi.crescer.melevaai.controller.response.VeiculoSolicitarResponse;
import br.com.cwi.crescer.melevaai.model.Veiculo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VeiculoSolicitarMapper {
    public static VeiculoSolicitarResponse toVeiculoSolicitarResponse(Veiculo entity){
        return new ModelMapper().map(entity, VeiculoSolicitarResponse.class);
    }

    private VeiculoSolicitarMapper(){}
}
